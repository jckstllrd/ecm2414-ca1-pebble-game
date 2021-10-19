package PebbleGame;

public class PebbleGame {

    class Player {
    
        
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
