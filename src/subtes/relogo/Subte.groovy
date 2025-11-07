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
	def longSubte = 10
    def linea
    
    
	def numVagones = 3 // Número de vagones por tren
    
	def capacidad // Capacidad total del tren
	def frecuencia 
	def posInicial
	
	def tiempoEsperando = 0
	Estacion estacion
	
	def estado
	List<Vagon> vagones = []  // Lista de vagones
	Cabina cabinaDelantera
	Cabina cabinaTrasera
	
	def Ymin = -25
	def Ymax = 32
	def velocidad = 0.05
	def tickEspera = 2000
	def tiempoEnEstacion = 0
	
	
	
    
    def mover() {
		switch(estado) {
			case "entrando":
                back(velocidad) //Avanza (hacia abajo, y negativa)
                // Verificar si la cabina BACK llegó al límite
                if (cabinaTrasera.getYcor() <= Ymin) {
                    estado = "enEstacion"
                    tiempoEnEstacion = 0
                }
                break
			case "enEstacion":
				 // Subir pasajeros en todos los vagones
                vagones.each { vagon ->
                    if(vagon.hayLugar() && !estacion.fila.empty) {
                        vagon.subirPasajero(estacion.getPasajeroEnFila())
                    }
                }
				//Si el tiempo de espera ya paso
                if(++tiempoEnEstacion >= tickEspera) {
                    estado = "saliendo"
                    tiempoEnEstacion = 0
                }
                break
			case "saliendo":
                fd( velocidad) //Retrocede (hacia arriba, y positiva)
                // Cuando el subte sale completamente de la pantalla
                if (getYcor() >= Ymax) {
                    estado = "afuera"
                }
                break
			case "afuera":
				// Ocultar toda la formación
				cabinaDelantera.hideTurtle()
				cabinaTrasera.hideTurtle()
				vagones.each { it.hideTurtle() 
					it.bajarPasajeros() // Bajar pasajeros de todos los vagones
				}
				if(++tiempoEsperando == frecuencia) {	
					setxy(posInicial, 50)
					reposicionarFormacion()
					
					// Mostrar toda la formación
					showTurtle()
					cabinaDelantera.showTurtle()
					cabinaTrasera.showTurtle()
					vagones.each { it.showTurtle() }		
					estado = "entrando"
					tiempoEsperando = 0
				}
				break
		}
		// Actualizar posición de toda la formación en cada tick
		if (estado != "afuera") {
			reposicionarFormacion()
		}
		
    }
	
	def createFormacion() {
		// Crear el vagón asociado a este subte
		def me = this
		//showTurtle()  // Hacer visible el subte principal
		
		// Crear cabina DELANTERA (FRONT) - va PRIMERA en la dirección del movimiento
		cabinaDelantera = hatchCabinas(1){
			setSubte(me)
			setDesplazamiento(-longSubte)
			setXcor(me.getXcor())
			setYcor(me.getYcor() - longSubte)
			setSize(longSubte)
			setHeading(0)
			setLabel("FRONT")
		}.first()
		
		// Crear VAGONES - entre la cabina delantera y trasera
		def capacidadPorVagon = (int)(capacidad / numVagones)
		for (i in 0..<numVagones) {
			def desplazamiento = -longSubte * (i + 2) // Posiciones negativas (hacia abajo)
			def vagon = hatchVagones(1) {
				// Asignar forma y color según la línea
				setSubtePadre(me)
				setLinea(me.linea)
				setXcor(me.getXcor())
				setYcor(me.getYcor() + desplazamiento)
				setSize(longSubte)
				setCapacidad(capacidadPorVagon)
				setDesplazamiento(desplazamiento)  // Para seguimiento
			}.first()
			vagones.add(vagon)
		}
		
		// Crear cabina TRASERA (BACK) - va ÚLTIMA
		def desplazamientoTrasero = -longSubte * (numVagones + 2)
		cabinaTrasera = hatchCabinas(1){
			setSubte(me)
			setDesplazamiento(desplazamientoTrasero)
			setXcor(me.getXcor())
			setYcor(me.getYcor() + desplazamientoTrasero)
			setSize(longSubte)
			setHeading(0)
			setLabel("BACK")
		}.first()
		
		if(linea == "F") {
			vagones.each { it.setShape("VagonRojo") }
			cabinaDelantera.setShape("SubteRojo")
			cabinaTrasera.setShape("SubteRojo")
		}
		if(linea == "C") {
			vagones.each { it.setShape("VagonAzul")}
			cabinaDelantera.setShape("SubteAzul")
			cabinaTrasera.setShape("SubteAzul")
		}
	}
	
	def hayLugarEnTren() {
		return vagones.any { it.hayLugar() }
	}
	
	def reposicionarFormacion() {
		// Reposicionar cabina delantera
        cabinaDelantera.setXcor(getXcor())
        cabinaDelantera.setYcor(getYcor() + cabinaDelantera.desplazamiento)
        
        // Reposicionar vagones
        vagones.each { vagon ->
            vagon.setXcor(getXcor())
            vagon.setYcor(getYcor() + vagon.desplazamiento)
        }
        
        // Reposicionar cabina trasera
        cabinaTrasera.setXcor(getXcor())
        cabinaTrasera.setYcor(getYcor() + cabinaTrasera.desplazamiento)
	}
	
	def detectarEstacionCercana() {
		 // Detectar cuando cualquier parte del tren esté cerca de la estación
        def trenEnEstacion = false
        
        // Verificar cabina delantera
        if (Math.abs(cabinaDelantera.getYcor() - estacion.getYcor()) < 2) {
            trenEnEstacion = true
        }
        
        // Verificar cabina trasera  
        if (Math.abs(cabinaTrasera.getYcor() - estacion.getYcor()) < 2) {
            trenEnEstacion = true
        }
        
        // Verificar vagones
        vagones.each { vagon ->
            if (Math.abs(vagon.getYcor() - estacion.getYcor()) < 2) {
                trenEnEstacion = true
            }
        }
        
        return trenEnEstacion
	}
	
}