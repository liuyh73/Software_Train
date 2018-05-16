import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    private Map<Location, E> occupantMap;
    private int row, col;
    //the constructor of the class
    public SparseBoundedGrid2(int row2, int col2) {
        // TODO Auto-generated constructor stub
        row=row2;
        col=col2;
        occupantMap = new HashMap<Location,E>();
    }
    //get row and col
    public int getNumRows(){
        return row;
    }

    public int getNumCols(){
        return col;
    }
    //judge if the loc is valid 
    public boolean isValid(Location loc){
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
    //get the occupied locations of the cratter
    public ArrayList<Location> getOccupiedLocations(){
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet()){
            a.add(loc);
        }
        return a;
    }
    //get the actor that is on loc
    public E get(Location loc){
        if (loc == null){
            throw new NullPointerException("loc == null");
        }
        if(!isValid(loc)) {
        	return null;
        }
        return occupantMap.get(loc);
    }
    //put the actor to the loc
    public E put(Location loc, E obj){
        if (loc == null){
            throw new NullPointerException("loc == null");
        }
        if (obj == null){
            throw new NullPointerException("obj == null");
        }
        return occupantMap.put(loc, obj);
    }
    //remove the actor from the loc
    public E remove(Location loc){
        if (loc == null){
            throw new NullPointerException("loc == null");
        }
        return occupantMap.remove(loc);
    }
}
