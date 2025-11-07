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
	def limAnden = 24
	
	def TIEMPO_TOTAL_SIMULACION = 10000
	
	def PASAJEROS_TOTAL_C 
	def PASAJEROS_TOTAL_F
	
	def frecuencia_hatch_C = 0.0
	def frecuencia_hatch_F = 0.0
	
	def ticks = 0
	
	def TIPO_DE_DIA = "domingo_partido"
	def tipo_dia
	def PORCENTAJE_A_F = 30           // 30% del total base usa F
	def NUEVOS_USUARIOS_F = 0
	def base_total
	def estacionF, estacionC
	def capacidadC, capacidadF,frecuenciaC, frecuenciaF, porcentaje_a_F, nuevos_usuarios, andenC_ocupacion, andenF_ocupacion, transferidos
	
	def numVagones = 3  // Número de vagones
	
	Subte subteC = null, subteF = null
	 @Setup
    def setup() {
        clearAll()
		
		setupFondo()
		
	
		base_total = getTotalPasajerosBase(tipo_dia)
		PASAJEROS_TOTAL_C = (int) (base_total * (1 - PORCENTAJE_A_F / 100.0))
		PASAJEROS_TOTAL_F = (int) (base_total * (PORCENTAJE_A_F / 100.0) + NUEVOS_USUARIOS_F)
		
		if (PASAJEROS_TOTAL_C > 0) {
			frecuencia_hatch_C = TIEMPO_TOTAL_SIMULACION / PASAJEROS_TOTAL_C
		} else {
			frecuencia_hatch_C = TIEMPO_TOTAL_SIMULACION // Evita división por cero
		}

		if (PASAJEROS_TOTAL_F > 0) {
			frecuencia_hatch_F = TIEMPO_TOTAL_SIMULACION / PASAJEROS_TOTAL_F
		} else {
			frecuencia_hatch_F = TIEMPO_TOTAL_SIMULACION
		}
		
		setDefaultShape(Pasajero, "person")
        setDefaultShape(Estacion, "PuertaAzul")
		setDefaultShape(Subte, "default")
		setDefaultShape(Cabina, "SubteAzul")
		setDefaultShape(Vagon, "VagonAzul")
		
		createInicial()

	 }
	 
	 def createInicial() {
		 
		 estacionC = createEstaciones(1){
			 linea = "C"
			 xMin = limAnden
			 setxy(limAnden-1, 0)
			 setSize(5)
		 }.first()
		 
		 estacionF = createEstaciones(1){
			 setShape("PuertaRoja")
			 linea = "F"
			 xMin = -limAnden
			 setxy(-limAnden+1, 0)
			 setSize(5)
		 }.first()
		 
		 // Subte C - Comienza desde ARRIBA (Y positivo)
		 subteC = createSubtes(1){
			 hideTurtle()
			 linea = "C"
			 estado = "entrando"
			 posInicial = limAnden+4
			 frecuencia = frecuenciaC
			 estacion = estacionC
			 capacidad = capacidadC
			 numVagones = numVagones  // Pasar el número de vagones
			 setColor(blue())
			 setxy(posInicial, 50)
			 setSize(10)
			 setHeading(0) // Mirando hacia abajo
		 }.first()  // ← .first() para obtener el Subte individual
		 
		 subteF = createSubtes(1){
			 hideTurtle()
			 linea = "F"
			 estado = "afuera"
			 posInicial = -limAnden-4
			 frecuencia = frecuenciaF
			 estacion = estacionF
			 capacidad = capacidadF
			 numVagones = numVagones  // Pasar el número de vagones
			 setColor(red())
			 setxy(posInicial, 50)
			 setSize(10)
			 setHeading(0)
		 }.first()// ← .first() para obtener el Subte individual
		
		 // Crear pasajeros de la línea C (andén azul)
		 createPasajeros(10) {
			 estacion = estacionC
			 setSize(2.5)
			 setColor(blue())
			 setxy(random(limAnden-2)-1, randomYcor())
		 }
		 
		 // Crear pasajeros de la línea F (andén verde)
		 createPasajeros(10) {
			 estacion = estacionF
			 setSize(2.5)
			 setColor(red())
			 setxy(random(-limAnden+2)+1, randomYcor())
		 }
		 		 
		 subteF.createFormacion()
		 subteC.createFormacion()
		
	 }
	 
	 // Fondo con patches
	 def setupFondo() {
		 ask(patches()){
			 setPcolor(gray()+2) //Fondo
			 if ((pxcor == -22 && pxcor == -21) || (pxcor == 22 && pxcor == 21)) { setPcolor(yellow()) } //Líneas de seguridad
			 if (pxcor == 0) { setPcolor(black()) } //Separador
			 
			 //Anden Subte F (-24 a -32)
			 if (pxcor <= -limAnden) {
				 if (pycor < -30) setPcolor(gray()-3) //Fin del anden
				 else {
					 if ((pxcor == -26) || (pxcor == -30)) setPcolor(gray()-2)	//Rieles
					 else if(pxcor <= -25 && pxcor >= -31 && pycor % 2 == 0) setPcolor(brown()+1) //Tablas
						 else setPcolor(brown()-2)//Tierra
				 }
			 }
			 
			 //Anden Subte C (24 a 32)
			 if (pxcor >= limAnden) {
				 if (pycor < -30) setPcolor(gray()-3) //Fin del anden
				 else {
					 if ((pxcor == 26) || (pxcor == 30)) setPcolor(gray()-2)	//Rieles
					 else if(pxcor >= 25 && pxcor <= 31 && pycor % 2 == 0) setPcolor(brown()+1) //Tablas
							 else setPcolor(brown()-2)//Tierra
				 }
			 }
		 }
	 }
    

    @Go
    def go() {
		
		ticks += 1
		if (frecuencia_hatch_C > 0 && (ticks % (int)frecuencia_hatch_C == 0)) {
			crearPasajero("C")
		}
		
		if (frecuencia_hatch_F > 0 && (ticks % (int)frecuencia_hatch_F == 0)) {
			crearPasajero("F")
		}
		ask(pasajeros()) {
			mover()
		}
		
		ask(subtes()) {
			mover()
		}
		
		ask(vagones()){
			seguirSubte()
		}
		
		ask(cabinas()){
			seguirSubte()
		}

    }

	def getTotalPasajerosBase(String tipoDia) {
		switch (tipoDia) {
			case "laboral":
				return 100
			case "sabado":
				return 60
			case "domingo":
				return 40
			
			case "laboral_partido":
				return 150
			case "sabado_partido":
				return 120
			case "domingo_partido":
				return 80
			default:
				return 50
		}
	}
	
	def crearPasajero(String lineaDestino) {
		def xRange = (lineaDestino == "C") ? [1, 22] : [-1, -22]
		def colorPasajero = (lineaDestino == "C") ? blue() : red()
		
		createPasajeros(1) {
			linea = lineaDestino
			estacion = (linea == "C") ? estacionC : estacionF
			setColor(colorPasajero)
			setxy(random(xRange[1] - xRange[0]) + xRange[0], randomYcor())
			setSize(2)
		}
	}
	
	def andenC_ocupacion() {
        def pasajerosAndenC = pasajeros().count{ Pasajero p -> 
            p.linea == "C" && !p.enVagon
        }
        return pasajerosAndenC
    }
	
	def andenF_ocupacion() {
		def pasajerosAndenF = pasajeros().count(){Pasajero p->
			p.linea == "F" && !p.enVagon
		}
		return pasajerosAndenF
	}
	
	

}