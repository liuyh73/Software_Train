import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private SparseGridNode[] occupantArray;
    private int row, column;
    
    //the constructor of the class
    public SparseBoundedGrid(int rows, int cols){
        if (rows <= 0){
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0){
            throw new IllegalArgumentException("cols <= 0");
        }
        row=rows;
        column=cols;
        occupantArray = new SparseGridNode[rows];
    }

    //get row and col
    public int getNumRows()
    {
        return row;
    }

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return column;
    }


    //judge if the loc is valid 
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    //get the occupied locations of the cratter
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int i = 0; i < getNumRows(); i++)
        {
            SparseGridNode temp=occupantArray[i];
            while(temp!=null) {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(i, temp.getCol());
                if (get(loc) != null){
                    theLocations.add(loc);
                }
                temp=temp.getNext();
            }

        }
        //return the result
        return theLocations;
    }

    //get the actor that is on loc
    public E get(Location loc)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        SparseGridNode temp=occupantArray[loc.getRow()];
        while(temp!=null) {
            // Judge whether the temp's column is equal to the loc's.
            if(temp.getCol()==loc.getCol()){
                return (E)temp.getOccupant();
            }
            temp=temp.getNext();
        }
        return null;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null){
            throw new NullPointerException("obj == null");
        }

        // Add the object to the grid.
        E oldOccupant = remove(loc);
        SparseGridNode temp = occupantArray[loc.getRow()];
        occupantArray[loc.getRow()]=new SparseGridNode(obj, loc.getCol(), temp);
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        // Remove the object from the grid.
        E r = get(loc);
        if(r==null){
            return null;
        }
        SparseGridNode pGridNode=occupantArray[loc.getRow()];
        if(pGridNode.getCol()==loc.getCol()) {
            occupantArray[loc.getRow()]=pGridNode.getNext();
            return (E)pGridNode.getOccupant();
        }

        SparseGridNode qGridNode=pGridNode;
        pGridNode=pGridNode.getNext();
        while(pGridNode!=null) {

            if(pGridNode.getCol()==loc.getCol()) {
                qGridNode.setNext(pGridNode.getNext());
                return (E)pGridNode.getOccupant();
            }
            pGridNode=pGridNode.getNext();
            qGridNode=qGridNode.getNext();
        }
        return r;
    }
}