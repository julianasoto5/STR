package subtes.relogo

import static repast.simphony.relogo.Utility.*
import static repast.simphony.relogo.UtilityG.*

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
    
    def subirA(Vagon v) {
        if (!enVagon) {
            enVagon = true
            miVagon = v
            setColor(red())
			
        }
    }
    
    def bajar() {
		die()
		/*
        if (enVagon && miVagon) {
            enVagon = false
            // Reposicionar en la estación
            setXcor(miVagon.getXcor())
            setYcor(miVagon.getYcor())
            setColor(green())
            showTurtle()
            miVagon = null
        }*/
    }
    
    def mover() {
        if (!enVagon) {
            // Comportamiento normal buscando subte
            rt(random(20) - 10)
            fd(velocidad)
            
            // Mantener en área del andén
            if (linea == "C") {
                if (getYcor() < 6) setYcor(6)
                if (getYcor() > 15) setYcor(14)
            }
        }else seguirVagon()
    }
	
	
	def seguirVagon() {
		setXcor(miVagon.getXcor())
		setYcor(miVagon.getYcor())
	}
	
	//hacer fila en estacion
}