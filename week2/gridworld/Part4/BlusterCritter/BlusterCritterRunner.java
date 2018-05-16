import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class BlusterCritterRunner {
    private BlusterCritterRunner(){}
    //static main function
    public static void main(String[] args) {
        //Create the new ActorWorld
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Critter());
        world.add(new Location(4, 4), new BlusterCritter(1, Color.PINK));
        world.add(new Bug());
        world.add(new Flower());
        world.add(new Bug());
        world.add(new Rock());
        world.show();
    }
}