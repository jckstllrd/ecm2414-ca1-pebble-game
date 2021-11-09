package PebbleGame;

/**
 * This class contains the static value for where to next discuard a rock and the static method
 * of updating where to discard a rock to.
 */
public final class BagHandler {

    /**
     * This is the WhiteBag object where the next discarded rock should be stored.
     */
    static WhiteBag nextDiscuard;

    /**
     * This method returns the next WhiteBag which is a rock should be discarded to.
     * 
     * @return The WhiteBag object to discard to next
     */
    public static WhiteBag getNextDiscardBag() {
        return nextDiscuard;
    }

    /**
     * This method is used to update where next to discard a discarded rock.
     * 
     * @param newNextDisguard The new WhiteBag to next discard to.
     */
    public synchronized static void updateNextDiscard(WhiteBag newNextDisguard) {
        nextDiscuard = newNextDisguard;
    }
}
