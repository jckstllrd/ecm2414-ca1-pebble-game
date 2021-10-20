package PebbleGame;

public class Bag {

    Rock[] rocks = {};

    Bag pairedBag;

    public void addRock(Rock newRock) {
        Rock[] newList = new Rock[rocks.length + 1];
        for (int i = 0; i < rocks.length; i++) {
            newList[i] = rocks[i];
        }
        newList[rocks.length] = newRock;
        rocks = newList;
    }

    /**
     *  WhiteBag
     */
    public class WhiteBag extends Bag{

        public WhiteBag(Bag paired) {

            this.pairedBag = paired; // Pairs white to black bag for emptying
        }

        public void emptyBag() {
            for(Rock rock : rocks) {
                pairedBag.addRock(rock); // Adds rock to the black bag
                
                // TODO: Remove same rock from white bag 
            }
        }
        
        
    }
    
    /**
     * BlackBack
     */
    public class BlackBag extends Bag{

        public BlackBag(int[] rocksFile, Bag paired) {

            this.pairedBag = paired; //Pairs black to white bag for when empty

            for (int i = 0; i < rocksFile.length; i++) {
                Rock rock = new Rock(rocksFile[i]); //Reads each element in rock file and creates rock
                addRock(rock); // Adds rock to the bags rock list
            }
        }
        
        
    }
}
