package subtes.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import subtes.ReLogoObserver;

class UserObserver extends ReLogoObserver{

	 @Setup
    def setup() {
        clearAll()

	    // Pintamos los patches
	    ask(patches()) {
	        if (pxcor > 5) {
	            setPcolor(blue())   // Andén C
	        } 
	        else if ((pxcor < 0)&&(pxcor > -11)) {
	            setPcolor(green())  // Andén F
	        }
	        else {
	            setPcolor(black())  
	        }
	    }

		setDefaultShape(Pasajero, "person")
        // Crear pasajeros de la línea C (andén azul)
        createPasajeros(15) {
            linea = "C"
            setColor(white())
            setxy(randomXcor(), random(4) + 6)
        }
		
		// Crear pasajeros de la línea F (andén verde)
		createPasajeros(15) {
			linea = "F"
			setColor(yellow())
			setxy(randomXcor(), random(4) - 10)
		}
		
		setDefaultShape(Estacion, "flag")
		
		 def estacionC = createEstaciones(1){
			linea = "C"
			setxy(9, 0)
			setSize(4)
			setColor(red())
		}.first()
		
		def estacionF = createEstaciones(1){
			linea = "F"
			setxy(-8, 0)
			setSize(4)
			setColor(yellow())
		}.first()
		
		setDefaultShape(Subte, "truck")
		
		def subteC = createSubtes(1){
			linea = "C"
			estado = "entrando"
			posInicial = 2.5
			frecuencia = 3000
			estacion = estacionC
			setColor(blue())
			setxy(posInicial, 15)
			setSize(5)
			setHeading(180)
		}.first()  // ← .first() para obtener el Subte individual
		
		def subteF = createSubtes(1){
			linea = "F"
			estado = "entrando"
			posInicial = -13.5
			frecuencia = 5000
			estacion = estacionF
			setColor(green())
			setxy(posInicial, 15)
			setSize(5)
			setHeading(180)
		}.first()  // ← .first() para obtener el Subte individual
		
		setupVagon(subteC)  // Ahora son Subte individuales
		setupVagon(subteF)

		
		

	 }
    

    @Go
    def go() {
		
		ask(pasajeros()) {
			mover()
		}
		
		ask(subtes()) {
			mover()
		}
		
		ask(vagones()){
			seguirSubte()
		}

    }
	
	def setupVagon(Subte subte) {
		// Crear el vagón asociado a este subte
		subte.vagon = createVagones(1) {
			setSubtePadre(subte)
			setLinea(subte.linea)
			setXcor(subte.getXcor())
			setYcor(subte.getYcor()+5)
			setShape("square")
			setColor(pink())
			setSize(3.5)
		}.first()
	}
	
	//addMonitorWL("andenC_ocupacion", "Personas en andén C", 5)
	//addMonitorWL("andenF_ocupacion", "Personas en andén F", 5)
	//addMonitorWL("transferidos", "Pasajeros transferidos a F", 5)
	def andenC_ocupacion() {
        def pasajerosAndenC = pasajeros().count{ p -> 
            p.linea == "C" && !p.enVagon
        }
        return pasajerosAndenC
    }

}