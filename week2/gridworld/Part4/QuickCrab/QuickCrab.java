import java.util.ArrayList;
import info.gridworld.grid.Location;

public class QuickCrab extends CrabCritter{
    @Override
    //Override getMoveLocations
    public ArrayList<Location> getMoveLocations(){
        ArrayList<Location> locs = new ArrayList<Location>();
        Location loc = getLocation();
        int[] dirs = { Location.LEFT, Location.RIGHT};
        //The for loop will get the position that the crab can move to.
        for(int dir : dirs) {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + dir);
            Location neighborLoc2=neighborLoc.getAdjacentLocation(getDirection() + dir);
            if(getGrid().isValid(neighborLoc) && getGrid().isValid(neighborLoc2) && getGrid().get(neighborLoc)==null && getGrid().get(neighborLoc2)==null){
                locs.add(neighborLoc2);
            }
        }
        //return the lcos
        //if the locs.size()=0
        if(locs.size()!=0){
            return locs;
        }
        else{
            return super.getMoveLocations();
        }
    }
}
