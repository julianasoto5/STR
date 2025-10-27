package subtes1.relogo

import static repast.simphony.relogo.Utility.*
import static repast.simphony.relogo.UtilityG.*

import repast.simphony.relogo.Plural
import repast.simphony.relogo.Stop
import repast.simphony.relogo.Utility
import repast.simphony.relogo.UtilityG
import repast.simphony.relogo.schedule.Go
import repast.simphony.relogo.schedule.Setup
import subtes1.ReLogoTurtle

class Pasajero extends ReLogoTurtle {
	
	def tipo
	def velocidad = 0.01
	def enSubte = false
	def miSubte = null
	def tick_inicio
	
	def mover() {
		
		if(!enSubte) {
			rt(random(20) - 10)
			fd(velocidad)
		
			if (tipo == "C") {
				if (getXcor() < 6) 
					setXcor(6)
					if (setXcor() > 15) 
						setXcor(14)
			} else if (tipo == "F") {
				if (setXcor() > -1) 
					setXcor(-2)
					if (setXcor() < -9) 
						setXcor(-9)
			}
		} else {
			// Se mueve junto al subte
			setxy(miSubte.getXcor(), miSubte.getYcor())
		}
	}

	def subirA(Subte s) {
		if (!enSubte) {
	        enSubte = true
	        miSubte = s
	        setColor(red())    // marcar visualmente que está a bordo
		}
	}
}
