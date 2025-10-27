package subtes;

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import groovy.lang.Closure;
import repast.simphony.relogo.*;
import repast.simphony.relogo.ast.Diffusible;
import repast.simphony.relogo.builder.GeneratedByReLogoBuilder;
import repast.simphony.relogo.builder.ReLogoBuilderGeneratedFor;
import repast.simphony.space.SpatialException;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

@GeneratedByReLogoBuilder
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ReLogoPatch extends BasePatch{

	/**
	 * Sprouts (makes) a number of new estaciones and then executes a set of commands on the
	 * created estaciones.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created estaciones
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Estacion")
	public AgentSet<subtes.relogo.Estacion> sproutEstaciones(int number, Closure closure) {
		AgentSet<subtes.relogo.Estacion> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"Estacion");
		for (Turtle t : createResult){
			if (t instanceof subtes.relogo.Estacion){
				result.add((subtes.relogo.Estacion)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new estaciones and then executes a set of commands on the
	 * created estaciones.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created estaciones
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Estacion")
	public AgentSet<subtes.relogo.Estacion> sproutEstaciones(int number) {
		return sproutEstaciones(number,null);
	}

	/**
	 * Returns an agentset of estaciones from the patch of the caller.
	 * 
	 * @return agentset of estaciones from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Estacion")
	public AgentSet<subtes.relogo.Estacion> estacionesHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<subtes.relogo.Estacion> result = new AgentSet<subtes.relogo.Estacion>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"estacion")){
			if (t instanceof subtes.relogo.Estacion)
			result.add((subtes.relogo.Estacion)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of estaciones on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of estaciones at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Estacion")
	public AgentSet<subtes.relogo.Estacion> estacionesAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<subtes.relogo.Estacion> result = new AgentSet<subtes.relogo.Estacion>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"estacion")){
			if (t instanceof subtes.relogo.Estacion)
			result.add((subtes.relogo.Estacion)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<subtes.relogo.Estacion>();
		}
	}

	/**
	 * Returns an agentset of estaciones on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of estaciones on patch p
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Estacion")
	public AgentSet<subtes.relogo.Estacion> estacionesOn(Patch p){
		AgentSet<subtes.relogo.Estacion> result = new AgentSet<subtes.relogo.Estacion>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"estacion")){
			if (t instanceof subtes.relogo.Estacion)
			result.add((subtes.relogo.Estacion)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of estaciones on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of estaciones on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Estacion")
	public AgentSet<subtes.relogo.Estacion> estacionesOn(Turtle t){
		AgentSet<subtes.relogo.Estacion> result = new AgentSet<subtes.relogo.Estacion>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"estacion")){
			if (tt instanceof subtes.relogo.Estacion)
			result.add((subtes.relogo.Estacion)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of estaciones on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of estaciones on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Estacion")
	public AgentSet<subtes.relogo.Estacion> estacionesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<subtes.relogo.Estacion>();
		}

		Set<subtes.relogo.Estacion> total = new HashSet<subtes.relogo.Estacion>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(estacionesOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(estacionesOn(p));
				}
			}
		}
		return new AgentSet<subtes.relogo.Estacion>(total);
	}

	/**
	 * Queries if object is a estacion.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a estacion
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Estacion")
	public boolean isEstacionQ(Object o){
		return (o instanceof subtes.relogo.Estacion);
	}

	/**
	 * Returns an agentset containing all estaciones.
	 * 
	 * @return agentset of all estaciones
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Estacion")
	public AgentSet<subtes.relogo.Estacion> estaciones(){
		AgentSet<subtes.relogo.Estacion> a = new AgentSet<subtes.relogo.Estacion>();
		for (Object e : this.getMyObserver().getContext().getObjects(subtes.relogo.Estacion.class)) {
			if (e instanceof subtes.relogo.Estacion){
				a.add((subtes.relogo.Estacion)e);
			}
		}
		return a;
	}

	/**
	 * Returns the estacion with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Estacion")
	public subtes.relogo.Estacion estacion(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof subtes.relogo.Estacion)
			return (subtes.relogo.Estacion) turtle;
		return null;
	}

	/**
	 * Sprouts (makes) a number of new humans and then executes a set of commands on the
	 * created humans.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created humans
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Human")
	public AgentSet<subtes.relogo.Human> sproutHumans(int number, Closure closure) {
		AgentSet<subtes.relogo.Human> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"Human");
		for (Turtle t : createResult){
			if (t instanceof subtes.relogo.Human){
				result.add((subtes.relogo.Human)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new humans and then executes a set of commands on the
	 * created humans.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created humans
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Human")
	public AgentSet<subtes.relogo.Human> sproutHumans(int number) {
		return sproutHumans(number,null);
	}

	/**
	 * Returns an agentset of humans from the patch of the caller.
	 * 
	 * @return agentset of humans from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Human")
	public AgentSet<subtes.relogo.Human> humansHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<subtes.relogo.Human> result = new AgentSet<subtes.relogo.Human>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"human")){
			if (t instanceof subtes.relogo.Human)
			result.add((subtes.relogo.Human)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of humans on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of humans at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Human")
	public AgentSet<subtes.relogo.Human> humansAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<subtes.relogo.Human> result = new AgentSet<subtes.relogo.Human>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"human")){
			if (t instanceof subtes.relogo.Human)
			result.add((subtes.relogo.Human)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<subtes.relogo.Human>();
		}
	}

	/**
	 * Returns an agentset of humans on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of humans on patch p
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Human")
	public AgentSet<subtes.relogo.Human> humansOn(Patch p){
		AgentSet<subtes.relogo.Human> result = new AgentSet<subtes.relogo.Human>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"human")){
			if (t instanceof subtes.relogo.Human)
			result.add((subtes.relogo.Human)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of humans on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of humans on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Human")
	public AgentSet<subtes.relogo.Human> humansOn(Turtle t){
		AgentSet<subtes.relogo.Human> result = new AgentSet<subtes.relogo.Human>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"human")){
			if (tt instanceof subtes.relogo.Human)
			result.add((subtes.relogo.Human)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of humans on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of humans on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Human")
	public AgentSet<subtes.relogo.Human> humansOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<subtes.relogo.Human>();
		}

		Set<subtes.relogo.Human> total = new HashSet<subtes.relogo.Human>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(humansOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(humansOn(p));
				}
			}
		}
		return new AgentSet<subtes.relogo.Human>(total);
	}

	/**
	 * Queries if object is a human.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a human
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Human")
	public boolean isHumanQ(Object o){
		return (o instanceof subtes.relogo.Human);
	}

	/**
	 * Returns an agentset containing all humans.
	 * 
	 * @return agentset of all humans
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Human")
	public AgentSet<subtes.relogo.Human> humans(){
		AgentSet<subtes.relogo.Human> a = new AgentSet<subtes.relogo.Human>();
		for (Object e : this.getMyObserver().getContext().getObjects(subtes.relogo.Human.class)) {
			if (e instanceof subtes.relogo.Human){
				a.add((subtes.relogo.Human)e);
			}
		}
		return a;
	}

	/**
	 * Returns the human with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Human")
	public subtes.relogo.Human human(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof subtes.relogo.Human)
			return (subtes.relogo.Human) turtle;
		return null;
	}

	/**
	 * Sprouts (makes) a number of new pasajeros and then executes a set of commands on the
	 * created pasajeros.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created pasajeros
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Pasajero")
	public AgentSet<subtes.relogo.Pasajero> sproutPasajeros(int number, Closure closure) {
		AgentSet<subtes.relogo.Pasajero> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"Pasajero");
		for (Turtle t : createResult){
			if (t instanceof subtes.relogo.Pasajero){
				result.add((subtes.relogo.Pasajero)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new pasajeros and then executes a set of commands on the
	 * created pasajeros.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created pasajeros
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Pasajero")
	public AgentSet<subtes.relogo.Pasajero> sproutPasajeros(int number) {
		return sproutPasajeros(number,null);
	}

	/**
	 * Returns an agentset of pasajeros from the patch of the caller.
	 * 
	 * @return agentset of pasajeros from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Pasajero")
	public AgentSet<subtes.relogo.Pasajero> pasajerosHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<subtes.relogo.Pasajero> result = new AgentSet<subtes.relogo.Pasajero>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"pasajero")){
			if (t instanceof subtes.relogo.Pasajero)
			result.add((subtes.relogo.Pasajero)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of pasajeros on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of pasajeros at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Pasajero")
	public AgentSet<subtes.relogo.Pasajero> pasajerosAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<subtes.relogo.Pasajero> result = new AgentSet<subtes.relogo.Pasajero>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"pasajero")){
			if (t instanceof subtes.relogo.Pasajero)
			result.add((subtes.relogo.Pasajero)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<subtes.relogo.Pasajero>();
		}
	}

	/**
	 * Returns an agentset of pasajeros on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of pasajeros on patch p
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Pasajero")
	public AgentSet<subtes.relogo.Pasajero> pasajerosOn(Patch p){
		AgentSet<subtes.relogo.Pasajero> result = new AgentSet<subtes.relogo.Pasajero>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"pasajero")){
			if (t instanceof subtes.relogo.Pasajero)
			result.add((subtes.relogo.Pasajero)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of pasajeros on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of pasajeros on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Pasajero")
	public AgentSet<subtes.relogo.Pasajero> pasajerosOn(Turtle t){
		AgentSet<subtes.relogo.Pasajero> result = new AgentSet<subtes.relogo.Pasajero>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"pasajero")){
			if (tt instanceof subtes.relogo.Pasajero)
			result.add((subtes.relogo.Pasajero)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of pasajeros on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of pasajeros on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Pasajero")
	public AgentSet<subtes.relogo.Pasajero> pasajerosOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<subtes.relogo.Pasajero>();
		}

		Set<subtes.relogo.Pasajero> total = new HashSet<subtes.relogo.Pasajero>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(pasajerosOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(pasajerosOn(p));
				}
			}
		}
		return new AgentSet<subtes.relogo.Pasajero>(total);
	}

	/**
	 * Queries if object is a pasajero.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a pasajero
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Pasajero")
	public boolean isPasajeroQ(Object o){
		return (o instanceof subtes.relogo.Pasajero);
	}

	/**
	 * Returns an agentset containing all pasajeros.
	 * 
	 * @return agentset of all pasajeros
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Pasajero")
	public AgentSet<subtes.relogo.Pasajero> pasajeros(){
		AgentSet<subtes.relogo.Pasajero> a = new AgentSet<subtes.relogo.Pasajero>();
		for (Object e : this.getMyObserver().getContext().getObjects(subtes.relogo.Pasajero.class)) {
			if (e instanceof subtes.relogo.Pasajero){
				a.add((subtes.relogo.Pasajero)e);
			}
		}
		return a;
	}

	/**
	 * Returns the pasajero with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Pasajero")
	public subtes.relogo.Pasajero pasajero(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof subtes.relogo.Pasajero)
			return (subtes.relogo.Pasajero) turtle;
		return null;
	}

	/**
	 * Sprouts (makes) a number of new subtes and then executes a set of commands on the
	 * created subtes.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created subtes
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Subte")
	public AgentSet<subtes.relogo.Subte> sproutSubtes(int number, Closure closure) {
		AgentSet<subtes.relogo.Subte> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"Subte");
		for (Turtle t : createResult){
			if (t instanceof subtes.relogo.Subte){
				result.add((subtes.relogo.Subte)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new subtes and then executes a set of commands on the
	 * created subtes.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created subtes
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Subte")
	public AgentSet<subtes.relogo.Subte> sproutSubtes(int number) {
		return sproutSubtes(number,null);
	}

	/**
	 * Returns an agentset of subtes from the patch of the caller.
	 * 
	 * @return agentset of subtes from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Subte")
	public AgentSet<subtes.relogo.Subte> subtesHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<subtes.relogo.Subte> result = new AgentSet<subtes.relogo.Subte>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"subte")){
			if (t instanceof subtes.relogo.Subte)
			result.add((subtes.relogo.Subte)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of subtes on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of subtes at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Subte")
	public AgentSet<subtes.relogo.Subte> subtesAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<subtes.relogo.Subte> result = new AgentSet<subtes.relogo.Subte>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"subte")){
			if (t instanceof subtes.relogo.Subte)
			result.add((subtes.relogo.Subte)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<subtes.relogo.Subte>();
		}
	}

	/**
	 * Returns an agentset of subtes on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of subtes on patch p
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Subte")
	public AgentSet<subtes.relogo.Subte> subtesOn(Patch p){
		AgentSet<subtes.relogo.Subte> result = new AgentSet<subtes.relogo.Subte>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"subte")){
			if (t instanceof subtes.relogo.Subte)
			result.add((subtes.relogo.Subte)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of subtes on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of subtes on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Subte")
	public AgentSet<subtes.relogo.Subte> subtesOn(Turtle t){
		AgentSet<subtes.relogo.Subte> result = new AgentSet<subtes.relogo.Subte>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"subte")){
			if (tt instanceof subtes.relogo.Subte)
			result.add((subtes.relogo.Subte)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of subtes on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of subtes on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Subte")
	public AgentSet<subtes.relogo.Subte> subtesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<subtes.relogo.Subte>();
		}

		Set<subtes.relogo.Subte> total = new HashSet<subtes.relogo.Subte>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(subtesOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(subtesOn(p));
				}
			}
		}
		return new AgentSet<subtes.relogo.Subte>(total);
	}

	/**
	 * Queries if object is a subte.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a subte
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Subte")
	public boolean isSubteQ(Object o){
		return (o instanceof subtes.relogo.Subte);
	}

	/**
	 * Returns an agentset containing all subtes.
	 * 
	 * @return agentset of all subtes
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Subte")
	public AgentSet<subtes.relogo.Subte> subtes(){
		AgentSet<subtes.relogo.Subte> a = new AgentSet<subtes.relogo.Subte>();
		for (Object e : this.getMyObserver().getContext().getObjects(subtes.relogo.Subte.class)) {
			if (e instanceof subtes.relogo.Subte){
				a.add((subtes.relogo.Subte)e);
			}
		}
		return a;
	}

	/**
	 * Returns the subte with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Subte")
	public subtes.relogo.Subte subte(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof subtes.relogo.Subte)
			return (subtes.relogo.Subte) turtle;
		return null;
	}

	/**
	 * Sprouts (makes) a number of new userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserTurtle")
	public AgentSet<subtes.relogo.UserTurtle> sproutUserTurtles(int number, Closure closure) {
		AgentSet<subtes.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof subtes.relogo.UserTurtle){
				result.add((subtes.relogo.UserTurtle)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserTurtle")
	public AgentSet<subtes.relogo.UserTurtle> sproutUserTurtles(int number) {
		return sproutUserTurtles(number,null);
	}

	/**
	 * Returns an agentset of userTurtles from the patch of the caller.
	 * 
	 * @return agentset of userTurtles from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserTurtle")
	public AgentSet<subtes.relogo.UserTurtle> userTurtlesHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<subtes.relogo.UserTurtle> result = new AgentSet<subtes.relogo.UserTurtle>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"userTurtle")){
			if (t instanceof subtes.relogo.UserTurtle)
			result.add((subtes.relogo.UserTurtle)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of userTurtles on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of userTurtles at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserTurtle")
	public AgentSet<subtes.relogo.UserTurtle> userTurtlesAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<subtes.relogo.UserTurtle> result = new AgentSet<subtes.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"userTurtle")){
			if (t instanceof subtes.relogo.UserTurtle)
			result.add((subtes.relogo.UserTurtle)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<subtes.relogo.UserTurtle>();
		}
	}

	/**
	 * Returns an agentset of userTurtles on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of userTurtles on patch p
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserTurtle")
	public AgentSet<subtes.relogo.UserTurtle> userTurtlesOn(Patch p){
		AgentSet<subtes.relogo.UserTurtle> result = new AgentSet<subtes.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"userTurtle")){
			if (t instanceof subtes.relogo.UserTurtle)
			result.add((subtes.relogo.UserTurtle)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of userTurtles on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserTurtle")
	public AgentSet<subtes.relogo.UserTurtle> userTurtlesOn(Turtle t){
		AgentSet<subtes.relogo.UserTurtle> result = new AgentSet<subtes.relogo.UserTurtle>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"userTurtle")){
			if (tt instanceof subtes.relogo.UserTurtle)
			result.add((subtes.relogo.UserTurtle)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of userTurtles on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserTurtle")
	public AgentSet<subtes.relogo.UserTurtle> userTurtlesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<subtes.relogo.UserTurtle>();
		}

		Set<subtes.relogo.UserTurtle> total = new HashSet<subtes.relogo.UserTurtle>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(userTurtlesOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(userTurtlesOn(p));
				}
			}
		}
		return new AgentSet<subtes.relogo.UserTurtle>(total);
	}

	/**
	 * Queries if object is a userTurtle.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userTurtle
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserTurtle")
	public boolean isUserTurtleQ(Object o){
		return (o instanceof subtes.relogo.UserTurtle);
	}

	/**
	 * Returns an agentset containing all userTurtles.
	 * 
	 * @return agentset of all userTurtles
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserTurtle")
	public AgentSet<subtes.relogo.UserTurtle> userTurtles(){
		AgentSet<subtes.relogo.UserTurtle> a = new AgentSet<subtes.relogo.UserTurtle>();
		for (Object e : this.getMyObserver().getContext().getObjects(subtes.relogo.UserTurtle.class)) {
			if (e instanceof subtes.relogo.UserTurtle){
				a.add((subtes.relogo.UserTurtle)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userTurtle with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserTurtle")
	public subtes.relogo.UserTurtle userTurtle(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof subtes.relogo.UserTurtle)
			return (subtes.relogo.UserTurtle) turtle;
		return null;
	}

	/**
	 * Sprouts (makes) a number of new vagones and then executes a set of commands on the
	 * created vagones.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created vagones
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Vagon")
	public AgentSet<subtes.relogo.Vagon> sproutVagones(int number, Closure closure) {
		AgentSet<subtes.relogo.Vagon> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"Vagon");
		for (Turtle t : createResult){
			if (t instanceof subtes.relogo.Vagon){
				result.add((subtes.relogo.Vagon)t);
			}
		} 
		return result;
	}

	/**
	 * Sprouts (makes) a number of new vagones and then executes a set of commands on the
	 * created vagones.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created vagones
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Vagon")
	public AgentSet<subtes.relogo.Vagon> sproutVagones(int number) {
		return sproutVagones(number,null);
	}

	/**
	 * Returns an agentset of vagones from the patch of the caller.
	 * 
	 * @return agentset of vagones from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Vagon")
	public AgentSet<subtes.relogo.Vagon> vagonesHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<subtes.relogo.Vagon> result = new AgentSet<subtes.relogo.Vagon>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"vagon")){
			if (t instanceof subtes.relogo.Vagon)
			result.add((subtes.relogo.Vagon)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of vagones on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of vagones at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Vagon")
	public AgentSet<subtes.relogo.Vagon> vagonesAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<subtes.relogo.Vagon> result = new AgentSet<subtes.relogo.Vagon>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"vagon")){
			if (t instanceof subtes.relogo.Vagon)
			result.add((subtes.relogo.Vagon)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<subtes.relogo.Vagon>();
		}
	}

	/**
	 * Returns an agentset of vagones on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of vagones on patch p
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Vagon")
	public AgentSet<subtes.relogo.Vagon> vagonesOn(Patch p){
		AgentSet<subtes.relogo.Vagon> result = new AgentSet<subtes.relogo.Vagon>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"vagon")){
			if (t instanceof subtes.relogo.Vagon)
			result.add((subtes.relogo.Vagon)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of vagones on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of vagones on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Vagon")
	public AgentSet<subtes.relogo.Vagon> vagonesOn(Turtle t){
		AgentSet<subtes.relogo.Vagon> result = new AgentSet<subtes.relogo.Vagon>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"vagon")){
			if (tt instanceof subtes.relogo.Vagon)
			result.add((subtes.relogo.Vagon)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of vagones on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of vagones on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Vagon")
	public AgentSet<subtes.relogo.Vagon> vagonesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<subtes.relogo.Vagon>();
		}

		Set<subtes.relogo.Vagon> total = new HashSet<subtes.relogo.Vagon>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(vagonesOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(vagonesOn(p));
				}
			}
		}
		return new AgentSet<subtes.relogo.Vagon>(total);
	}

	/**
	 * Queries if object is a vagon.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a vagon
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Vagon")
	public boolean isVagonQ(Object o){
		return (o instanceof subtes.relogo.Vagon);
	}

	/**
	 * Returns an agentset containing all vagones.
	 * 
	 * @return agentset of all vagones
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Vagon")
	public AgentSet<subtes.relogo.Vagon> vagones(){
		AgentSet<subtes.relogo.Vagon> a = new AgentSet<subtes.relogo.Vagon>();
		for (Object e : this.getMyObserver().getContext().getObjects(subtes.relogo.Vagon.class)) {
			if (e instanceof subtes.relogo.Vagon){
				a.add((subtes.relogo.Vagon)e);
			}
		}
		return a;
	}

	/**
	 * Returns the vagon with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.Vagon")
	public subtes.relogo.Vagon vagon(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof subtes.relogo.Vagon)
			return (subtes.relogo.Vagon) turtle;
		return null;
	}

	/**
	 * Queries if object is a userLink.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userLink
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserLink")
	public boolean isUserLinkQ(Object o){
		return (o instanceof subtes.relogo.UserLink);
	}

	/**
	 * Returns an agentset containing all userLinks.
	 * 
	 * @return agentset of all userLinks
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserLink")
	public AgentSet<subtes.relogo.UserLink> userLinks(){
		AgentSet<subtes.relogo.UserLink> a = new AgentSet<subtes.relogo.UserLink>();
		for (Object e : this.getMyObserver().getContext().getObjects(subtes.relogo.UserLink.class)) {
			if (e instanceof subtes.relogo.UserLink){
				a.add((subtes.relogo.UserLink)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            an integer
	 * @param otherEnd
	 *            an integer
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserLink")
	public subtes.relogo.UserLink userLink(Number oneEnd, Number otherEnd) {
		return (subtes.relogo.UserLink)(this.getMyObserver().getNetwork("UserLink").getEdge(turtle(oneEnd),turtle(otherEnd)));
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            a turtle
	 * @param otherEnd
	 *            a turtle
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("subtes.relogo.UserLink")
	public subtes.relogo.UserLink userLink(Turtle oneEnd, Turtle otherEnd) {
		return userLink(oneEnd.getWho(), otherEnd.getWho());
	}

	/**
	 * Returns the value of the global variable capacidadC.
	 *
	 * @return the value of the global variable capacidadC
	 */
	@ReLogoBuilderGeneratedFor("global: capacidadC")
	public Object getCapacidadC(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("capacidadC");
	}

	/**
	 * Sets the value of the global variable capacidadC.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: capacidadC")
	public void setCapacidadC(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("capacidadC",value);
	}

	/**
	 * Returns the value of the global variable capacidadF.
	 *
	 * @return the value of the global variable capacidadF
	 */
	@ReLogoBuilderGeneratedFor("global: capacidadF")
	public Object getCapacidadF(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("capacidadF");
	}

	/**
	 * Sets the value of the global variable capacidadF.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: capacidadF")
	public void setCapacidadF(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("capacidadF",value);
	}

	/**
	 * Returns the value of the global variable frecuenciaC.
	 *
	 * @return the value of the global variable frecuenciaC
	 */
	@ReLogoBuilderGeneratedFor("global: frecuenciaC")
	public Object getFrecuenciaC(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("frecuenciaC");
	}

	/**
	 * Sets the value of the global variable frecuenciaC.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: frecuenciaC")
	public void setFrecuenciaC(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("frecuenciaC",value);
	}

	/**
	 * Returns the value of the global variable frecuenciaF.
	 *
	 * @return the value of the global variable frecuenciaF
	 */
	@ReLogoBuilderGeneratedFor("global: frecuenciaF")
	public Object getFrecuenciaF(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("frecuenciaF");
	}

	/**
	 * Sets the value of the global variable frecuenciaF.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: frecuenciaF")
	public void setFrecuenciaF(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("frecuenciaF",value);
	}

	/**
	 * Returns the value of the global variable porcentaje_a_F.
	 *
	 * @return the value of the global variable porcentaje_a_F
	 */
	@ReLogoBuilderGeneratedFor("global: porcentaje_a_F")
	public Object getPorcentaje_a_F(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("porcentaje_a_F");
	}

	/**
	 * Sets the value of the global variable porcentaje_a_F.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: porcentaje_a_F")
	public void setPorcentaje_a_F(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("porcentaje_a_F",value);
	}

	/**
	 * Returns the value of the global variable nuevos_usuarios.
	 *
	 * @return the value of the global variable nuevos_usuarios
	 */
	@ReLogoBuilderGeneratedFor("global: nuevos_usuarios")
	public Object getNuevos_usuarios(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("nuevos_usuarios");
	}

	/**
	 * Sets the value of the global variable nuevos_usuarios.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: nuevos_usuarios")
	public void setNuevos_usuarios(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("nuevos_usuarios",value);
	}

	/**
	 * Returns the value of the global variable tipo_dia.
	 *
	 * @return the value of the global variable tipo_dia
	 */
	@ReLogoBuilderGeneratedFor("global: tipo_dia")
	public Object getTipo_dia(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("tipo_dia");
	}

	/**
	 * Sets the value of the global variable tipo_dia.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: tipo_dia")
	public void setTipo_dia(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("tipo_dia",value);
	}


}