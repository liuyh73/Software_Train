/* 
 * AP(r) Software Engineering GridWorld Case Study:
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
 * @author LiuYH
 */


import java.util.ArrayList;

import info.gridworld.actor.Bug;

/**
 * A <code>ZBug</code> traces out a alpha Z of a given side length. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
    private ArrayList<Integer>array = new ArrayList<Integer>();
    private int turns;

    /**
     * Constructs a DancingBug bug that traces out a alpha Z of a given side length.
     * @param length the side length
     */
    public DancingBug(Integer [] array2)
    {
        turns=array2[0];
        for(int i=1;i<array2.length;i++){
            array.add(array2[i]);
        }
        array.add(array2[0]);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if(turns==0) {
            turns=array.get(0);
            array.remove(0);
            array.add(turns);
            move();
        }
        turn();
        turns--;
    }
}
