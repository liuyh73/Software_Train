import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class KingCrabRunner
{
    private KingCrabRunner(){}
    //the main function
    public static void main(String[] args){
    	//Create the new ActorWorld
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(4, 5), new Bug());
        world.add(new Location(1, 5), new Flower(Color.RED));
        world.add(new Location(5, 8), new KingCrab());
        world.show();
    }
}