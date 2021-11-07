package PebbleGame;

/**
 * WhiteBag
 */
public class WhiteBag extends Bag {

    BlackBag assignedBlackBag;
    Rock[] rocks = {};
    int number;

    public WhiteBag() {

    }

    public void addToWhiteBag(Rock newRock) {
        Rock[] newRocks = new Rock[rocks.length + 1];
        for (int i = 0; i < rocks.length; i++) {
            newRocks[i] = rocks[i];
        }
        newRocks[rocks.length] = newRock;
        rocks = newRocks;
    }

    public void drainWhiteBag() {
        Rock[] newRocks = new Rock[0];
        for (int i = 0; i < rocks.length; i++) {

            assignedBlackBag.refillBag(rocks[i]);
        }
        rocks = newRocks;
    }

    public void assignBlackBag(int bagNumber, BlackBag assignedBlackBag) {
        this.number = bagNumber;
        this.assignedBlackBag = assignedBlackBag;
    }

    @Override
    public String toString() {
        return "\n---\nBlack Bag " + number + "Rocks" + rocks.toString();
    }
}