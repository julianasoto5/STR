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

@Plural("Estaciones")
class Estacion extends ReLogoTurtle {
	
	def posX, posY, linea
	def xMin, xMax
	Queue<Pasajero> fila = new LinkedList<>()
	
	def enEstacion(pos) {
		return (pos>=xMin) && (pos <=xMax)
	}
	
	def hacerFila(Pasajero p) {
		fila.add(p)
	}
	
	def getPasajeroEnFila() {
		return fila.poll()
	}
}
