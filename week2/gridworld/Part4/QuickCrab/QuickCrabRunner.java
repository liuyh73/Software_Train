import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class QuickCrabRunner{
    private QuickCrabRunner(){}
    //The main function
    public static void main(String[] args) {
        //Create the new ActorWorld
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(2, 6), new Bug());
        world.add(new Location(7, 2), new Flower(Color.YELLOW));
        world.add(new Location(5, 8), new QuickCrab());
        world.show();
    }
}