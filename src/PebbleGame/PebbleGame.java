package PebbleGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * This class is the main controller for the entire platform and has a nested
 * for each player in the game. It contains methods for creating the White and
 * Black Bags as well as the threads and players themselves. It has an array of
 * three WhiteBags and an array of three BlackBags.
 */
public class PebbleGame {

    /**
     * This is the array of three WhiteBag objects.
     */
    static WhiteBag[] whiteBags = new WhiteBag[3];

    /**
     * This is the array of three BlackBag objects.
     */
    static BlackBag[] blackBags = new BlackBag[3];

    /**
     * This is a nested class which is used to define a Player, which holds the
     * player's name, their rocks and their specific outputFilehandler. It has an
     * array of rocks, a string value for the name and an OutputFileHandler object
     * as attributes.
     */
    static class Player implements Runnable {

        /**
         * This is the array of rocks the player has.
         */
        private Rock[] rocks = new Rock[10];

        /**
         * This is the players name.
         */
        private String name;
        private OutputFileHandler outputFileHandler;

        /**
         * This is te constructor for a Player object, it takes in an inex (id number)
         * for the player which is then used to create the player name.
         * 
         * @param index Index of the player (player number).
         */
        public Player(int index) {
            this.name = "player" + String.valueOf(index);
            outputFileHandler = new OutputFileHandler(name);
        }

        /**
         * This method is used to let the player "play" the game and loops discuarding
         * and drawing rocks until they win or are interupted by someone else winning.
         */
        public void run() {
            System.out.println(name + ": Has Started");
            /**
             * When game begins:
             * 
             * Take 10 rocks from bag if not equal to 100: discard a rock take another rock
             * (while bag not empty) if bag empty: empty white bag into associated black bag
             * 
             * if equal to 100: you win
             */

            beginGame();

            while (!hasWon()) {
                discardRock();
                addRock(drawRock(false));
            }
            System.out.println(name + ": Has Won");
            System.exit(0);
        }

        /**
         * This method is used to draw the initial ten rocks each player needs before
         * they can start playing the game.
         */
        private void beginGame() {
            for (int i = 0; i < 10; i++) {
                Rock nextRock = this.drawRock(true);
                rocks[i] = nextRock;
            }
            System.out.println(name + "Has collected their starting rocks");
        }

        /**
         * This method checks if the player's rock's weights equal 100 and hence if they
         * have won.
         * 
         * @return a boolean value true or false depending if they have won.
         */
        private Boolean hasWon() {
            int totalWeight = 0;

            for (Rock rock : rocks) {
                totalWeight += rock.weight;
            }
            if (totalWeight == 100) {
                return true;
            } else {

                return false;
            }
        }

        /**
         * This method is used to discrad a random rock from the players rocks.
         */
        public synchronized void discardRock() {
            Random rand = new Random();
            int index = rand.nextInt(rocks.length);

            Rock[] newRocks = new Rock[rocks.length - 1];
            for (int i = 0, k = 0; i < rocks.length; i++) {
                if (i == index) {
                    continue;
                }

                newRocks[k++] = rocks[i];
            }
            BagHandler.getNextDiscardBag().addToWhiteBag(rocks[index]);
            
            outputFileHandler.writeDiscardRockMessage(rocks[index].weight, BagHandler.getNextDiscardBag().number);
            outputFileHandler.writeAllRocksMessage(Arrays.toString(newRocks));
            rocks = newRocks;
        }

        /**
         * This method is used to add a rock to the players array of rocks.
         * 
         * @param newRock The new rock which has been added.
         */
        public void addRock(Rock newRock) {
            Rock[] newRocks = new Rock[rocks.length + 1];
            for (int i = 0; i < rocks.length; i++) {
                newRocks[i] = rocks[i];
            }
            newRocks[9] = newRock;
            rocks = newRocks;

            outputFileHandler.writeAllRocksMessage(Arrays.toString(rocks));
        }

        /**
         * This method is used to draw a new rock.
         * 
         * @param isSetup a boolean value for if this is part of the 10 initial draws
         *                each player does.
         * @return the next rock to be drawn
         */
        private synchronized Rock drawRock(Boolean isSetup) {
            Random rand = new Random();

            BlackBag blackBag = blackBags[rand.nextInt(blackBags.length)];

            if (blackBag.getRocks().length == 0) {
                blackBag.getAssignedWhiteBag().drainWhiteBag();
            }

            int index = rand.nextInt(blackBag.getRocks().length);

            Rock nextRock = blackBag.getRocks()[index];

            if (nextRock == null) {
                System.out.println(Arrays.toString(blackBag.getRocks()));
                System.out.println("This rock has a value of null");
            }

            blackBag.removeRock(index);

            if (!isSetup) {
                outputFileHandler.writeDrawnRockMessage(nextRock.weight, blackBag.getNumber());
            }

            BagHandler.updateNextDiscard(blackBag.getAssignedWhiteBag());

            return nextRock;
        }
    }

