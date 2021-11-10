package PebbleGame;

import java.util.Scanner;

/**
 * This method is used to define the User Interface which control all of the
 * interactions with the user, it has a Scanner object attribute which is used
 * to read inputs from the user.
 */
public class UserInterface {

    /**
     * This is the Scanner objet which is used to read inputs from the user.
     */
    Scanner sc = new Scanner(System.in);

    /**
     * This method is used to get an input from the user using the class's Scanner
     * attribute.
     * 
     * @return the user's input
     */
    private String getInput() {
        String input = sc.nextLine();
        checkIfExit(input);
        return input;
    }

    /**
     * This method is used to check if the user has requested to Exit.
     * 
     * @param input The users input.
     */
    private void checkIfExit(String input) {
        if (input.equals("E")) {
            System.exit(0);
        }
    }

    /**
     * This method is used to display the title message when the program is first
     * run.
     */
    public void displayTitleMessage() {
        System.out.println(
                "Welcome to the PebbleGame!!\nYou will be asked to enter the number of players.\nand then for the location of three files in turn containing comma separated integer values for the pebble weights.\nThe integer values must be strictly positive.\nThe game will then be simulated, and output written to files in this directory.");
    }

    /**
     * This method is used to display an error message.
     * 
     * @param error A description of the error that has occured.
     */
    public void displayErrorMessage(String error) {
        System.out.println(error);
    }

    /**
     * This method is used to ask the user for the number of players, it also
     * carries out checks to ensure that the number of players is an integer rather
     * than text and that the number is greater than zero.
     * 
     * @return returns the number of players entered by the user as an integer.
     */
    public int askNumPlayers() {
        do {
            try {
                System.out.println("Please enter the number of players:");
                String stringNumPlayers = getInput();
                int numPlayers = Integer.parseInt(stringNumPlayers);
                if (numPlayers <= 0) {
                    throw new InvalidInputException(
                            "Unacceptable Input - Number of Players Entered was not a positive number");
                }
                return numPlayers;
            } catch (NumberFormatException e) {
                displayErrorMessage("Error with given input, please enter a number not text.");
            } catch (InvalidInputException e) {
                displayErrorMessage("Error with given input, please enter a number greater than 0.");
            }
        } while (true);
    }

    /**
     * This method is used to ask the user for the file location of a bag.
     * 
     * @param bagNumber the id of the bag the platform ants to know the location of.
     * 
     * @return the file location the user has entered
     */
    public String askBagLocation(int bagNumber) {
        System.out.printf("Please enter location of bag number %d to load:", bagNumber);
        String bagLocation = getInput();
        return bagLocation;
    }
}
