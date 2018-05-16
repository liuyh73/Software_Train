
public class SparseGridNode {
    private Object occupant;
    private int col;
    private SparseGridNode next;
    
    //Constructor
    public SparseGridNode(Object occpupant2, int col2, SparseGridNode next2) {
        occupant=occpupant2;
        col=col2;
        next=next2;
    }
    
    //setOccupant function 
    public void setOccupant(Object occpupant2) {
        occupant=occpupant2;
    }
    
    //setCol function
    public void setCol(int col2) {
        col=col2;
    }
    
    //setNext function
    public void setNext(SparseGridNode next2) {
        next=next2;
    }
    
    //getOccupant function
    public Object getOccupant() {
        return occupant;
    }
    
    //getCol function
    public int getCol() {
        return col;
    }
    
    //getNext function
    public SparseGridNode getNext() {
        return next;
    }
}
