import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public final class SparseBoundedGrid2Runner {
    private SparseBoundedGrid2Runner(){}
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid2");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}