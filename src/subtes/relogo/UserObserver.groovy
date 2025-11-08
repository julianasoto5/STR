package subtes.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import subtes.ReLogoObserver;

class UserObserver extends ReLogoObserver{

	def limAnden = 24
	
	def TIEMPO_TOTAL_SIMULACION = 12000
	
	def PASAJEROS_TOTAL_C 
	def PASAJEROS_TOTAL_F
	
	def frecuencia_hatch_C = 0.0
	def frecuencia_hatch_F = 0.0
	
	def ticks = 0

	def base_total
	def estacionF, estacionC
	
	def tiempos_espera_C = []     // Almacena todos los tiempos individuales de la Línea C
	def tiempos_espera_F = []
	def total_subidos_C = 0       // Cuenta cuántos pasajeros subieron
	def total_subidos_F = 0
	def tiempo_promedio_C = 0.0   // El resultado del promedio
	def tiempo_promedio_F = 0.0
	

	Subte subteC = null, subteF = null
	 @Setup
    def setup() {
        clearAll()
		
		base_total = getTotalPasajerosBase(tipo_dia)
		
		PASAJEROS_TOTAL_C = (int) (base_total * (1 - porcentaje_a_F / 100.0))
		PASAJEROS_TOTAL_F = (int) (base_total * (porcentaje_a_F / 100.0) + nuevos_usuarios)
		
		if (PASAJEROS_TOTAL_C > 0) {
			frecuencia_hatch_C = TIEMPO_TOTAL_SIMULACION / PASAJEROS_TOTAL_C
		} else {
			frecuencia_hatch_C = TIEMPO_TOTAL_SIMULACION // Evita división por cero
		}

		if (PASAJEROS_TOTAL_F > 0) {
			frecuencia_hatch_F = TIEMPO_TOTAL_SIMULACION / PASAJEROS_TOTAL_F
		} else {
			frecuencia_hatch_F = TIEMPO_TOTAL_SIMULACION
		}
		
		setDefaultShape(Pasajero, "person")
		setDefaultShape(Subte, "default")
		setDefaultShape(Cabina, "subteazul")
		setDefaultShape(Vagon, "vagonazul")

		setupFondo()
		
		// Crear pasajeros de la línea C (andén azul)
		/*createPasajeros(100) {
			estacion = estacionC
			setColor(white())
			setxy(random(11) + 5, randomYcor())
			setHeading(random(2) == 0 ? 0 : 180)
		}
		
		// Crear pasajeros de la línea F (andén verde)
		createPasajeros(100) {
			estacion = estacionF
			setColor(yellow())
			setxy(random(11) - 11, randomYcor())
			setHeading(random(2) == 0 ? 0 : 180)
		}*/
			
		subteC = createSubtes(1){
			linea = "C"
			estado = "entrando"
			posInicial = limAnden+4
			frecuencia = frecuenciaC
			capacidad = capacidadC
			setColor(blue())
			setxy(posInicial, 22)
			setSize(10)
			setHeading(180)
			hideTurtle()
			
		}.first()  // ← .first() para obtener el Subte individual
		
		subteF = createSubtes(1){
			linea = "F"
			estado = "entrando"
			posInicial = -limAnden-4
			frecuencia = frecuenciaF
			capacidad = capacidadF
			setColor(green())
			setxy(posInicial, 22)
			setSize(10)
			setHeading(180)
			hideTurtle()
		}.first()// ← .first() para obtener el Subte individual
				
		subteF.createFormacion()
		subteC.createFormacion()

	 }
    

    @Go
    def go() {
		
		ticks += 1
		if (frecuencia_hatch_C > 0 && (ticks % (int)frecuencia_hatch_C == 0)) {
			crearPasajero("C")
		}
		
		if (frecuencia_hatch_F > 0 && (ticks % (int)frecuencia_hatch_F == 0)) {
			crearPasajero("F")
		}
		ask(pasajeros()) {
			mover()
		}
		
		ask(subtes()) {
			mover()
		}
		
		ask(vagones()){
			seguirSubte()
		}
		
		ask(cabinas()){
			seguirSubte()
		}
		
		if (ticks > TIEMPO_TOTAL_SIMULACION) {
			stop()
		}
		
		if (total_subidos_C > 0) {
			// Suma todos los tiempos y los divide por el total de pasajeros subidos
			def suma_C = tiempos_espera_C.sum() ?: 0
			tiempo_promedio_C = suma_C / total_subidos_C
		}
		if (total_subidos_F > 0) {
			def suma_F = tiempos_espera_F.sum() ?: 0
			tiempo_promedio_F = suma_F / total_subidos_F
		}

    }
	
