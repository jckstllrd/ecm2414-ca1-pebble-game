package PebbleGame;

public class PebbleGame {

    class Player implements Runnable{

        Bag bag;
        int currentWeight;


        public void run() {
            
        }

        public void discardRock(Rock rock) {
            bag.addRock(rock);
            takeRock(); // !!!! WILL FLESH ALL OF THIS OUT AFTER TRAINING !!!!
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
