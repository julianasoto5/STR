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
    def capacidad = 20
    def ocupacion = 0
	def desplazamiento = 0   // Desplazamiento vertical
    List<Pasajero> pasajeros = []  // lista de pasajeros a bordo
    
    def seguirSubte() {
        if (subtePadre) {
            setXcor(subtePadre.getXcor())
            setYcor(subtePadre.getYcor()+ desplazamiento)
            setHeading(0) // Misma dirección que el tren
        }
    }
    
	def subirPasajero(Pasajero p) {
		pasajeros.add(p)
		p.subirA(this)
		p.setEstado("enVagon")
		ocupacion++;
	}

	//subirPasajeros() es para subir a TODOS los pasajeros que entren en UN TICK
	def subirPasajeros() {
		def esteVagon = this   // Guardamos referencia al vagon
		// Agentset de pasajeros que están en el mismo patch (o muy cerca), del tipo correcto y que no estén ya en subte
		
		List<Pasajero> disponibles = pasajeros().asList().findAll{ p -> (distance(p) < 1)&& p.linea == esteVagon.linea && !p.enVagon}
		
		// Si no hay espacio o no hay disponibles, salir rápido
		def espacio = capacidad - ocupacion
		if ((espacio <= 0) || (count(disponibles) == 0) ) return
	
		// Pedimos a cada pasajero disponible que intente subirse, hasta completar espacio.
		for (d in disponibles){
			if(espacio--) { 
				ask(d){
					esteVagon.pasajeros.add(d)
					d.subirA(esteVagon)
				}
			}
			else break
		}
	
	}
	
	def hayLugar() {
		return (capacidad - ocupacion) > 0
	}
    
    def bajarPasajeros() {
	    pasajeros.forEach({ p ->
			p.setEstado("bajarVagon");   
	    })
	    pasajeros.clear()
	    ocupacion = 0
	}
}