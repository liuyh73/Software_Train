import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public final class SparseBoundedGridRunner {
    private SparseBoundedGridRunner(){}
    //main function
    public static void main(String[] args) {
        //Create a new ActorWorld
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}