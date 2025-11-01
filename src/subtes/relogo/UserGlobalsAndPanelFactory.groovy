package subtes.relogo

import repast.simphony.relogo.factories.AbstractReLogoGlobalsAndPanelFactory

public class UserGlobalsAndPanelFactory extends AbstractReLogoGlobalsAndPanelFactory{
	
	def tipo_dias = ["laboral", "sabado", "domingo", "laboral_partido", "sabado_partido", "domingo_partido"]
	def capacidadC, tipo_dia, capacidadF, frecuenciaC, frecuenciaF, porcentaje_a_F, nuevos_usuarios, andenC_ocupacion, andenF_ocupacion, transferidos, andenC_total, andenF_total
	public void addGlobalsAndPanelComponents(){

        // Entradas configurables
        addSliderWL("capacidadC", "Capacidad subte C", 100, 50, 500, 300)
        addSliderWL("capacidadF", "Capacidad subte F", 100, 50, 500, 300)
        addSliderWL("frecuenciaC", "Frecuencia llegada C (ticks)", 10, 1, 10000, 200)
        addSliderWL("frecuenciaF", "Frecuencia llegada F (ticks)", 10, 1, 10000, 200)
        addSliderWL("porcentaje_a_F", "% de pasajeros que pasan a F", 0, 0, 100, 0)
        addSliderWL("nuevos_usuarios", "Nuevos usuarios F", 0, 0, 500, 100)
		
		//Elegir dia
		addChooserWL("tipo_dia", "Tipo de día",tipo_dias, 0)
		
		// Monitores de salida
		addMonitorWL("andenC_ocupacion", "Personas en andén C", 5)
		addMonitorWL("andenF_ocupacion", "Personas en andén F", 5)
		addMonitorWL("andenC_total", "Total pasajeros C", 5)
		addMonitorWL("andenF_total", "Total pasajeros F", 5)
		addMonitorWL("get_tiempo_promedio_C", "Tiempo promedio de espera línea C", 5)
		addMonitorWL("get_tiempo_promedio_F", "Tiempo promedio de espera línea F", 5)
		
		//addMonitorWL("transferidos", "Pasajeros transferidos a F", 5)
	}
}