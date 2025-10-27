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

class Subte extends ReLogoTurtle {
	
    def linea   
    def estado  
    def tiempoEnEstacion = 0
	def tiempoAfuera = 0
    def velocidad = 0.005 
	def frecuencia = 5000
	def capacidad = 5   
	def ocupacion = 0
	
    def mover() {
        switch (estado) {

            case "entrando":
                fd(velocidad)
                
                // Si llegamos a la posici�n de la estaci�n (0)
                if (distance(patch(getXcor(), 0)) < velocidad) { 
                    setxy(getXcor(), 0) // Fija la posici�n en el centro
                    estado = "enEstacion"
                    tiempoEnEstacion = 0
                }
                break

            case "enEstacion":
                tiempoEnEstacion += 1
				
				subirPasajeros()
				           
                if (tiempoEnEstacion >= 5000) { 
                    estado = "saliendo"
                    tiempoEnEstacion = 0
                    setHeading(0)
                }
                break

            case "saliendo":
                fd(velocidad)
                
                // Si llegamos al l�mite de la simulaci�n
                if (getYcor() >= 15) { 
                    estado = "afuera"
					bajarPasajeros()
                    setHeading(180) 
					hideTurtle()
                    setxy(getXcor(), 16) // Posici�n inicial para el nuevo ciclo
                    tiempoEnEstacion = 0
                } 
                break
				
			case "afuera":
				tiempoAfuera += 1
			
				if (tiempoAfuera >= frecuencia) {
					estado = "entrando"
					showTurtle()
					tiempoAfuera = 0
				}
				break
				
        }
    }

	def subirPasajeros() {
		def esteSubte = this   // Guardamos referencia al subte
		// Agentset de pasajeros que est�n en el mismo patch (o muy cerca), del tipo correcto y que no est�n ya en subte
	    def disponibles = pasajeros().with({ p -> distance(p) < 1 && p.tipo == linea && !p.enSubte })
	    
	    // Si no hay espacio o no hay disponibles, salir r�pido
	    def espacio = capacidad - ocupacion
	    if (espacio <= 0) return
	    if (count(disponibles) == 0) return
	
	    // Pedimos a cada pasajero disponible que intente subirse, hasta completar espacio.
	    ask(disponibles) { 
	        if (esteSubte.ocupacion < esteSubte.capacidad) {
	            subirA(esteSubte)
	            // actualizamos la ocupaci�n del subte 
	            esteSubte.ocupacion = esteSubte.ocupacion + 1
	        }
	    }
	}
	
	def bajarPasajeros() {
		def esteSubte = this
		// Selecciona pasajeros cuyo miSubte es este subte
		def aBajar = pasajeros().with({ p -> p.miSubte == esteSubte })
		if (count(aBajar) == 0) {
			esteSubte.ocupacion = 0
			return
		}
		ask(aBajar) {
			// eliminar el pasajero del mundo
			die()
		}
		// reiniciamos la ocupaci�n
		esteSubte.ocupacion = 0
	}
}