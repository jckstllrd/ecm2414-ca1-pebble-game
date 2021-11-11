package PebbleGame;

/**
 * This class is used to define a White Bag which inherits from its parent class
 * Bag. A white bag is used by the program to store rocks when they are
 * discarded by a player and then each which bag is asigned to a black back
 * which is then refilled by its partnering white bag when empty. The class has
 * this asigned black bag, an array of rocks, and an id number as attributes.
 */
public class WhiteBag extends Bag {

    /**
     * This is the black bag the white bag is asigned to.
     */
    private BlackBag assignedBlackBag;

    /**
     * This is the array of rocks stored in the white bag.
     */
    private Rock[] rocks;

    /**
     * This is the id number of the white bag.
     */
    private int number;

    /**
     * This is the constructor for a white bag which calls and uses its parent class
     * Bag's constructor.
     */
    public WhiteBag() {
        rocks = new Rock[] {};
    }

    /**
     * This method is used to add a rock to the White Bag's array of rocks.
     * 
     * @param newRock The new rock to be added to the array.
     */
    public synchronized void addToWhiteBag(Rock newRock) {
        Rock[] newRocks = new Rock[rocks.length + 1];
        for (int i = 0; i < rocks.length; i++) {
            newRocks[i] = rocks[i];
        }
        newRocks[rocks.length] = newRock;
        rocks = newRocks;
    }

    /***
     * This method is used to add all the rocks in the WhiteBag to the asigned black
     * bag and then to clear the white bag's rocks to an empty array.
     */
    public synchronized void drainWhiteBag() {
        for (int i = 0; i < rocks.length; i++) {
            assignedBlackBag.refillBag(rocks[i]);
        }
        Rock[] newRocks = new Rock[] {};
        rocks = newRocks;
    }

    /**
     * This method is used to asign a black back object to the class's attribute.
     * 
     * @param bagNumber        the id number of this bag.
     * @param assignedBlackBag the black bag to be asigned to this white bag.
     */
    public void assignBlackBag(int bagNumber, BlackBag assignedBlackBag) {
        this.number = bagNumber;
        this.assignedBlackBag = assignedBlackBag;
    }

    /**
     * This method returns the white bags rocks/
     * 
     * @return the white bags rocks.
     */
    @Override
    public Rock[] getRocks() {
        return rocks;
    }

    /**
     * This method returns the assiged black bag of the white bag.
     * @return the assigned black bag.
     */
    public BlackBag getAssignedBlackBag() {
        return assignedBlackBag;
    }

    /**
     * This method returns the id number of the white bag.
     * 
     * @return the id number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * This method displays the White Bag in a user friendly readable way.
     */
    @Override
    public String toString() {
        return "\n---\nBlack Bag " + number + "Rocks" + rocks.toString();
    }
}