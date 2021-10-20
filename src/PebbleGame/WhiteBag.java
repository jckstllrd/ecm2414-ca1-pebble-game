package PebbleGame;

/**
 *  WhiteBag
 */
public class WhiteBag extends Bag{

    BlackBag asignedBlackBag;
    int number;

    public WhiteBag() {

    }

    public void asignBlackBag(int bagNumber, BlackBag asignedBlackBag) {
        this.number = bagNumber;
        this.asignedBlackBag = asignedBlackBag;
    }

    @Override
    public String toString() {
        return "\n---\nBlack Bag " + number + "Rocks" + rocks.toString();
    }
}