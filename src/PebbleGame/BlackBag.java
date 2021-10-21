package PebbleGame;

/**
 * BlackBack
 */
public class BlackBag extends Bag{

    WhiteBag assignedWhiteBag;
    Rock[] rocks;
    int number;

    public BlackBag(int bagNumber, WhiteBag assignedWhiteBag, Rock[] rocks) {
        this.number = bagNumber;
        this.assignedWhiteBag = assignedWhiteBag; //Pairs black to white bag for when empty
        this.rocks = rocks;
        assignedWhiteBag.assignBlackBag(bagNumber, this);
    }

    @Override
    public String toString() {
        String str = "\n---\nBlack Bag " + number + "\nRocks ";
        for (Rock rock : rocks) {
            str += rock.toString();
        }
        return str;
    }
}
