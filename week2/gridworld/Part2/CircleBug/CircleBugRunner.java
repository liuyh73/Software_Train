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


import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains circle bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class CircleBugRunner
{
    private CircleBugRunner(){}
    public static void main(String[] args)
    {
    	//Create a new world grid.
        ActorWorld world = new ActorWorld();
        //Create a new bug.
        CircleBug alice = new CircleBug(2);
        //Set color of the bug.
        alice.setColor(Color.ORANGE);
        //Add the bug onto the grid.
        world.add(new Location(5, 1), alice);
        //show
        world.show();
    }
}