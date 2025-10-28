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

	
	def TIEMPO_TOTAL_SIMULACION = 10000
	
	def PASAJEROS_TOTAL_C 
	def PASAJEROS_TOTAL_F
	
	def frecuencia_hatch_C = 0.0
	def frecuencia_hatch_F = 0.0
	
	def ticks = 0
	
	def TIPO_DE_DIA = "domingo_partido"
	def tipo_dia
	def PORCENTAJE_A_F = 30           // 30% del total base usa F
	def NUEVOS_USUARIOS_F = 0
	def base_total
	def estacionF, estacionC
	def capacidadC, capacidadF,frecuenciaC, frecuenciaF, porcentaje_a_F, nuevos_usuarios, andenC_ocupacion, andenF_ocupacion, transferidos
	
	Subte subteC = null, subteF = null
	 @Setup
    def setup() {
        clearAll()
		
		base_total = getTotalPasajerosBase(tipo_dia)
		
		PASAJEROS_TOTAL_C = (int) (base_total * (1 - PORCENTAJE_A_F / 100.0))
		PASAJEROS_TOTAL_F = (int) (base_total * (PORCENTAJE_A_F / 100.0) + NUEVOS_USUARIOS_F)
		
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
		
/*		createTests(1){
			setShape("estacion2")  // Tu png de fondo
			setxy(0, 0)
			size = 30  // Muy grande
			setLabel("")
			// Hacer que se quede atrás de otros agentes
		}
*/		setDefaultShape(Pasajero, "person")
        setDefaultShape(Estacion, "flag")
		setDefaultShape(Subte, "default")
		setDefaultShape(Cabina, "subteazul")
		setDefaultShape(Vagon, "vagonazul")
	    // Pintamos los patches
	    ask(patches()) {
	        if (pxcor > 5) { //5-16
	            setPcolor(blue())   // Andén C
	        } 
	        else if ((pxcor < 0)&&(pxcor > -11)) { //-11 - 0
	            setPcolor(green())  // Andén F
	        }
	        else {
	            setPcolor(black())  
	        }
	    }


		
		 estacionC = createEstaciones(1){
			linea = "C"
			xMin = 5
			xMax = 17
			setxy(9, 0)
			setSize(4)
			setColor(red())
			setLabel("DIA "+tipo_dia)
		}.first()
		
		estacionF = createEstaciones(1){
			linea = "F"
			xMin = -11
			xMax = 0
			setxy(-8, 0)
			setSize(4)
			setColor(yellow())
		}.first()
		
		// Crear pasajeros de la línea C (andén azul)
		createPasajeros(100) {
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
		}
		
		
		
		subteC = createSubtes(1){
			linea = "C"
			estado = "entrando"
			posInicial = 2.5
			frecuencia = frecuenciaC
			estacion = estacionC
			capacidad = capacidadC
			setColor(blue())
			setxy(posInicial, 15)
			setSize(0)
			setHeading(180)
			hideTurtle()
			
		}.first()  // ← .first() para obtener el Subte individual
		
		subteF = createSubtes(1){
			linea = "F"
			estado = "entrando"
			posInicial = -13.5
			frecuencia = frecuenciaF
			estacion = estacionF
			capacidad = capacidadF
			setColor(green())
			setxy(posInicial, 15)
			setSize(0)
			setHeading(180)
			hideTurtle()
		}.first()// ← .first() para obtener el Subte individual
				
		subteF.createFormacion()
		subteC.createFormacion()
		/*setupVagon(subteC)  // Ahora son Subte individuales
		setupVagon(subteF)
		
		setupCabinas(subteC)
		setupCabinas(subteF)
		*/
		
		

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
				return 100
			case "sabado":
				return 60
			case "domingo":
				return 40
			
			case "laboral_partido":
				return 150
			case "sabado_partido":
				return 120
			case "domingo_partido":
				return 80
			default:
				return 50
		}
	}
	
	def crearPasajero(String lineaDestino) {
		def xRange = (lineaDestino == "C") ? [6, 14] : [-10, -2]
		def colorPasajero = (lineaDestino == "C") ? white() : yellow()
		
		createPasajeros(1) {
			linea = lineaDestino
			estacion = (linea == "C") ? estacionC : estacionF
			setColor(colorPasajero)
			setxy(random(xRange[1] - xRange[0]) + xRange[0], randomYcor())
			//tick_inicio = ticks
		}
	}
	
	
	/* FUNCIONES PARA MOSTRAR EN EL MONITOR	*/
	//addMonitorWL("andenC_ocupacion", "Personas en andén C", 5)
	//addMonitorWL("andenF_ocupacion", "Personas en andén F", 5)
	//addMonitorWL("transferidos", "Pasajeros transferidos a F", 5)
	//addMonitorWL("getEspacioC", "Espacio en linea C", 1)
	//addMonitorWL("getEspacioF", "Espacio en linea F", 1)
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
	
	

}