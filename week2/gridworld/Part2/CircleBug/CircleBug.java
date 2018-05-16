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

/**
 * A <code>CircleBug</code> traces out a "circle" that becomes bigger with the time going by. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class CircleBug extends Bug
{
    private int steps;
    private int sideLength;

    /**
     * Constructs a circle bug that traces a "circle" that becomes bigger with the time going by.
     * @param length the side length
     */
    public CircleBug(int length)
    {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            turn();
            steps = 0;
        }
    }
}
