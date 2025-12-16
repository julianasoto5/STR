package subtes.relogo

import repast.simphony.relogo.factories.AbstractReLogoGlobalsAndPanelFactory

public class UserGlobalsAndPanelFactory extends AbstractReLogoGlobalsAndPanelFactory{
	
	def tipo_dias = ["laboral", "sabado", "domingo"]
	def capacidadC, tipo_dia, capacidadF, frecuenciaC, frecuenciaF, porcentaje_a_F, nuevos_usuarios, andenC_ocupacion, andenF_ocupacion, transferidos, andenC_total, andenF_total
	public void addGlobalsAndPanelComponents(){

        // Entradas configurables
        addSliderWL("capacidadC", "Capacidad subte C", 100, 50, 1500, 650)
        addSliderWL("capacidadF", "Capacidad subte F", 100, 50, 1500, 650)
        addSliderWL("frecuenciaC", "Frecuencia llegada C (ticks)", 200, 1, 3600, 800)
        addSliderWL("frecuenciaF", "Frecuencia llegada F (ticks)", 200, 1, 3600, 800)
        addSliderWL("porcentaje_a_F", "% de pasajeros que pasan a F", 0, 0, 100, 0)
        addSliderWL("nuevos_usuarios", "Nuevos usuarios F", 0, 0, 10000, 4500)
		
		//Elegir dia
		addChooserWL("tipo_dia", "Tipo de día",tipo_dias, 0)
		
		// Monitores de salida
		addMonitorWL("andenC_ocupacion", "Personas en andén C", 5)
		addMonitorWL("andenF_ocupacion", "Personas en andén F", 5)
		addMonitorWL("get_max_anden_C", "Max personas en andén C", 5)
		addMonitorWL("get_max_anden_F", "Max personas en andén F", 5)
		addMonitorWL("andenC_total", "Total pasajeros C", 5)
		addMonitorWL("andenF_total", "Total pasajeros F", 5)
		addMonitorWL("get_tiempo_promedio_C", "Tiempo promedio de espera línea C", 5)
		addMonitorWL("get_tiempo_promedio_F", "Tiempo promedio de espera línea F", 5)
		
	}
}