import java.util.ArrayList;
import info.gridworld.actor.Actor;

public class KingCrab extends CrabCritter{
    //Override processActors
    public void processActors(ArrayList<Actor> actors) {

        //the for loop will let the other actor to run away.
        for (Actor actor : actors) {
            if(!(actor instanceof KingCrab)) {
                actor.setDirection(getLocation().getDirectionToward(actor.getLocation()));
                actor.act();
            }
        }
        //the for loop will remove the actor that don't run away.
        for(Actor actor : getActors()){
            if(!(actor instanceof KingCrab)){
                actor.removeSelfFromGrid();
            }
        }
    }
}
