import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

public class RockHound extends Critter{
    //Override processActors
    @Override
    public void processActors(ArrayList<Actor> actors) {
        //The for loop will remove the Rock
        for (Actor a : actors) {
            if (a instanceof Rock){
                a.removeSelfFromGrid();
            }
        }
    }
}
