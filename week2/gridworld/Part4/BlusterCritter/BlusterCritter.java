import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class BlusterCritter extends Critter{
    private int critterCount;
    //Constructor
    public BlusterCritter(int critterCount2, Color color) {
        critterCount=critterCount2;
        setColor(color);
    }
    //Override getActor
    @Override
    public ArrayList<Actor> getActors() {
        ArrayList<Actor>arrayList = new ArrayList<Actor>();
        int row=getLocation().getRow();
        int col=getLocation().getCol();

        //for loop to get the actors
        for(int i= Math.max(row-2, getGrid().getNumRows()); i<= Math.min(row+2, getGrid().getNumRows()); i++) {
            for(int j=Math.max(col-2, getGrid().getNumCols()); j<=Math.min(col+2, getGrid().getNumCols()); j++) {
                if(getGrid().get(new Location(i, j)) instanceof Critter){
                    arrayList.add(getGrid().get(new Location(i, j)));
                }
            }
        }
        //return the result
        return arrayList;
    }
    

    //override processActors
    @Override
    public void processActors(ArrayList<Actor> actors){
        int num=255, num2=1;
        //get the color 
        double factor = actors.size()-num2 < critterCount ? 0.05 : -0.05;
        Color c = getColor();
        //set the new rgb
        int red = Math.min((int) ((c.getRed()+num2) * (num2 + factor)), num);
        int green = Math.min((int) ((c.getGreen()+num2) * (num2 + factor)), num);
        int blue = Math.min((int) ((c.getBlue()+num2) * (num2 + factor)), num);
        //set the color
        setColor(new Color(red, green, blue));
    }
}
