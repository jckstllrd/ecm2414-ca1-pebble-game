package PebbleGame;

/**
 * This class is used to define a Bag which is used as a parent class for
 * the WhiteBag and BlackBag classes. These classes are used to "store"
 * rocks / pebbles when they are respectively disguarded to or taken from.
 */
public class Bag {

    /**
     * This is the Array of rocks stored in the Bag.
     */
    Rock[] rocks = {};

    /**
     * This method is used to add a rock to the array of rocks stored in
     * the instances array of rocks.
     * 
     * @param newRock This is the new rock object to be added.
     */
    public void addRock(Rock newRock) {
        Rock[] newList = new Rock[rocks.length + 1];
        for (int i = 0; i < rocks.length; i++) {
            newList[i] = rocks[i];
        }
        newList[rocks.length] = newRock;
        rocks = newList;
    }
}
