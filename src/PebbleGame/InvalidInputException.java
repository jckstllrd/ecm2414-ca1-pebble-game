package PebbleGame;

/**
 * This class defines a custom Exception Class which us used for when a Invalid Input is given
 * by the user in the UserInterface.
 */
public class InvalidInputException extends Exception{

    /**
     * This is the constructor class for an InvalidInputExcption, for when a specific message
     * is not given as an argument.
     */
    public InvalidInputException() {
        super("Unacceptable input - given input does not fit the specified requirements.");

    }

    /**
     * This is the constructor class for a InvalidInputException, for when a specific message
     * is given as an argument and then used to be displayed as the Exceptions message.
     * 
     * @param message The error message.
     */
    public InvalidInputException(String message) {
        super(message);
    }
    
}
