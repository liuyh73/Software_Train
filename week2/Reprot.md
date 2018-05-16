[TOC]

# Part3

### Set 3 

1. How would you access the row value for loc1?


   Call its method getRow, such as loc1.getRow(), which will return the row value for loc1.

   In the following code, the method getRow will return row value belonged to the loc.

   ```java
   	// @file: info/gridworld/grid/Location.java
  	// @line: 110-113
	public int getRow()
   	{
   		return row;
   	}
   ```

2. What is the value of b after the following statement is executed?

   b = false; Because the row and the col of loc1 and loc2 are different, the return value will be false. 

   ```java
   	// @file: info/gridworld/grid/Location.java
     	// @line: 205
   	public boolean equals(Object other)
       {
           if (!(other instanceof Location))
               return false;

           Location otherLoc = (Location) other;
           return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
       }

   ```

3. What is the value of loc3 after the following statement is executed?

   The value of loc3 will be (4, 4).

   ```java
       // @file: info/gridworld/grid/Location.java
     	// @line: 130-169
   	public Location getAdjacentLocation(int direction)
       {
   		……
           int dc = 0;
           int dr = 0;
           ……
           else if (adjustedDirection == SOUTH)
               dr = 1;
           ……
           return new Location(getRow() + dr, getCol() + dc);
       }
   ```

   From the code above, we can know that dr=1, dc=0, and the new Location will be the south one of loc2. So loc3 will be (4 ,4). 

4. What is the value of dir after the following statement is executed?

   dir=135. The explanation are the comment in the following code. 

   ```java
       // @file: info/gridworld/grid/Location.java
     	// @line: 178-195
   	public int getDirectionToward(Location target) 	//The method getDirectionToward and target is (6, 5).
       {
           int dx = target.getCol() - getCol();	//dx=2
           int dy = target.getRow() - getRow();	//dy=2
           int angle = (int) Math.toDegrees(Math.atan2(-dy, dx)); 	//angle=315
           int compassAngle = RIGHT - angle;		//compassAngle=-225
           compassAngle += HALF_RIGHT / 2;			//compassAngle=-202.5
           if (compassAngle < 0)					
               compassAngle += FULL_CIRCLE;		//compassAngle=157.5
           return (compassAngle / HALF_RIGHT) * HALF_RIGHT; //compassAngle / HALF_RIGHT = 3; so the result is multiplied by HALF_RIGHT will be 135.
       }

   ```

5. How does the getAdjacentLocation method know which adjacent location to return?

   First, adjustedDirection is obtained according to the direction, and then the coordinate difference is used to respectively represent the eight directions near the current location. When dc and dr are not 0 at the same time. The values of dc and dr respectively indicate eight directions.

   ```java
       // @file: info/gridworld/grid/Location.java
     	// @line: 130-169
   	public Location getAdjacentLocation(int direction)
       {
           // reduce mod 360 and round to closest multiple of 45
           int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
           if (adjustedDirection < 0)
               adjustedDirection += FULL_CIRCLE;

           adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
           int dc = 0;
           int dr = 0;
           if (adjustedDirection == EAST)
               dc = 1;
           else if (adjustedDirection == SOUTHEAST)
           {
               dc = 1;
               dr = 1;
           }
           else if (adjustedDirection == SOUTH)
               dr = 1;
           else if (adjustedDirection == SOUTHWEST)
           {
               dc = -1;
               dr = 1;
           }
           else if (adjustedDirection == WEST)
               dc = -1;
           else if (adjustedDirection == NORTHWEST)
           {
               dc = -1;
               dr = -1;
           }
           else if (adjustedDirection == NORTH)
               dr = -1;
           else if (adjustedDirection == NORTHEAST)
           {
               dc = 1;
               dr = -1;
           }
           return new Location(getRow() + dr, getCol() + dc);
       }
   ```

### Set 4

