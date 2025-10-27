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

class Subte extends ReLogoTurtle {
    def linea
    def estado
    def velocidad = 0.005
    Vagon vagon  // referencia al vagón asociado
	def frecuencia 
	def posInicial
	def tiempoEnEstacion = 0
	def tiempoEsperando = 0
	Estacion estacion
	def Ymin = -16.0
	
    
    def mover() {
		
		switch(estado) {
			case "entrando":
				fd (velocidad) //avanza
				if(detectarEstacionCercana()) {
					//setxy(0, getYcor())
					estado = "enEstacion"
					tiempoEnEstacion = 0
				}
				break
			case "enEstacion":
				vagon.subirPasajero()
				if(++tiempoEnEstacion >= 5000) {
					estado = "saliendo"
					tiempoEnEstacion = 0
				}
				//println("ESTACION posX = "+getXcor()+" posY = " + getYcor())
				
				break
			case "saliendo":
				fd(velocidad)
				if(getYcor() <= Ymin) {
					//el subte terminóla 'vuelta'. 
					
					estado = "afuera" 
				}
				break
			case "afuera":
				vagon.bajarPasajeros()
				vagon.hideTurtle()
				hideTurtle()
				//wait(frecuencia)
				if(++tiempoEsperando == frecuencia) {	
					
					setxy(posInicial, 15)
					vagon.showTurtle()
					showTurtle()
					estado = "entrando"
					tiempoEsperando = 0
				}
				
				break
				
		}
		
        
    }
	
	def detectarEstacionCercana() {
		// Buscar estaciones en la misma posición Y (con tolerancia)
		def estacionesEnMismaY = estaciones().find { estacion ->
			Math.abs(getYcor() - estacion.getYcor()) < 0.5  // Tolerancia de 0.5
		}
		return estacionesEnMismaY
	}
	
}