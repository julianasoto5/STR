package subtes.relogo

import static repast.simphony.relogo.Utility.*
import static repast.simphony.relogo.UtilityG.*
import repast.simphony.engine.environment.RunEnvironment;

import repast.simphony.relogo.Plural
import repast.simphony.relogo.Stop
import repast.simphony.relogo.Utility
import repast.simphony.relogo.UtilityG
import repast.simphony.relogo.schedule.Go
import repast.simphony.relogo.schedule.Setup
import subtes.ReLogoTurtle

class Pasajero extends ReLogoTurtle {
	def linea
    def velocidad = 0.01
    def enVagon = false
    def miVagon = null
	def enEstacion = false
	def estado = "enEstacion"
	def tick_inicio
    
	
    def subirA(Vagon v) {
        if (!enVagon) {
            enVagon = true
            miVagon = v
			hideTurtle()
			def tick_actual = RunEnvironment.getInstance().getCurrentSchedule().getTickCount()
			
			// 2. Calcular el tiempo de espera (Tiempo Final - Tiempo Inicial)
			// Se realiza el casteo a (int) porque los ticks de Repast son doubles.
			def tiempo_espera = tick_actual - tick_inicio
			println("Tick espera> "+tiempo_espera+"    tick_inicio> "+tick_inicio)
			
			
			//. Obtener el observador (¡SIN PARÉNTESIS!)
			def obs = myObserver // 4. Reportar al Observer.
			obs.registrarTiempoEspera(linea, tiempo_espera as int)
			
        }
    }
    
    def bajar() {
		die()
    }
	
	def mover() {
		switch (estado) {
			case "enEstacion":
				rt(random(20) - 10) 
				fd(velocidad) 
				quedarseEnAnden()
				break
				
			case "enVagon":
				seguirVagon()
				setColor(pink())
				break
				
			case "bajarVagon": 
				bajar();
				break;
		}
	}
	
	def seguirVagon() {
		setXcor(miVagon.getXcor())
		setYcor(miVagon.getYcor())
	}
	
	def quedarseEnAnden() {
 
        def posX = pxcor

        if (linea == "F") {
            if (posX >= -2 || posX <= -22) {
                rt(180) 
				setXcor(-21)
				fd(velocidad)
            }
        }

        else if (linea == "C") {
            if (posX >= 22 || posX <= 2) {
                rt(180)
				setXcor(21)
				fd(velocidad)
            }
        }
    }
}