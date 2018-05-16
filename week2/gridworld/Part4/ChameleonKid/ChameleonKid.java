import java.util.ArrayList;

import info.gridworld.actor.Actor;

public class ChameleonKid extends ChameleonCritter{
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as the front or behind one. If there are no actors in front or behind, its color will darken.
     */
    //Override getActor
    @Override
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        //for loop to get the actors
        for (Actor a : getGrid().getNeighbors(getLocation()))
        {
            if (getLocation().getDirectionToward(a.getLocation()) == getDirection() || getLocation().getDirectionToward(a.getLocation()) == getDirection()){
                actors.add(a);
            }
        }
        //return the result
        return actors;
    }
}