1. How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?

   The following function can help me obtain a ArrayList of the locations that are occupied in a grid. The the ArrayList's size will give me the count of the objects in a grid.

   ```java
    	// @file: info/gridworld/grid/Grid.java
     	// @line: 85
   	// Grid interface
   	ArrayList<Location> getOccupiedLocations();
   ```

   In a bounded grid, we can get a count of the object in the grid as above, then we can use  getNumRows() * getNumCols() - count to get the count of the empty locations.

   ```java
    	// @file: info/gridworld/grid/BoundedGrid.java
     	// @line: 48-58
   	// BoundedGrid class
   	public int getNumRows()
       {
           return occupantArray.length;
       }

       public int getNumCols()
       {
           return occupantArray[0].length;
       }
   ```

   The methods above will return the row value and the col value of the bounded grid.

2. How can you check if location (10,10) is in a grid?

   I can use the method isValid(). Let the location (10, 10) be the parameter, the the return value will tell me if the location is in the grid.

   ```java
    	// @file: info/gridworld/grid/Grid.java
     	// @line: 50
   	// Grid interface
   	boolean isValid(Location loc);
   ```

3. Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?

   Because Grid is a interface and it contains all the methods that a class will implement. The method is implemented in AbStractGrid.java, BoundedGrid.java and UnBoundedGrid.java

   ```java
    	// @file: info/gridworld/grid/AbstractGrid.java
     	// @line: 26
   	public abstract class AbstractGrid<E> implements Grid<E> //This abstract class implements part of the methods.
       // @file: info/gridworld/grid/BoundedGrid.java
     	// @line: 29
       public class BoundedGrid<E> extends AbstractGrid<E> //This class implements all the methods and part of these methods are inherit from AbstractGrid.
       // @file: info/gridworld/grid/UnboundedGrid.java
     	// @line: 31
       public class UnboundedGrid<E> extends AbstractGrid<E> //This class implements all the methods and part of these methods are inherit from AbstractGrid.
   ```

4. All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.

   No, I think it is a better design to return the objects in an ArrayList. Because an ArrayList has its methods to show the count, and It's easy to iterate over the elements in the array. It give us some method to operate on the objects. Also, it is efficient to return an array. If we use an array, it may be more troublesome.

   ```java
   	// @file: info/gridworld/grid/Grid.java
     	// @line: 96
   	// Grid interface
   	public ArrayList<Location> getValidAdjacentLocations(Location loc)
   ```

### Set 5

1. Name three properties of every actor.

   grid, location, direction (and color).

   ```java
   	// @file: info/gridworld/actor/Actor.java
     	// @line: 31-34
   	// Actor class
   	private Grid<Actor> grid;
       private Location location;
       private int direction;
       private Color color;
   ```

2. When an actor is constructed, what is its direction and color?

   Its direction is NORTH and its color is BLUE.

   ```java
   	// @file: info/gridworld/actor/Actor.java
     	// @line: 39-45
   	// Actor class
   	public Actor()
       {
           color = Color.BLUE;
           direction = Location.NORTH;
           ……
       }
   ```

3. Why do you think that the Actor class was created as a class instead of an interface?

   Because an interface can't have its properties, but the Actor need to have some properties and it is an entity. Also, the Actor will be inherited by other class, but interface can't be inherited.

   The properties of Actor are as follows and it is inherited by the Bug class:

    ```java
   	// @file: info/gridworld/actor/Actor.java
     	// @line: 31-34
   	// Actor class
   	private Grid<Actor> grid;
       private Location location;
   	private int direction;
   	private Color color;
    	// @file: info/gridworld/actor/Bug.java
     	// @line: 29
   	// Bug class
       public class Bug extends Actor
    ```

4. Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?

   No. An actor can't put itself into a grid twice without first removing itself. The codes and Error message are as follows: 

   ```java
    	// @file: projects/boxBug/BoxBugRunner.java
   	ActorWorld world = new ActorWorld();
   	BoxBug bug = new BoxBug(3);
   	world.add(new Location(7, 8), bug);
   	bug.putSelfInGrid(bug.getGrid(), new Location(4, 5));

   Exception in thread "main" java.lang.IllegalStateException: This actor is already contained in a grid.
   	at info.gridworld.actor.Actor.putSelfInGrid(Actor.java:118)
   	at BoxBugRunner.main(BoxBugRunner.java:37)
   ```

   No. An actor can't remove itself from a grid twice.

   ```java
    	// @file: projects/boxBug/BoxBugRunner.java
   	ActorWorld world = new ActorWorld();
       BoxBug bug = new BoxBug(3);
       world.add(new Location(7, 8), bug);
       bug.removeSelfFromGrid();
       bug.removeSelfFromGrid();

   Exception in thread "main" java.lang.IllegalStateException: This actor is not contained in a grid.
   	at info.gridworld.actor.Actor.removeSelfFromGrid(Actor.java:136)
   	at BoxBugRunner.main(BoxBugRunner.java:38)
   ```

   As for the third question, there are two different possibilities:

   If we store the grid for the bug in advance, an actor can be placed into a grid, remove itself, and then put itself back.

   ```java
    	// @file: projects/boxBug/BoxBugRunner.java
   	ActorWorld world = new ActorWorld();
       BoxBug bug = new BoxBug(3);
       world.add(new Location(7, 8), bug);
       Grid<Actor> grid= bug.getGrid();
       bug.removeSelfFromGrid();
       bug.putSelfInGrid(grid, new Location(4, 5));
   ```

   If we don't store the grid for the bug in advance, an actor can be placed into a grid, remove itself, and then put itself back.

   ```java
    	// @file: projects/boxBug/BoxBugRunner.java
   	ActorWorld world = new ActorWorld();
       BoxBug bug = new BoxBug(3);
       world.add(new Location(7, 8), bug);
       bug.removeSelfFromGrid();
       bug.putSelfInGrid(bug.getGrid(), new Location(4, 5));
       
   Exception in thread "main" java.lang.NullPointerException
   	at info.gridworld.actor.Actor.putSelfInGrid(Actor.java:121)
   	at BoxBugRunner.main(BoxBugRunner.java:38)
   ```

   ​

   ​

5. How can an actor turn 90 degrees to the right?

   We can call the setDirection method. And the parameter will be current direction plus Location.RIGHT.

   From the following code, we can know that the property direction is set a newDirection.

   ```java
    	// @file: info/gridworld/actor/Actor.java
     	// @line: 80-85
   	// Actor class
   	public void setDirection(int newDirection)
       {
           direction = newDirection % Location.FULL_CIRCLE;
           if (direction < 0)
               direction += Location.FULL_CIRCLE;
       }
   ```

### Set 6

1. Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

   The following codes judge if the next position is valid (in the grid) which it will move to.

   ```java
   	// @file: info/gridworld/actor/Bug.java
     	// @line: 98-99
   	// Bug class
   	if (!gr.isValid(next))
       	return false;
   ```

2. Which statement(s) in the canMove method determines that a bug will not walk into a rock?

   The following codes judge if the next position is empty or a flower. So if it is a rock, the return value will be false, and the bug can't walk into it. 

   ```java
   	// @file: info/gridworld/actor/Bug.java
     	// @line: 100-101
   	// Bug class
   	Actor neighbor = gr.get(next);
       return (neighbor == null) || (neighbor instanceof Flower);
   ```

3. Which methods of the Grid interface are invoked by the canMove method and why?

   The methods isValid and get. Because the method isValid tells us if the next position is in the grid and the method get returns us the object on the cell, which will help us to judge.

   ```java
   	// @file: info/gridworld/actor/Bug.java
     	// @line: 98、100
   	// Bug class
   	if (!gr.isValid(next))
       Actor neighbor = gr.get(next);
   	// @file: info/gridworld/actor/Bug.java
     	// @line: 50、79
       // Grid interface
       boolean isValid(Location loc);
   	E get(Location loc);
   ```

