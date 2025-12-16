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
	def cant = 0
	
	def TIEMPO_TOTAL_SIMULACION = 24000 //
	
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
	
	def max_anden_C = 0.0  
	def max_anden_F = 0.0
	
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
    
	 def acumC = 0.0
	 def acumF = 0.0
	@Go
	def go() {
		
		ticks += 1
		// Línea C
		acumC += 1
		if (acumC >= frecuencia_hatch_C) {
			crearPasajero("C")
			acumC -= frecuencia_hatch_C
		}
		
		// Línea F
		acumF += 1
		if (acumF >= frecuencia_hatch_F) {
			crearPasajero("F")
			acumF -= frecuencia_hatch_F
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
		
		if (tiempos_espera_C.size()) {
			// Suma todos los tiempos y los divide por el total de pasajeros subidos
			def suma_C = tiempos_espera_C.sum() ?: 0
			tiempo_promedio_C = suma_C / tiempos_espera_C.size()
			println("Prom C "+tiempo_promedio_C)
		}
		if (tiempos_espera_F.size()) {
			def suma_F = tiempos_espera_F.sum() ?: 0
			tiempo_promedio_F = suma_F / tiempos_espera_F.size()
			println("Prom F "+tiempo_promedio_F)
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
	
	def getTotalPasajerosBase(String tipoDia) {
		switch (tipoDia) {
			case "laboral":
				return 18086              
			case "sabado":
				return 7020           
			case "domingo":
				return 2785      		
		}
	}
	
	def registrarTiempoEspera(String linea, int tiempo) {
		if (linea == "C") {
			tiempos_espera_C.add(tiempo)
		} else if (linea == "F") {
			tiempos_espera_F.add(tiempo)
		}
	}
	
	
	def crearPasajero(String lineaDestino) {
		def xRange = (lineaDestino == "C") ? [1, 24] : [-24, -1]
		def colorPasajero = (lineaDestino == "C") ? white() : yellow()
		cant++
		println(cant)
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
            p.linea == "C" && !p.enVagon}
		if(pasajerosAndenC > max_anden_C)
			max_anden_C = pasajerosAndenC
		println("Max C "+max_anden_C)
        return pasajerosAndenC
    }
	
	def andenF_ocupacion() {
		def pasajerosAndenF = pasajeros().count(){Pasajero p->
			p.linea == "F" && !p.enVagon
		}
		if(pasajerosAndenF > max_anden_F)
			max_anden_F = pasajerosAndenF
		println("Max F "+max_anden_F)
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
	
	def get_max_anden_C() {
		return max_anden_C
	}	
	def get_max_anden_F() {
		return max_anden_F
	}
	
}