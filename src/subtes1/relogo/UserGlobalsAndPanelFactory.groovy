package subtes1.relogo

import repast.simphony.relogo.factories.AbstractReLogoGlobalsAndPanelFactory

public class UserGlobalsAndPanelFactory extends AbstractReLogoGlobalsAndPanelFactory{
	
	def tipo_dia = ["laboral", "sabado", "domingo", "laboral_partido", "sabado_partido", "domingo_partido"]
	def capacidadC, capacidadF, frecuenciaC, frecuenciaF, porcentaje_a_F, nuevos_usuarios, total_pasajeros_C, total_pasajeros_F, tiempo_promedio_C, tiempo_promedio_F
	
	public void addGlobalsAndPanelComponents(){

        // Entradas configurables
        addSliderWL("capacidadC", "Capacidad subte C", 100, 50, 500, 300)
        addSliderWL("capacidadF", "Capacidad subte F", 100, 50, 500, 300)
        addSliderWL("frecuenciaC", "Frecuencia llegada C (ticks)", 10, 1, 60, 20)
        addSliderWL("frecuenciaF", "Frecuencia llegada F (ticks)", 10, 1, 60, 20)
        addSliderWL("porcentaje_a_F", "% de pasajeros que pasan a F", 0, 0, 100, 0)
        addSliderWL("nuevos_usuarios", "Nuevos usuarios F", 0, 0, 500, 100)
		
		//Elegir dia
		addChooserWL("tipo_dia", "Tipo de día", tipo_dia, "laboral")
		
		// Monitores de salida
		addMonitorWL("total_pasajeros_C", "Total pasajeros línea C", 5)
		addMonitorWL("total_pasajeros_F", "Total pasajeros línea F", 5)
		addMonitorWL("tiempo_promedio_C", "Tiempo promedio de espera línea C", 5)
		addMonitorWL("tiempo_promedio_F", "Tiempo promedio de espera línea F", 5)
	}
}