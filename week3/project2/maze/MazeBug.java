package info.gridworld.maze;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public final class MazeBug extends Bug {
	private Location next;
	private boolean isEnd = false;
	private Map<Location, Location>map;
	private Stack<Location> crossLocation = new Stack<Location>();
	private Integer stepCount = 0;
	//final message has been shown
	private boolean hasShown = false;
	private int[] dirCounter= {0,0,0,0};

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		map=new HashMap<Location, Location>();
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd) {
		//to show step count when reach the goal		
			if (!hasShown) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null){
			return null;
		}
		ArrayList<Location> valid = new ArrayList<Location>();
		ArrayList<Location> locations=gr.getValidAdjacentLocations(getLocation());
		//find the North, East, South, West empty locations
		for(Location location:locations) {
			if((gr.get(location)==null) && (location.getDirectionToward(getLocation())+360)%90==0){
				valid.add(location);
			}
			//judge if the bug move to the red rock
			if(gr.get(location) instanceof Rock && gr.get(location).getColor().equals(Color.RED) && (location.getDirectionToward(getLocation())+360)%90==0) {
				isEnd=true;
				break;
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		ArrayList<Location> valid=getValid(getLocation());
		crossLocation.push(getLocation());
		boolean flag=false;
		int maxx=-1;
		//find a adj location of the top location
		for(Location location:valid) {
			if(map.get(location)==null && dirCounter[getLocation().getDirectionToward(location)/90] > maxx) {
				maxx=dirCounter[getLocation().getDirectionToward(location)/90];
				flag=true;
				next=location;
			}
		}
		//have found a location
		if(flag) {
			map.put(next, getLocation());
			int dir=getLocation().getDirectionToward(next);
			dirCounter[dir/90]++;
		}
		//otherwise, pop the top location
		if(!flag) {
			crossLocation.pop();
			next=map.get(getLocation());
			int dir=next.getDirectionToward(getLocation());
			dirCounter[dir/90]--;
		}
		return true;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null){
			return;
		}
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else{
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}
