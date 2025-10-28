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
    def velocidad = 0.05
    Vagon vagon  // referencia al vagón asociado
	Cabina cabinaDelantera
	Cabina cabinaTrasera
	def frecuencia 
	def posInicial
	def tiempoEnEstacion = 0
	def tiempoEsperando = 0
	Estacion estacion
	def Ymin = -16.0
	
	def capacidad
	
    
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
				//se podria hacer mas de una vez por tick
				if(vagon.hayLugar() && !estacion.fila.empty)
					vagon.subirPasajero(estacion.getPasajeroEnFila())
				if(++tiempoEnEstacion >= 5000 || !vagon.hayLugar()) {
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
				cabinaDelantera.hideTurtle()
				cabinaTrasera.hideTurtle()
				vagon.hideTurtle()
				if(++tiempoEsperando == frecuencia) {	
					
					setxy(posInicial, 15)
					cabinaDelantera.setXcor(posInicial+cabinaDelantera.desplazamiento)
					cabinaTrasera.setXcor(posInicial+cabinaTrasera.desplazamiento)
					vagon.setXcor(posInicial)
					cabinaDelantera.showTurtle()
					cabinaTrasera.showTurtle()
					vagon.showTurtle()
					showTurtle()
					estado = "entrando"
					tiempoEsperando = 0
				}
				break
		}
		
    }
	
	def createFormacion() {
		// Crear el vagón asociado a este subte
		def me = this
		cabinaDelantera = hatchCabinas(1){
			setSubte(me)
			setDesplazamiento(-5)
			setXcor(me.getXcor())
			setYcor(me.getYcor()-5)
			setColor(me.getColor())
			setSize(5)
			setHeading(me.getHeading())
			setLabel("FRONT")
			
		}.first()
		
		cabinaTrasera = hatchCabinas(1){
			setSubte(me)
			setDesplazamiento(5)
			setXcor(me.getXcor())
			setYcor(me.getYcor()+5)
			setColor(me.getColor())
			setSize(5)
			setHeading(me.getHeading()+45)
			setLabel("BACK")
		}.first()
		
		vagon = hatchVagones(1) {
			setSubtePadre(me)
			setLinea(me.linea)
			setXcor(me.getXcor())
			setYcor(me.getYcor())
			setColor(pink())
			setSize(5)
			setCapacidad(capacidad)
		}.first()
		
		if(linea == "F") {
			vagon.setShape("vagonrojo")
			cabinaDelantera.setShape("subterojo")
			cabinaTrasera.setShape("subterojo")
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