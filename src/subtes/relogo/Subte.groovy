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
    Vagon vagon  
	Cabina cabinaDelantera
	Cabina cabinaTrasera
	def frecuencia 
	def posInicial
	def tiempoEnEstacion = 0
	def tiempoEsperando = 0
	def tiempoEspera = 500
	def Ymin = -32
	def Ymax = 32
	
	def capacidad
	
    
    def mover() {
		
		switch(estado) {
			case "entrando":
				fd (velocidad) 
				
				if(getYcor() <= 0) {
					estado = "enEstacion"
					tiempoEnEstacion = 0
				}
				break
				
			case "enEstacion":
					
				if(++tiempoEnEstacion >= tiempoEspera) {
					vagon.subirPasajerosEnEstacion()
					estado = "saliendo"
					tiempoEnEstacion = 0
					setHeading(0)
				}				
				break
				
			case "saliendo":
				fd(velocidad)
				
				if(getYcor() >= Ymax-10) {
					cabinaDelantera.hideTurtle()
					cabinaTrasera.hideTurtle()
					vagon.hideTurtle()
					hideTurtle()
					estado = "afuera" 
					tiempoEsperando = 0
				}
				break
				
			case "afuera":
			
				vagon.bajarPasajeros()
			
				if(++tiempoEsperando == frecuencia) {	
					
					setxy(posInicial, Ymax-10)
					cabinaDelantera.setxy(posInicial + cabinaDelantera.desplazamiento, Ymax-10) 
                    cabinaTrasera.setxy(posInicial + cabinaTrasera.desplazamiento, Ymax-10) 
                    vagon.setxy(posInicial, Ymax-10)
					
					setHeading(180)
					
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
		def me = this
		cabinaDelantera = hatchCabinas(1){
			setSubte(me)
			setDesplazamiento(-10)
			setXcor(me.getXcor())
			setYcor(me.getYcor()-10)
			setColor(me.getColor())
			setSize(10)
			setHeading(me.getHeading())
			setLabel("FRONT")
			
		}.first()
		
		cabinaTrasera = hatchCabinas(1){
			setSubte(me)
			setDesplazamiento(10)
			setXcor(me.getXcor())
			setYcor(me.getYcor()+10)
			setColor(me.getColor())
			setSize(10)
			setHeading(me.getHeading())
			setLabel("BACK")
		}.first()
		
		vagon = hatchVagones(1) {
			setSubtePadre(me)
			setLinea(me.linea)
			setXcor(me.getXcor())
			setYcor(me.getYcor())
			setColor(pink())
			setSize(10)
			setCapacidad(capacidad)
		}.first()
		
		if(linea == "F") {
			vagon.setShape("vagonrojo")
			cabinaDelantera.setShape("subterojo")
			cabinaTrasera.setShape("subterojo")
		}
	}
	
}