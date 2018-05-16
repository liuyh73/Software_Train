import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Jumper extends Actor{
    
    /**
     * The Constructor of Jumper 
     */
    public Jumper() {}
    
    /**
     * Moves to the next location of the square.
     */
    public void act() {
        if(canJump()) {
            jump();
        }else {
            turn();
        }
    }
    
    /**
     * Judge whether the Jumper can jump.
     * @return
     */
    public boolean canJump() {
        Grid<Actor> grid = getGrid();
        if(grid==null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if(!grid.isValid(next)){
            return false;
        }
        next=next.getAdjacentLocation(getDirection());
        if(!grid.isValid(next)){
            return false;
        }
        Actor neighbor = grid.get(next);
        return (neighbor==null || (neighbor instanceof Flower));
    }
    
    /**
     * Jump function.
     */
    public void jump() {
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        moveTo(next);
    }
    
    /**
     * Turn function.
     */
    public void turn(){
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
}