    /**
     * This method is used to get an array of rocks from a file and to check that the file meets
     * the neccesary criteria. Meaning that it does not have any weights which are non-integer values
     * less than 0 it will throw an exception orif the total number of weights per file is at least
     * eleven times the number of players then it will throw an exception.
     * 
     * @param fileLocation the location of the file storing the rock weights.
     * @param numPlayers the number of players in the game.
     * 
     * @return the array of rocks with the weights given in the file given by the file location.
     * 
     * @throws FileNotFoundException This is thrown if the file is not found at the location given.
     * @throws IOException This is thrown if ther eis an issue reaing the specified file.
     * @throws InvalidRockWeightException   This is thrown if the file contains a rock which is either non
     *                                      zero, not an integer or the total number of rocks is less than
     *                                      eleven times the number of players.
     */
    private static Rock[] getRocksFromFile(String fileLocation, int numPlayers)
            throws FileNotFoundException, IOException, InvalidRockWeightException {
        BufferedReader br = new BufferedReader(new FileReader(fileLocation));
        String line;
        String[] weights = {};
        while ((line = br.readLine()) != null) {
            weights = line.split(",");
        }

        br.close();

        if (numPlayers * 11 > weights.length) {
            System.out.println(weights.length);
            throw new InvalidRockWeightException("File doesn't have enough weights for number of players entered.");
        }

        Rock[] rocks = new Rock[weights.length];
        for (int i = 0; i < rocks.length; i++) {
            if (weights[i].trim().equals("")) {
                throw new InvalidRockWeightException("File contains a entry with no value (emptyspace).");
            }
            if (Integer.parseInt(weights[i]) <= 0) {
                throw new InvalidRockWeightException("A Rock weight in a given file is non-positive.");
            }
            rocks[i] = new Rock(Integer.parseInt(weights[i]));
        }
        return rocks;
    }

    /**
     * This method is used to create the different bags used by the game. The method will create the three
     * black bags each filled with rocks specified by the given file locations, and the three white bags where
     * rocks will be discraded to. These will be stored in the two arrays screated as attributes of this class.
     * 
     * @param myUserInterface The user interface to use to interact with the user if there are any errors.
     * @param bagLocations An array of the file loctions of the three bags.
     * @param numPlayers The number of players in the game
     * 
     * @return whether the opperation was a success (true) or not (false).
     */
    private static Boolean createbags(UserInterface myUserInterface, String[] bagLocations, int numPlayers) {
        do {
            try {
                for (int i = 0; i < bagLocations.length; i++) {
                    Rock[] rocks = getRocksFromFile(bagLocations[i], numPlayers);
                    whiteBags[i] = new WhiteBag();
                    blackBags[i] = new BlackBag(i, whiteBags[i], rocks);
                }
                System.out.println(whiteBags.length);
                break;
            } catch (FileNotFoundException e) {
                myUserInterface.displayErrorMessage(e.getMessage() + "\nPlease re-enter the file locations.");
                return false;
            } catch (IOException e) {
                myUserInterface.displayErrorMessage(e.getMessage());
                return false;
            } catch (InvalidRockWeightException e) {
                myUserInterface.displayErrorMessage(e.getMessage());
                return false;
            } catch (Exception e) {
                throw e;
            }

        } while (true);
        return true;
    }

    /**
     * This is the main method which runs when the platform is run. After asing the user for the eccesary details 
     * It initiates all the neccesary objects and then uses these objects to create threads for each of the players
     * which then all run simultaneously to find a winner to the game.
     * 
     * @param args the arguents given when the program is run
     * 
     */
    public static void main(String[] args) {
        UserInterface myUserInterface = new UserInterface();

        myUserInterface.displayTitleMessage();

        int numPlayers = myUserInterface.askNumPlayers();

        String[] bagLocations = new String[3];

        for (int i = 0; i < 3; i++) {
            bagLocations[i] = myUserInterface.askBagLocation(i);
        }

        Boolean success = createbags(myUserInterface, bagLocations, numPlayers);

        while (!success) {
            for (int i = 0; i < 3; i++) {
                bagLocations[i] = myUserInterface.askBagLocation(i);
            }
            success = createbags(myUserInterface, bagLocations, numPlayers);
        }

        // for (BlackBag bag : blackBags) {
        // System.out.println(bag.toString());
        // }

        Player[] players = new Player[numPlayers];
        Thread[] threads = new Thread[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(i);
        }
        for (int i = 0; i < players.length; i++) {
            threads[i] = new Thread(players[i]);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}