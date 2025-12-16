package subtes.relogo

import static repast.simphony.relogo.Utility.*
import static repast.simphony.relogo.UtilityG.*

import java.util.concurrent.CountDownLatch

import repast.simphony.relogo.Plural
import repast.simphony.relogo.Stop
import repast.simphony.relogo.Utility
import repast.simphony.relogo.UtilityG
import repast.simphony.relogo.schedule.Go
import repast.simphony.relogo.schedule.Setup
import subtes.ReLogoTurtle

@Plural("Vagones")
class Vagon extends ReLogoTurtle {
    Subte subtePadre
    def linea
    def capacidad = 0
    def ocupacion = 0
    List<Pasajero> pasajeros = []  // lista de pasajeros a bordo
    
    def seguirSubte() {
        if (subtePadre) {
            setXcor(subtePadre.getXcor())
            setYcor(subtePadre.getYcor())
            setHeading(subtePadre.getHeading())
        }
    }
    
	def subirPasajero(Pasajero p) {
		pasajeros.add(p)
		p.subirA(this)
		p.setEstado("enVagon")
		ocupacion++;
	}
	
	
	def subirPasajerosEnEstacion() {
		def espacio = capacidad - ocupacion 
		if (espacio <= 0) return 
		
		def esteVagon = this
		
		
		List<Pasajero> disponibles = pasajeros().asList().findAll{ p ->
			p instanceof Pasajero &&
			p.linea == esteVagon.linea &&
			p.estado == "enEstacion"
		}
		
		if (!disponibles.size()) return
		
		for (p in disponibles) {
			if (espacio > 0) {
				ask(p) {
					p.subirA(esteVagon)
					esteVagon.pasajeros.add(p)
				}
				ocupacion++
				espacio--
			} else {
				break 
			}
		}
	}

    
    def bajarPasajeros() {
		
	    pasajeros.forEach({ p ->
			p.setEstado("bajarVagon");   
	    })
		
	    pasajeros.clear()
	    ocupacion = 0
	}
}