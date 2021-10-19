package PebbleGame;

public class Bag {

    Rock[] rocks = {};

    public void addRock(Rock newRock) {
        Rock[] newList = new Rock[rocks.length + 1];
        for (int i = 0; i < rocks.length; i++) {
            newList[i] = rocks[i];
        }
        newList[rocks.length] = newRock;
        rocks = newList;
    }

    /**
     * WhiteBag
     */
    public class WhiteBag {
    
        
    }
    
    /**
     * BlackBack
     */
    public class BlackBag {
        
        public BlackBag() {
            
        }
        
    }
}
