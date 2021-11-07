package PebbleGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class PebbleGame {

    static WhiteBag[] whiteBags = new WhiteBag[3];
    static BlackBag[] blackBags = new BlackBag[3];

    static class Player implements Runnable {

        Rock[] rocks = new Rock[10];
        int currentWeight;

        public void run() {

            /**
             * When game begins:
             * 
             * Take 10 rocks from bag if not equal to 100: discard a rock take another rock
             * (while bag not empty) if bag empty: empty white bag into associated black bag
             * 
             * if equal to 100: you win
             */
            System.out.println(Arrays.toString(blackBags[0].rocks));
            beginGame();
            System.out.println(Arrays.toString(rocks));
            System.out.println(Arrays.toString(blackBags[0].rocks));

            while (!hasWon()) {

                System.out.println(Thread.currentThread().getName() + ": Discarding Rock");

                discardRock();

                System.out.println(Thread.currentThread().getName() + ": Getting new Rock");

                addRock(drawRock());

                System.out.println(Arrays.toString(rocks));

            }

            System.out.println(Thread.currentThread().getName() + ": Has Won");
        }

        public void addRock(Rock newRock) {
            Rock[] newRocks = new Rock[rocks.length + 1];
            for (int i = 0; i < rocks.length; i++) {
                newRocks[i] = rocks[i];
            }
            newRocks[9] = newRock;
            rocks = newRocks;
        }

        private Boolean hasWon() {
            int totalWieght = 0;

            for (Rock rock : rocks) {
                totalWieght += rock.weight;
            }
            if (totalWieght == 100) {
                return true;
            } else {
                System.out.println("Not equal to 100, weight is: " + totalWieght);
                return false;
            }
        }

        private void beginGame() {
            for (int i = 0; i < 10; i++) {
                Rock nextRock = this.drawRock();
                rocks[i] = nextRock;
            }
        }

        // Does not interact with black bag
        public void discardRock() {
            Random rand = new Random();
            int index = rand.nextInt(rocks.length);
            Rock randRock = rocks[index];

            Rock[] newRocks = new Rock[rocks.length - 1];
            for (int i = 0, k = 0; i < rocks.length; i++) {
                if (i == index) {
                    continue;
                }

                newRocks[k++] = rocks[i];
            }
            rocks = newRocks;
        }

        private Rock drawRock() {
            Random rand = new Random();

            BlackBag blackBag = blackBags[rand.nextInt(blackBags.length)];

            if (blackBag.rocks.length == 0) {

                blackBag.assignedWhiteBag.drainWhiteBag();
            }

            int index = rand.nextInt(blackBag.rocks.length);
            Rock nextRock = blackBag.rocks[index];
            blackBag.removeRock(index);
            return nextRock;
        }

    }

    private static Rock[] getRocksFromFile(String fileLocation, int numPlayers)
            throws FileNotFoundException, IOException, InvalidRockWeightException {
        BufferedReader br = new BufferedReader(new FileReader(fileLocation));
        String line;
        String[] weights = {};
        while ((line = br.readLine()) != null) {
            if (line.contains(" ")) {
                weights = line.split(" |\\,");
            } else {
                weights = line.split(",");
            }

        }
        br.close();

        if (numPlayers * 11 > weights.length) {
            System.out.println(weights.length);
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

        // for (BlackBag bag : blackBags) {
        // System.out.println(bag.toString());
        // }

        Player player = new Player();
        Thread p1 = new Thread(player);
        p1.start();
    }
}