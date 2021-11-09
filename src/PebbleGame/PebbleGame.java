package PebbleGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

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
        private WhiteBag nextDiscardBag;
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
        private synchronized void beginGame() {
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
            if (totalWeight == 700) {
                return true;
            } else {

                return false;
            }
        }

        /**
         * This method is used to discrad a random rock from the players rocks.
         */
        public void discardRock() {
            Random rand = new Random();
            int index = rand.nextInt(rocks.length);

            Rock[] newRocks = new Rock[rocks.length - 1];
            for (int i = 0, k = 0; i < rocks.length; i++) {
                if (i == index) {
                    continue;
                }

                newRocks[k++] = rocks[i];
            }
            nextDiscardBag.addToWhiteBag(rocks[index]);
            // BagHandler.getNextDisgardBag().addToWhiteBag(rocks[index]);

            outputFileHandler.writeDiscardRockMessage(rocks[index].weight, nextDiscardBag.number);
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
         * @return nextRock the next rock
         */
        private Rock drawRock(Boolean isSetup) {
            Random rand = new Random();

            BlackBag blackBag = blackBags[rand.nextInt(blackBags.length)];
            AtomicInteger amountRocks = new AtomicInteger(blackBag.rocks.length);
            if (amountRocks.get() == 0) {
                // System.out.println("Black bag " + blackBag.number + " is empty!");
                // System.out.println("The white bag contains " +
                // Arrays.toString(blackBag.assignedWhiteBag.rocks));
                blackBag.assignedWhiteBag.drainWhiteBag();
            }
            System.out.println("black bag contains" + amountRocks.get());
            int index = rand.nextInt(amountRocks.get());

            Rock nextRock = blackBag.rocks[index];

            if (nextRock == null) {
                System.out.println(Arrays.toString(blackBag.rocks));
                System.out.println("This rock has a value of null");
            }

            blackBag.removeRock(index);

            if (!isSetup) {
                outputFileHandler.writeDrawnRockMessage(nextRock.weight, blackBag.number);
            }

            this.nextDiscardBag = blackBag.assignedWhiteBag;
            // BagHandler.updateNextDisguard(blackBag.assignedWhiteBag);
            return nextRock;
        }
    }

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

    private static Boolean createbags(UserInterface myUserInterface, String[] bagLocations, int numPlayers) {
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
                return false;
            } catch (IOException e) {
                myUserInterface.displayErrorMessage(e.getMessage());
                return false;
            } catch (InvalidRockWeightException e) {
                myUserInterface.displayErrorMessage(e.getMessage());
                return false;
            } catch (Exception e) {
                return false;
            }

        } while (true);
        return true;
    }

    public static void main(String[] args) throws IOException, InvalidRockWeightException {
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