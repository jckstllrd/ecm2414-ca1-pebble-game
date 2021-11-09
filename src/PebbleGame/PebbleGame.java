package PebbleGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.*;

public class PebbleGame {

    static WhiteBag[] whiteBags = new WhiteBag[3];
    static BlackBag[] blackBags = new BlackBag[3];

    static class Player implements Runnable {

        private Rock[] rocks = new Rock[10];
        private String name;
        private WhiteBag nextDiscardBag;
        private OutputFileHandler outputFileHandler;

        public Player(int index) {
            this.name = "player" + String.valueOf(index);
            outputFileHandler = new OutputFileHandler(name);
        }

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
                playGame();
            }
            System.out.println(name + ": Has Won");
        }

        private synchronized void playGame() {
            discardRock();
            addRock(drawRock(false));
        }

        private synchronized void beginGame() {
            for (int i = 0; i < 10; i++) {
                Rock nextRock = this.drawRock(true);
                rocks[i] = nextRock;
            }
            System.out.println(name + "Has collected their starting rocks");
        }

        private Boolean hasWon() {
            int totalWieght = 0;

            for (Rock rock : rocks) {
                totalWieght += rock.weight;
            }
            if (totalWieght == 100) {
                return true;
            } else {

                return false;
            }
        }

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
            nextDiscardBag.addToWhiteBag(rocks[index]);
            // BagHandler.getNextDisgardBag().addToWhiteBag(rocks[index]);
            System.out.println(
                    "Adding to White bag " + nextDiscardBag.number + ", size is: " + nextDiscardBag.rocks.length);
            outputFileHandler.writeDiscardRockMessage(rocks[index].weight, nextDiscardBag.number);
            outputFileHandler.writeAllRocksMessage(Arrays.toString(newRocks));
            rocks = newRocks;
        }

        public synchronized void addRock(Rock newRock) {
            Rock[] newRocks = new Rock[rocks.length + 1];
            for (int i = 0; i < rocks.length; i++) {
                newRocks[i] = rocks[i];
            }
            newRocks[9] = newRock;
            rocks = newRocks;

            outputFileHandler.writeAllRocksMessage(Arrays.toString(rocks));
        }

        private synchronized Rock drawRock(Boolean isSetup) {
            Random rand = new Random();
            int length = blackBags.length;

            BlackBag blackBag = blackBags[rand.nextInt(length)];

            if (length == 0) {
                System.out.println("Black bag " + blackBag.number + " is empty!");
                System.out.println("The white bag contains " + Arrays.toString(blackBag.assignedWhiteBag.rocks));
                blackBag.assignedWhiteBag.drainWhiteBag();
            }

            int index = rand.nextInt(length);
            Rock nextRock = blackBag.rocks[index];

            if (nextRock == null) {
                System.out.println(Arrays.toString(blackBag.rocks));
                System.out.println("This rock has a value of null");
            }

            blackBag.removeRock(index);

            if (!isSetup) {
                outputFileHandler.writeDrawnRockMessage(nextRock.weight, blackBag.number);
            }
            System.out.println("Taking from Black bag " + blackBag.number + ", size is: " + length);

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