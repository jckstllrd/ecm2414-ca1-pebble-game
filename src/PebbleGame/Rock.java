package PebbleGame;

/**
 * This class is usd to represents a rock (pebble) in the game and only has one atribute 
 * which is it's weight.
 */
public class Rock {

    /**
     * This is the weight of the rock.
     */
    int weight;

    /**
     * The Constructor for the Rock object
     * @param weight Inputted weight
     */
    public Rock(int weight) {
        this.weight = weight;
    }
    
    /**
     * This method is use to return the weight of the rock as a String value.
     */
    @Override
    public String toString() {
        return String.valueOf(weight);
    }
}