	def setupFondo() {
		ask(patches()){
			setPcolor(gray()+2) //Fondo
			if ((pxcor == -22 && pxcor == -21) || (pxcor == 22 && pxcor == 21)) { setPcolor(yellow()) } //Líneas de seguridad
			if (pxcor == 0) { setPcolor(black()) } //Separador
			
			//Anden Subte F (-24 a -32)
			if (pxcor <= -limAnden) {
				if (pycor < -30) setPcolor(gray()-3) //Fin del anden
				else {
					if ((pxcor == -26) || (pxcor == -30)) setPcolor(gray()-2)	//Rieles
					else if(pxcor <= -25 && pxcor >= -31 && pycor % 2 == 0) setPcolor(brown()+1) //Tablas
						else setPcolor(brown()-2)//Tierra
				}
			}
			
			//Anden Subte C (24 a 32)
			if (pxcor >= limAnden) {
				if (pycor < -30) setPcolor(gray()-3) //Fin del anden
				else {
					if ((pxcor == 26) || (pxcor == 30)) setPcolor(gray()-2)	//Rieles
					else if(pxcor >= 25 && pxcor <= 31 && pycor % 2 == 0) setPcolor(brown()+1) //Tablas
							else setPcolor(brown()-2)//Tierra
				}
			}
		}
	}
	
	def setupVagon(Subte subte) {
		// Crear el vagón asociado a este subte
		subte.vagon = createVagones(1) {
			setSubtePadre(subte)
			setLinea(subte.linea)
			setXcor(subte.getXcor())
			setYcor(subte.getYcor())
			setShape("square")
			setColor(pink())
			setSize(3.5)
		}.first()
	}
	
	def setupCabinas(Subte subte) {
		setDefaultShape(Cabina, "truck")
		
		// Crear el vagón asociado a este subte
		subte.cabinaDelantera = createCabinas(1) {
			setSubte(subte)
			setXcor(subte.getXcor())
			setYcor(subte.getYcor()-5)
			setColor(subte.getColor())
			setSize(5)
			setHeading(0)
			setLabel("FRONT")
		}.first()
		
		subte.cabinaTrasera = createCabinas(1){
			setSubte(subte)
			setXcor(subte.getXcor())
			setYcor(subte.getYcor()+5)
			setColor(subte.getColor())
			setSize(5)
			setHeading(180)
			setLabel("BACK")
		}.first()
		
	}

	
	def getTotalPasajerosBase(String tipoDia) {
		switch (tipoDia) {
			case "laboral":
				return 187              //para 22 y 23 horas
			case "sabado":
				return 3059             //para 16 y 17 horas
			case "domingo":
				return 458      		//para 21 y 22 horas
							
			case "laboral_partido":
				return 311
			case "sabado_partido":
				return 2224
			case "domingo_partido":
				return 774
		}
	}
	
	def registrarTiempoEspera(String linea, int tiempo) {
		if (linea == "C") {
			tiempos_espera_C.add(tiempo)
			total_subidos_C += 1
		} else if (linea == "F") {
			tiempos_espera_F.add(tiempo)
			total_subidos_F += 1
		}
	}
	
	def crearPasajero(String lineaDestino) {
		def xRange = (lineaDestino == "C") ? [1, 24] : [-24, -1]
		def colorPasajero = (lineaDestino == "C") ? white() : yellow()
		
		createPasajeros(1) {
			linea = lineaDestino
			setColor(colorPasajero)
			setSize(2.5)
			setxy(random(xRange[1] - xRange[0]) + xRange[0], randomYcor())
			tick_inicio = ticks
		}
	}
	
	def andenC_ocupacion() {
        def pasajerosAndenC = pasajeros().count{ Pasajero p -> 
            p.linea == "C" && !p.enVagon
        }
        return pasajerosAndenC
    }
	
	def andenF_ocupacion() {
		def pasajerosAndenF = pasajeros().count(){Pasajero p->
			p.linea == "F" && !p.enVagon
		}
		return pasajerosAndenF
	}
	
	def andenC_total() {
		return PASAJEROS_TOTAL_C
	}
	
	def andenF_total() {
		return PASAJEROS_TOTAL_F
	}
	
	def get_tiempo_promedio_C() {
		return tiempo_promedio_C
	}
	def get_tiempo_promedio_F() {
		return tiempo_promedio_F
	}
	
}