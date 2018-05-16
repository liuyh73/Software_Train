import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public final class ChameleonKidRunner
{
    private ChameleonKidRunner(){}
    //static main function
    public static void main(String[] args)
    {
        //Create the new ActorWorld
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(1, 5), new Rock(Color.RED));
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        world.add(new Location(4, 4), new ChameleonKid());
        world.add(new Location(5, 8), new ChameleonKid());
        world.show();
    }
}