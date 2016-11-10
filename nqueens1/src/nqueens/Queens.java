package nqueens;

/**
 *
 * @author shivam207
 */
public class Queens {
    
    private final int[] locations;
        
    public Queens(int n) {
    
        locations = new int[n];
    }

    public void setQueen(int i, int val){
    
        this.locations[i] = val;
    }
    
    public int getQueen(int i) {
    
        return this.locations[i];
    }
    
    public int length(){
        
        return this.locations.length;
    
    }
}
