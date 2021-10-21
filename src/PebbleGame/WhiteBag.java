package PebbleGame;

/**
 *  WhiteBag
 */
public class WhiteBag extends Bag{

    BlackBag assignedBlackBag;
    int number;

    public WhiteBag() {

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