import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class RockHoundRunner {
    //The main function
    private RockHoundRunner(){}
    public static void main(String[] args) {
        //Create the new ActorWorld
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        world.add(new Location(5, 8), new RockHound());
        world.show();
    }
}