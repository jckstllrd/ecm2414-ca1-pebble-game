package PebbleGame;

public final class BagHandler {
    static WhiteBag nextDisguard;

    public static WhiteBag getNextDisgardBag() {
        return nextDisguard;
    }

    public static void updateNextDisguard(WhiteBag newNextDisguard) {
        nextDisguard = newNextDisguard;
    }
}
