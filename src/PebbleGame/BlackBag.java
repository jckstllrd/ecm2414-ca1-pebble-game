package PebbleGame;

/**
 * BlackBack
 */
public class BlackBag extends Bag {

    WhiteBag assignedWhiteBag;
    Rock[] rocks;
    int number;

    public void refillBag(Rock newRock) {
        Rock[] newRocks = new Rock[rocks.length + 1];
        newRocks[rocks.length + 1] = newRock;
        rocks = newRocks;
    }

    public void removeRock(int index) {
        Rock[] newRocks = new Rock[rocks.length - 1];
        for (int i = 0, k = 0; i < rocks.length; i++) {
            if (i == index) {
                continue;
            }
            newRocks[k++] = rocks[i];
        }
        Rock removedRock = rocks[index];
        assignedWhiteBag.addToWhiteBag(removedRock);
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
