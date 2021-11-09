package PebbleGame;

/**
 * This class defines a custom Exception Class which is used when a rock weight in a
 * given file does not match the given requirements of a rock wieight in the specification.
 */
public class InvalidRockWeightException extends Exception{

    /**
     * This is the constructor class for an InvalidRockWeightException, for when a specific
     * message is not given as an argument.
     */
    public InvalidRockWeightException() {
        super("Error A rock in the given files does not match the specifed requirements.");
    }

    /**
     * This is the constructor class for an InvalidRockWeightException, for when a specific
     * message is given as an argument.
     * 
     * @param message The error message.
     */
    public InvalidRockWeightException(String message) {
        super(message);
    }
    
}
