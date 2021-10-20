package PebbleGame;

public class Rock {
    
    int weight;

    /**
     * The Constructor for the Rock object
     * @param weight Inputted weight
     */
    public Rock(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "\n   ---\n   Rock\n   Weight: " + weight;
    }
}
