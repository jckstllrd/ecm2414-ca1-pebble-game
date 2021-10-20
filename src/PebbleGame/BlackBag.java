package PebbleGame;

/**
 * BlackBack
 */
public class BlackBag extends Bag{

    WhiteBag asignedWhiteBag;
    Rock[] rocks;
    int number;

    public BlackBag(int bagNumber, WhiteBag asignedWhiteBag, Rock[] rocks) {
        this.number = bagNumber;
        this.asignedWhiteBag = asignedWhiteBag; //Pairs black to white bag for when empty
        this.rocks = rocks;
        asignedWhiteBag.asignBlackBag(bagNumber, this);
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
