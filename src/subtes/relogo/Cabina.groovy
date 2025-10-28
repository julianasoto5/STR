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

class Cabina extends ReLogoTurtle {
	Subte subte
	def desplazamiento
	def seguirSubte() {
		if (subte) {
			setXcor(subte.getXcor())
			setYcor(subte.getYcor()+desplazamiento)
		}
	}

}
