package subtes1.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import subtes1.ReLogoObserver;

class UserObserver extends ReLogoObserver{
	
	def TIEMPO_TOTAL_SIMULACION = 10000
	
	def PASAJEROS_TOTAL_C 
	def PASAJEROS_TOTAL_F 
	
	def frecuencia_hatch_C = 0.0
	def frecuencia_hatch_F = 0.0
	
	def ticks = 0
	
	def TIPO_DE_DIA = "domingo_partido"
	def PORCENTAJE_A_F = 30           // 30% del total base usa F
	def NUEVOS_USUARIOS_F = 0
	def base_total
	
    @Setup
    def setup() {
        clearAll()
		
		base_total = getTotalPasajerosBase(TIPO_DE_DIA)
		
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

	    // Pintamos los patches
	    ask(patches()) {
	        if (pxcor > 5) {
	            setPcolor(blue())   // Andén C
	        } 
	        else if ((pxcor < 0)&&(pxcor > -11)) {
	            setPcolor(green())  // Andén F
	        }
	        else {
	            setPcolor(black())  
	        }
	    }

		setDefaultShape(Pasajero, "person")
        // Crear pasajeros de la línea C (andén azul)
        /*createPasajeros(15) {
            tipo = "C"
            setColor(white())
            setxy(random(4) + 6, randomXcor())
        }
		
		// Crear pasajeros de la línea F (andén verde)
		createPasajeros(15) {
			tipo = "F"
			setColor(yellow())
			setxy(random(4) - 10, randomXcor())
		}*/
		
		setDefaultShape(Subte, "truck")
		
		createSubtes(1){
			linea = "C"
			estado = "entrando"
			setColor(blue())
			setxy(2, 16)
			setSize(5)
			setHeading(180)
		}
		
		createSubtes(1){
			linea = "F"
			estado = "entrando"
			setColor(green())
			setxy(-13, 16)
			setSize(5)
			setHeading(180)
		}

    }

    @Go
    def go() {
		
		ticks += 1
		
		ask(pasajeros()) {
			mover()
		}
		
		ask(subtes()) {
			mover()
		}
		
		if (frecuencia_hatch_C > 0 && (ticks % (int)frecuencia_hatch_C == 0)) {
			crearPasajero("C")
		}
		
		if (frecuencia_hatch_F > 0 && (ticks % (int)frecuencia_hatch_F == 0)) {
			crearPasajero("F")
		}

    }
	
	def crearPasajero(String lineaDestino) {
		def xRange = (lineaDestino == "C") ? [6, 14] : [-10, -2]
		def colorPasajero = (lineaDestino == "C") ? white() : yellow()
		
		createPasajeros(1) {
			tipo = lineaDestino
			setColor(colorPasajero)
			setxy(random(xRange[1] - xRange[0]) + xRange[0], randomXcor())
			tick_inicio = ticks
		}
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
}