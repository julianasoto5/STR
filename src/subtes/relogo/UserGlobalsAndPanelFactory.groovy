package subtes.relogo

import repast.simphony.relogo.factories.AbstractReLogoGlobalsAndPanelFactory

public class UserGlobalsAndPanelFactory extends AbstractReLogoGlobalsAndPanelFactory{
	
	def tipo_dias = ["laboral", "sabado", "domingo", "laboral_partido", "sabado_partido", "domingo_partido"]
	def capacidadC, capacidadF, frecuenciaC, frecuenciaF, porcentaje_a_F, nuevos_usuarios, andenC_ocupacion, andenF_ocupacion, transferidos
	public void addGlobalsAndPanelComponents(){

        // Entradas configurables
        addSliderWL("capacidadC", "Capacidad subte C", 100, 50, 500, 300)
        addSliderWL("capacidadF", "Capacidad subte F", 100, 50, 500, 300)
        addSliderWL("frecuenciaC", "Frecuencia llegada C (ticks)", 10, 1, 60, 20)
        addSliderWL("frecuenciaF", "Frecuencia llegada F (ticks)", 10, 1, 60, 20)
        addSliderWL("porcentaje_a_F", "% de pasajeros que pasan a F", 0, 0, 100, 0)
        addSliderWL("nuevos_usuarios", "Nuevos usuarios F", 0, 0, 500, 100)
		
		//Elegir dia
		addChooserWL("tipo_dia", "Tipo de día",tipo_dias, 0)
		
		// Monitores de salida
		addMonitorWL("andenC_ocupacion", "Personas en andén C", 5)
		//addMonitorWL("andenF_ocupacion", "Personas en andén F", 5)
		//addMonitorWL("transferidos", "Pasajeros transferidos a F", 5)
	}
}