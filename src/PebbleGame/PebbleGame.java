package PebbleGame;

public class PebbleGame {

    class Player implements Runnable{

        Bag bag;
        int currentWeight;


        public void run() {
            
            /**
             * When game begins:
             * 
             * Take 10 rocks from bag
             * if not equal to 100:
             *      discard a rock
             *      take another rock (while bag not empty)
             * if bag empty:
             *      empty white bag into associated black bag
             * 
             * if equal to 100: 
             *      you win
             */
        }

        public void discardRock(Rock rock) {

            bag.addRock(rock); //Adding the rock to a corresponding white bag

            // TODO: Take another rock from the black bag while !empty
            
        }

        public void drawRock() {

        }
        
    }
    public static void main(String[] args) {
        UserInterface myUserInterface = new UserInterface();

        myUserInterface.displayTitleMessage();
        int numPlayers = myUserInterface.askNumPlayers();

        String[] bagLocations = {"", "", ""};

        for (int i = 0; i < 3; i++) {
            bagLocations[i] = myUserInterface.askBagLocation(i);
        }

        System.out.println(numPlayers);
        System.out.println(bagLocations);
    }
}
