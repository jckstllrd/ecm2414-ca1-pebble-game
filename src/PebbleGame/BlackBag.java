package PebbleGame;

/**
 * BlackBack
 */
public class BlackBag extends Bag {

    WhiteBag assignedWhiteBag;
    Rock[] rocks;
    int number;

    public synchronized void refillBag(Rock newRock) {
        Rock[] newRocks = new Rock[rocks.length + 1];
        for (int i = 0; i < rocks.length; i++) {
            newRocks[i] = rocks[i];
        }
        newRocks[rocks.length] = newRock;
        rocks = newRocks;
    }

    public synchronized void removeRock(int index) {
        Rock[] newRocks = new Rock[rocks.length - 1];
        for (int i = 0, k = 0; i < rocks.length; i++) {
            if (i == index) {
                continue;
            }
            newRocks[k++] = rocks[i];
        }

        rocks = newRocks;
    }

    public BlackBag(int bagNumber, WhiteBag assignedWhiteBag, Rock[] rocks) {
        this.number = bagNumber;
        this.assignedWhiteBag = assignedWhiteBag; // Pairs black to white bag for when empty
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
