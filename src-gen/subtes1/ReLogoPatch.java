package subtes1;

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
	 * Sprouts (makes) a number of new pasajeros and then executes a set of commands on the
	 * created pasajeros.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created pasajeros
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Pasajero")
	public AgentSet<subtes1.relogo.Pasajero> sproutPasajeros(int number, Closure closure) {
		AgentSet<subtes1.relogo.Pasajero> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"Pasajero");
		for (Turtle t : createResult){
			if (t instanceof subtes1.relogo.Pasajero){
				result.add((subtes1.relogo.Pasajero)t);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Pasajero")
	public AgentSet<subtes1.relogo.Pasajero> sproutPasajeros(int number) {
		return sproutPasajeros(number,null);
	}

	/**
	 * Returns an agentset of pasajeros from the patch of the caller.
	 * 
	 * @return agentset of pasajeros from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Pasajero")
	public AgentSet<subtes1.relogo.Pasajero> pasajerosHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<subtes1.relogo.Pasajero> result = new AgentSet<subtes1.relogo.Pasajero>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"pasajero")){
			if (t instanceof subtes1.relogo.Pasajero)
			result.add((subtes1.relogo.Pasajero)t);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Pasajero")
	public AgentSet<subtes1.relogo.Pasajero> pasajerosAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<subtes1.relogo.Pasajero> result = new AgentSet<subtes1.relogo.Pasajero>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"pasajero")){
			if (t instanceof subtes1.relogo.Pasajero)
			result.add((subtes1.relogo.Pasajero)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<subtes1.relogo.Pasajero>();
		}
	}

	/**
	 * Returns an agentset of pasajeros on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of pasajeros on patch p
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Pasajero")
	public AgentSet<subtes1.relogo.Pasajero> pasajerosOn(Patch p){
		AgentSet<subtes1.relogo.Pasajero> result = new AgentSet<subtes1.relogo.Pasajero>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"pasajero")){
			if (t instanceof subtes1.relogo.Pasajero)
			result.add((subtes1.relogo.Pasajero)t);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Pasajero")
	public AgentSet<subtes1.relogo.Pasajero> pasajerosOn(Turtle t){
		AgentSet<subtes1.relogo.Pasajero> result = new AgentSet<subtes1.relogo.Pasajero>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"pasajero")){
			if (tt instanceof subtes1.relogo.Pasajero)
			result.add((subtes1.relogo.Pasajero)tt);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Pasajero")
	public AgentSet<subtes1.relogo.Pasajero> pasajerosOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<subtes1.relogo.Pasajero>();
		}

		Set<subtes1.relogo.Pasajero> total = new HashSet<subtes1.relogo.Pasajero>();
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
		return new AgentSet<subtes1.relogo.Pasajero>(total);
	}

	/**
	 * Queries if object is a pasajero.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a pasajero
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Pasajero")
	public boolean isPasajeroQ(Object o){
		return (o instanceof subtes1.relogo.Pasajero);
	}

	/**
	 * Returns an agentset containing all pasajeros.
	 * 
	 * @return agentset of all pasajeros
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Pasajero")
	public AgentSet<subtes1.relogo.Pasajero> pasajeros(){
		AgentSet<subtes1.relogo.Pasajero> a = new AgentSet<subtes1.relogo.Pasajero>();
		for (Object e : this.getMyObserver().getContext().getObjects(subtes1.relogo.Pasajero.class)) {
			if (e instanceof subtes1.relogo.Pasajero){
				a.add((subtes1.relogo.Pasajero)e);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Pasajero")
	public subtes1.relogo.Pasajero pasajero(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof subtes1.relogo.Pasajero)
			return (subtes1.relogo.Pasajero) turtle;
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Subte")
	public AgentSet<subtes1.relogo.Subte> sproutSubtes(int number, Closure closure) {
		AgentSet<subtes1.relogo.Subte> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"Subte");
		for (Turtle t : createResult){
			if (t instanceof subtes1.relogo.Subte){
				result.add((subtes1.relogo.Subte)t);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Subte")
	public AgentSet<subtes1.relogo.Subte> sproutSubtes(int number) {
		return sproutSubtes(number,null);
	}

	/**
	 * Returns an agentset of subtes from the patch of the caller.
	 * 
	 * @return agentset of subtes from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Subte")
	public AgentSet<subtes1.relogo.Subte> subtesHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<subtes1.relogo.Subte> result = new AgentSet<subtes1.relogo.Subte>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"subte")){
			if (t instanceof subtes1.relogo.Subte)
			result.add((subtes1.relogo.Subte)t);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Subte")
	public AgentSet<subtes1.relogo.Subte> subtesAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<subtes1.relogo.Subte> result = new AgentSet<subtes1.relogo.Subte>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"subte")){
			if (t instanceof subtes1.relogo.Subte)
			result.add((subtes1.relogo.Subte)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<subtes1.relogo.Subte>();
		}
	}

	/**
	 * Returns an agentset of subtes on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of subtes on patch p
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Subte")
	public AgentSet<subtes1.relogo.Subte> subtesOn(Patch p){
		AgentSet<subtes1.relogo.Subte> result = new AgentSet<subtes1.relogo.Subte>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"subte")){
			if (t instanceof subtes1.relogo.Subte)
			result.add((subtes1.relogo.Subte)t);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Subte")
	public AgentSet<subtes1.relogo.Subte> subtesOn(Turtle t){
		AgentSet<subtes1.relogo.Subte> result = new AgentSet<subtes1.relogo.Subte>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"subte")){
			if (tt instanceof subtes1.relogo.Subte)
			result.add((subtes1.relogo.Subte)tt);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Subte")
	public AgentSet<subtes1.relogo.Subte> subtesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<subtes1.relogo.Subte>();
		}

		Set<subtes1.relogo.Subte> total = new HashSet<subtes1.relogo.Subte>();
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
		return new AgentSet<subtes1.relogo.Subte>(total);
	}

	/**
	 * Queries if object is a subte.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a subte
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Subte")
	public boolean isSubteQ(Object o){
		return (o instanceof subtes1.relogo.Subte);
	}

	/**
	 * Returns an agentset containing all subtes.
	 * 
	 * @return agentset of all subtes
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Subte")
	public AgentSet<subtes1.relogo.Subte> subtes(){
		AgentSet<subtes1.relogo.Subte> a = new AgentSet<subtes1.relogo.Subte>();
		for (Object e : this.getMyObserver().getContext().getObjects(subtes1.relogo.Subte.class)) {
			if (e instanceof subtes1.relogo.Subte){
				a.add((subtes1.relogo.Subte)e);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.Subte")
	public subtes1.relogo.Subte subte(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof subtes1.relogo.Subte)
			return (subtes1.relogo.Subte) turtle;
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserTurtle")
	public AgentSet<subtes1.relogo.UserTurtle> sproutUserTurtles(int number, Closure closure) {
		AgentSet<subtes1.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.sprout(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof subtes1.relogo.UserTurtle){
				result.add((subtes1.relogo.UserTurtle)t);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserTurtle")
	public AgentSet<subtes1.relogo.UserTurtle> sproutUserTurtles(int number) {
		return sproutUserTurtles(number,null);
	}

	/**
	 * Returns an agentset of userTurtles from the patch of the caller.
	 * 
	 * @return agentset of userTurtles from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserTurtle")
	public AgentSet<subtes1.relogo.UserTurtle> userTurtlesHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<subtes1.relogo.UserTurtle> result = new AgentSet<subtes1.relogo.UserTurtle>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"userTurtle")){
			if (t instanceof subtes1.relogo.UserTurtle)
			result.add((subtes1.relogo.UserTurtle)t);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserTurtle")
	public AgentSet<subtes1.relogo.UserTurtle> userTurtlesAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getGridLocationAsNdPoint(),displacement,getMyObserver());
		AgentSet<subtes1.relogo.UserTurtle> result = new AgentSet<subtes1.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"userTurtle")){
			if (t instanceof subtes1.relogo.UserTurtle)
			result.add((subtes1.relogo.UserTurtle)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<subtes1.relogo.UserTurtle>();
		}
	}

	/**
	 * Returns an agentset of userTurtles on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of userTurtles on patch p
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserTurtle")
	public AgentSet<subtes1.relogo.UserTurtle> userTurtlesOn(Patch p){
		AgentSet<subtes1.relogo.UserTurtle> result = new AgentSet<subtes1.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"userTurtle")){
			if (t instanceof subtes1.relogo.UserTurtle)
			result.add((subtes1.relogo.UserTurtle)t);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserTurtle")
	public AgentSet<subtes1.relogo.UserTurtle> userTurtlesOn(Turtle t){
		AgentSet<subtes1.relogo.UserTurtle> result = new AgentSet<subtes1.relogo.UserTurtle>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"userTurtle")){
			if (tt instanceof subtes1.relogo.UserTurtle)
			result.add((subtes1.relogo.UserTurtle)tt);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserTurtle")
	public AgentSet<subtes1.relogo.UserTurtle> userTurtlesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<subtes1.relogo.UserTurtle>();
		}

		Set<subtes1.relogo.UserTurtle> total = new HashSet<subtes1.relogo.UserTurtle>();
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
		return new AgentSet<subtes1.relogo.UserTurtle>(total);
	}

	/**
	 * Queries if object is a userTurtle.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userTurtle
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserTurtle")
	public boolean isUserTurtleQ(Object o){
		return (o instanceof subtes1.relogo.UserTurtle);
	}

	/**
	 * Returns an agentset containing all userTurtles.
	 * 
	 * @return agentset of all userTurtles
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserTurtle")
	public AgentSet<subtes1.relogo.UserTurtle> userTurtles(){
		AgentSet<subtes1.relogo.UserTurtle> a = new AgentSet<subtes1.relogo.UserTurtle>();
		for (Object e : this.getMyObserver().getContext().getObjects(subtes1.relogo.UserTurtle.class)) {
			if (e instanceof subtes1.relogo.UserTurtle){
				a.add((subtes1.relogo.UserTurtle)e);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserTurtle")
	public subtes1.relogo.UserTurtle userTurtle(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof subtes1.relogo.UserTurtle)
			return (subtes1.relogo.UserTurtle) turtle;
		return null;
	}

	/**
	 * Queries if object is a userLink.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userLink
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserLink")
	public boolean isUserLinkQ(Object o){
		return (o instanceof subtes1.relogo.UserLink);
	}

	/**
	 * Returns an agentset containing all userLinks.
	 * 
	 * @return agentset of all userLinks
	 */
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserLink")
	public AgentSet<subtes1.relogo.UserLink> userLinks(){
		AgentSet<subtes1.relogo.UserLink> a = new AgentSet<subtes1.relogo.UserLink>();
		for (Object e : this.getMyObserver().getContext().getObjects(subtes1.relogo.UserLink.class)) {
			if (e instanceof subtes1.relogo.UserLink){
				a.add((subtes1.relogo.UserLink)e);
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserLink")
	public subtes1.relogo.UserLink userLink(Number oneEnd, Number otherEnd) {
		return (subtes1.relogo.UserLink)(this.getMyObserver().getNetwork("UserLink").getEdge(turtle(oneEnd),turtle(otherEnd)));
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
	@ReLogoBuilderGeneratedFor("subtes1.relogo.UserLink")
	public subtes1.relogo.UserLink userLink(Turtle oneEnd, Turtle otherEnd) {
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