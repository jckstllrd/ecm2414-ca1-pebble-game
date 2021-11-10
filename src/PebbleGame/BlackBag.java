package PebbleGame;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is a chile class which extends the Bag class and is used to
 * represent a Black Bag in the game. It stores an asigned WhiteBag which is
 * used to refill the black bag and has an array of rocks in the bag as well as
 * an ID number.
 */
public class BlackBag extends Bag {

    /**
     * This is the asigned WhiteBag which is used to refil the BlackBag.
     */
    private WhiteBag assignedWhiteBag;

    /**
     * This is the array of rocks in the black bag.
     */
    private Rock[] rocks;

    /**
     * This is the ID number of the black bag.
     */
    private int number;

    /**
     * This is the constructor method for a BlackBag, it takes in a bag number to
     * identify the black bag by, an assigned White Bag objet which is used to
     * refill the black bag when ut is empty and an array of rocks.
     * 
     * @param bagNumber        This is the ID number of the bag.
     * @param assignedWhiteBag This is the asigned White Bag for the black bag.
     * @param rocks            This is the array of rocks which are in the black
     *                         bag.
     */
    public BlackBag(int bagNumber, WhiteBag assignedWhiteBag, Rock[] rocks) {
        this.number = bagNumber;
        this.assignedWhiteBag = assignedWhiteBag; // Pairs black to white bag for when empty
        this.rocks = rocks;
        assignedWhiteBag.assignBlackBag(bagNumber, this);
    }

    /**
     * This method is used to add a rock to the black bag, and is used when
     * refilling the bag from a white bag after it is empty.
     * 
     * @param newRock This is the new rock to add to the array of rocks in the black
     *                bag.
     */
    public synchronized void refillBag(Rock newRock) {
        Rock[] newRocks = new Rock[rocks.length + 1];
        for (int i = 0; i < rocks.length; i++) {
            newRocks[i] = rocks[i];
        }
        newRocks[rocks.length] = newRock;
        rocks = newRocks;
    }

    /**
     * This method is used to remove a rock with a given index in the rocks array of
     * the black bag.
     * 
     * @param index This is the index of the rock to be removed.
     */
    public synchronized void removeRock(int index) {
        AtomicInteger length = new AtomicInteger(rocks.length);
        Rock[] newRocks = new Rock[length.get() - 1];
        for (int i = 0, k = 0; i < length.get(); i++) {
            if (i == index) {
                continue;
            }
            newRocks[k++] = rocks[i];
        }

        rocks = newRocks;
    }

    /**
     * This method returns the Black Bag's Number.
     * 
     * @return the black bag's number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * This method returns the asigned White Bag.
     * 
     * @return the asigned white bag.
     */
    public WhiteBag getAssignedWhiteBag() {
        return assignedWhiteBag;
    }

    /**
     * This method is used to returns the bag's rocks
     */
    @Override
    public Rock[] getRocks() {
        return super.getRocks();
    }

    /**
     * This is the toString method for the class which is used to display the class
     * in a more readable user friendly format.
     */
    @Override
    public String toString() {
        String str = "\n---\nBlack Bag " + number + "\nRocks ";
        for (Rock rock : rocks) {
            str += rock.toString();
        }
        return str;
    }
}
