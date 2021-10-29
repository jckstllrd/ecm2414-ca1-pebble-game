package PebbleGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PebbleGame {

    static WhiteBag[] whiteBags = new WhiteBag[3];
    static BlackBag[] blackBags = new BlackBag[3];

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
    
    private static Rock[] getRocksFromFile(String fileLocation, int numPlayers) throws FileNotFoundException, IOException, InvalidRockWeightException{
        BufferedReader br = new BufferedReader(new FileReader(fileLocation));
        String line;
        String[] weights = {};
        while ((line = br.readLine()) != null) {
            weights = line.split(",");
        }
        br.close();

        if (numPlayers * 11 < weights.length) {
            throw new InvalidRockWeightException("File doesn't have enough weights for number of players entered.");
        }

        Rock[] rocks = new Rock[weights.length];
        for (int i = 0; i < rocks.length; i++) {
            if (Integer.parseInt(weights[i]) <= 0) {
                throw new InvalidRockWeightException("A Rock weight in a given file is non-positive.");
            }
            rocks[i] = new Rock(Integer.parseInt(weights[i]));
        }
        return rocks;
    }

    private static void createbags(UserInterface myUserInterface, String[] bagLocations, int numPlayers) {
        do {
            try {
                for (int i = 0; i < bagLocations.length; i++) {
                    Rock[] rocks = getRocksFromFile(bagLocations[i], numPlayers);
                    whiteBags[i] = new WhiteBag();
                    blackBags[i] = new BlackBag(i, whiteBags[i], rocks);
                }
                break;
            } catch (FileNotFoundException e) {
                myUserInterface.displayErrorMessage(e.getMessage() + "\nPlease re-enter the file locations.");
            } catch (IOException e) {
                myUserInterface.displayErrorMessage(e.getMessage());
            } catch (InvalidRockWeightException e) {
                myUserInterface.displayErrorMessage(e.getMessage());
            }
            
        } while (true);
    }

    public static void main(String[] args) throws IOException, InvalidRockWeightException {
        UserInterface myUserInterface = new UserInterface();

        myUserInterface.displayTitleMessage();

        int numPlayers = myUserInterface.askNumPlayers();

        String[] bagLocations = new String[3];

        for (int i = 0; i < 3; i++) {
            bagLocations[i] = myUserInterface.askBagLocation(i);
        }

        createbags(myUserInterface, bagLocations, numPlayers);

        for (BlackBag bag : blackBags) {
            System.out.println(bag.toString());
        }
    }
}