4. Which method of the Location class is invoked by the canMove method and why?

   The method getAdjacentLocation. Because the bug will walk into a new position, we need to get the next position to judge if it is valid.

   ```java
   	// @file: info/gridworld/actor/Bug.java
     	// @line: 77
   	// class Bug 
   	Location next = loc.getAdjacentLocation(getDirection());
   	// @file: info/gridworld/grid/Location.java
     	// @line: 130
   	// class Location 
       public Location getAdjacentLocation(int direction)
   ```

5. Which methods inherited from the Actor class are invoked in the canMove method?

   The method getGrid and getLocation. The two method can give us the instance of Grid and the current position of the object. 

   ```java
   	// @file: info/gridworld/actor/Bug.java
     	// @line: 73、76
   	// method move
   	Grid<Actor> gr = getGrid();
   	Location loc = getLocation();
   ```

6. What happens in the move method when the location immediately in front of the bug is out of the grid?

   The bug will call the method removeSelfFromGrid. The details are as follows:

   ```java
   	// @file: info/gridworld/actor/Bug.java
     	// @line: 76-81
   	// method move
   	Location loc = getLocation();
   	Location next = loc.getAdjacentLocation(getDirection());
   	if (gr.isValid(next))
            moveTo(next);
   	else
       	removeSelfFromGrid();
   ```

   ​

7. Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?

   The variable loc is needed. Because after call the moveTo method, the position of the bug is changed, so when we put a flower on the current position, it will be false. Thus, we need a variable loc to store the bug's position before it moves.

   ```java
   	// @file: info/gridworld/actor/Bug.java
     	// @line: 76-83
   	// method move
       Location loc = getLocation();
       Location next = loc.getAdjacentLocation(getDirection());
       if (gr.isValid(next))
       	moveTo(next);
       else
       	removeSelfFromGrid();
       Flower flower = new Flower(getColor());
       flower.putSelfInGrid(gr, loc);
   ```

8. Why do you think the flowers that are dropped by a bug have the same color as the bug?

   Because every time a bug drop the flower, the constructor of the new flower pass a parameter which was the bug the color.

   ```java
   	// @file: info/gridworld/actor/Bug.java
     	// @line: 82-83
   	// method move
   	Flower flower = new Flower(getColor()); //The method getColor will return the bug's color.
       flower.putSelfInGrid(gr, loc);
   ```

9. When a bug removes itself from the grid, will it place a flower into its previous location?

   Yes. When a bug removes itself from the grid, the move method will also execute the followed the statements. So it will place a flower into its previous location.

   ```java
   	// @file: info/gridworld/actor/Bug.java
     	// @line: 76-83
   	// method move
       Location loc = getLocation();
       Location next = loc.getAdjacentLocation(getDirection());
       if (gr.isValid(next))
       	moveTo(next);
       else
            removeSelfFromGrid();
       Flower flower = new Flower(getColor());
       flower.putSelfInGrid(gr, loc);
   ```

10. Which statement(s) in the move method places the flower into the grid at the bug’s previous location?

   The statements are as follows.

   ```java
   	// @file: info/gridworld/actor/Bug.java
     	// @line: 82-83
   	// method move
   	Flower flower = new Flower(getColor());
       flower.putSelfInGrid(gr, loc);
   ```

11. If a bug needs to turn 180 degrees, how many times should it call the turn method?

    4 time. Because every time it call the turn method, it will turn 45 degrees, so it need to call the turn method 4 times.

    ```java
        // @file: info/gridworld/actor/Bug.java
      	// @line: 62-65
    	// method turn
        public void turn()
        {
            setDirection(getDirection() + Location.HALF_RIGHT); //HALF_RIGHT is 45 degrees.
        }
    ```

    ​