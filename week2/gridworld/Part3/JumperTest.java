import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public final class JumperTest {
    
    @Test
    public void testTurn() {
        //Create a new world grid.
        ActorWorld world = new ActorWorld();
        //Create a new jumper.
        Jumper jumper = new Jumper();
        //Set color of the jumper.
        jumper.setColor(Color.ORANGE);
        //Add the jumper onto the grid.
        world.add(new Location(2, 3), jumper);
        
        //The jumper initially faces north.
        assertEquals(Location.NORTH, jumper.getDirection());
        
        //When the jumper turn to the right of 45, it will face north-east. 
        jumper.turn();
        assertEquals(Location.NORTHEAST, jumper.getDirection());
        
      //When the jumper turn to the right of 90, it will face south-east. 
        jumper.turn();
        jumper.turn();
        assertEquals(Location.SOUTHEAST, jumper.getDirection());
    }
    
    
    @Test
    public void testJump() {
        //Create a new world grid.
        ActorWorld world = new ActorWorld();
        //Create a new jumper.
        Jumper jumper = new Jumper();
        //Set color of the jumper.
        jumper.setColor(Color.ORANGE);
        //Add the jumper onto the grid.
        world.add(new Location(2, 3), jumper);
        
        //The jumper initially locates at (2, 3).
        assertEquals("(2, 3)", jumper.getLocation().toString());
        
        //Let the jumper jump, then it will be at (0, 3)
        jumper.jump();
        assertEquals("(0, 3)", jumper.getLocation().toString());
    }
    

    @Test
    public void testCanJump(){
        //Create a new world grid.
        ActorWorld world = new ActorWorld();
        //Create a new jumper.
        Jumper jumper = new Jumper();
        //Set color of the jumper.
        jumper.setColor(Color.ORANGE);
        //Add the jumper onto the grid.
        world.add(new Location(2, 3), jumper);
        
        //The grid was [0-9] * [0-9], so the jumper can jump at this time.
        assertEquals(true,  jumper.canJump());
        
        //The jumper can jump to a position that has flower on it.
        world.add(new Location(0, 3), new Flower());
        assertEquals(true,  jumper.canJump());
        
        //Now it will be on the edge of the grid, and it faces north, so it can't jump.
        jumper.jump();
        assertEquals(false,  jumper.canJump());
        
        //Let it turn right. And this has a bug on (0,5), so it can't jump.
        jumper.turn();
        jumper.turn();
        world.add(new Location(0, 5), new Bug());
        assertEquals(false,  jumper.canJump());
        
        //Replace the bug with a flower, so it can jump.
        world.add(new Location(0, 5), new Flower());
        assertEquals(true,  jumper.canJump());
        
        //Add a Rock on (0,7), so it can't jump.
        jumper.jump();
        world.add(new Location(0, 7), new Rock());
        assertEquals(false,  jumper.canJump());
        
        //Replace the Rock with another jumper, it can't jump, too.
        world.add(new Location(0, 7), new Jumper());
        assertEquals(false,  jumper.canJump());
    }

    @Test
    public void testAct() {
        //Create a new world grid.
        ActorWorld world = new ActorWorld();
        //Create a new jumper.
        Jumper jumper = new Jumper();
        //Set color of the jumper.
        jumper.setColor(Color.ORANGE);
        //Add the jumper onto the grid.
        world.add(new Location(2, 3), jumper);
        jumper.jump();
        
        //The grid was [0-9] * [0-9], so the jumper can jump at this time, and after for a time, it will be at (0, 3).
        assertEquals("(0, 3)",  jumper.getLocation().toString());
        
        //The jumper is now at the edge of the grid. So after the act function, it will face north-east and stay at the same cell.
        jumper.act();
        assertEquals(Location.NORTHEAST, jumper.getDirection());
        
        //Add a rock on the next cell that the jump can jump to, now if can't jump and will turn to the right of 45
        jumper.act();
        world.add(new Location(0, 5),new Rock());
        assertEquals("(0, 3)",  jumper.getLocation().toString());
        assertEquals(Location.EAST, jumper.getDirection());
    }
    
    private JumperTest() {}
    
    public static void main(String args[]){
        Result result = JUnitCore.runClasses(JumperTest.class);
        for(Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

}
