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

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * A <code>ZBug</code> traces out a alpha Z of a given side length. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int totalSteps;

    /**
     * Constructs a zbug that traces out a alpha Z of a given side length.
     * @param length the side length
     */
    public ZBug(int length)
    {
        steps = 0;
        sideLength = length;
        totalSteps=0;
        setDirection(getDirection() + Location.RIGHT);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if(canMove() && totalSteps<3*sideLength) 
        {
            if (steps < sideLength)
            {
                move();
                steps++;
                totalSteps++;
            }
            else
            {
                if(totalSteps==sideLength){
                    setDirection(getDirection() + Location.RIGHT + Location.HALF_RIGHT);
                }
                else{
                    setDirection(getDirection() + Location.LEFT + Location.HALF_LEFT);
                }
                steps = 0;
            }
        }
    }
}
