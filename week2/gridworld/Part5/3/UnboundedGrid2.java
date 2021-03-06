/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.*;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray;
    private int row, column;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2(){
        row=16;
        column=16;
        occupantArray = new Object[row][column];
    }

    //get and set row and col
    public int getNumRows(){
        return -1;
    }

    public int getNumCols(){
        return -1;
    }

    //judge if the loc is vaild
    public boolean isValid(Location loc){
        return loc.getRow()>=0 && loc.getCol()>=0;
    }

    public ArrayList<Location> getOccupiedLocations(){
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                // If there's an object at this location, put it in the array.
                Location loc = new Location(i, j);
                if (get(loc) != null){
                    theLocations.add(loc);
                }
            }
        }

        return theLocations;
    }

    //get the actor that is on the loc
    public E get(Location loc){
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if(loc.getRow()>=row || loc.getCol()>=column){
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    //put the actor to the loc
    public E put(Location loc, E obj)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null){
            throw new NullPointerException("obj == null");
        }

        if(loc.getRow()>=row || loc.getCol()>=column){
            extendMap(loc.getRow(), loc.getCol());
        }
        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if(loc.getRow()>=row || loc.getCol()>=column){
            return null;
        }
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
    
    public void extendMap(int row2, int col2) {
        int oldRow=row, oldCol=column;
        while(row2>=row || col2>=column) {
            row*=2;
            column*=2;
        }
        Object[][] newMap=new Object[row][column];
        for(int i=0; i<oldRow; i++) {
            for(int j=0; j<oldCol; j++) {
                newMap[i][j]=occupantArray[i][j];
            }
        }
        occupantArray=newMap;
    }
}
