package subtes.relogo

import static repast.simphony.relogo.Utility.*
import static repast.simphony.relogo.UtilityG.*
import repast.simphony.engine.environment.RunEnvironment;

import repast.simphony.relogo.Plural
import repast.simphony.relogo.Stop
import repast.simphony.relogo.Utility
import repast.simphony.relogo.UtilityG
import repast.simphony.relogo.schedule.Go
import repast.simphony.relogo.schedule.Setup
import subtes.ReLogoTurtle

class Pasajero extends ReLogoTurtle {
    Estacion estacion
	def linea
    def velocidad = 0.01
    def enVagon = false
    def miVagon = null
	def enEstacion = false
	def estado = "enEstacion"
	def tick_inicio
    
	
    def subirA(Vagon v) {
        if (!enVagon) {
            enVagon = true
            miVagon = v
            setColor(violet())
			
        }
    }
    
    def bajar() {
		die()
		//estado = "reborn"
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
    /*
    def mover() {
        
		if (!enVagon) {
            // Comportamiento normal buscando subte
            rt(random(20) - 10)
            fd(velocidad)
            
            // Mantener en área del andén
            if (estacion.linea == "C") {
                if (getYcor() < 6) setYcor(6)
                if (getYcor() > 15) setYcor(14)
            }
        }else seguirVagon()
    }
	
	*/
	
	def mover() {
		//double currentTick = RunEnvironment.getInstance().getCurrentSchedule().getTickCount();
		//if(tick_inicio < currentTick) return
		//println("Estado: "+ estado)
		switch (estado) {
			case "enEstacion":
				//println("moviendose")
				//buscarEstacion()
				rt(random(20) - 10) //rota
				fd(velocidad) //avanza
				quedarseEnAnden()
				if(Math.abs(getYcor() - estacion.getYcor()) < 5){
					estado = "haciendoFila"
					estacion.hacerFila(this)
					setColor(orange())
				}
				break
			case "haciendoFila": //no hace nada
				
				break
			case "enVagon":
				seguirVagon()
				setColor(pink())
				break
			case "bajarVagon": 
				bajar();
				break;
		}
	}
	
	def seguirVagon() {
		setXcor(miVagon.getXcor())
		setYcor(miVagon.getYcor())
	}
	
	def buscarEstacion() {
		if(random(10)<2)
			setHeading(random(360)) //cambia de direccion 
		
		fd(velocidad)
		
		if(!estacion.enEstacion(getXcor()))
			 setHeading(180-getHeading())
	}
	
	def quedarseEnAnden() {
        
        // Obtiene la coordenada X actual del pasajero
        def posX = pxcor

        // --- Lógica para la Línea F ---
        // El Andén F está entre pxcor -11 y 0 (es decir, -10 a -1)
        if (linea == "F") {
            
            // Si el pasajero está FUERA de los límites (pxcor >= 0 O pxcor <= -11)
            if (posX >= 0 || posX <= -11) {
                
                // Gira 180 grados (da media vuelta)
                rt(180) 
                
                // Avanza para volver a entrar al andén
                fd(1) 
            }
        }
        
        // --- Lógica para la Línea C ---
        // El Andén C está en pxcor > 5 (es decir, de 6 en adelante)
        else if (linea == "C") {
            
            // Si el pasajero está FUERA de los límites (pxcor <= 5)
            if (posX <= 5) {
                
                // Gira 180 grados
                rt(180)
                
                // Avanza para volver
                fd(1)
            }
        }
    }
}