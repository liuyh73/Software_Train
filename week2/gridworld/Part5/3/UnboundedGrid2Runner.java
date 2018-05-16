import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public final class UnboundedGrid2Runner {
	private UnboundedGrid2Runner(){}
	//main function
    public static void main(String[] args) {
    	//Create the new ActorWorld
        ActorWorld world = new ActorWorld();
        world.addGridClass("UnboundedGrid2");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}