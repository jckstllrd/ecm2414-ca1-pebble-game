package PebbleGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is used to define an OutputFileHandler object which is used to write text to each
 * players individual file which describes their actions when the program is running. It has a 
 * the file name and the player's name as attributes.
 */
public class OutputFileHandler {

    /**
     * This is the name of the file the OutputFileHandler needs to write to.
     */
    private String fileName;

    /**
     * This is the player's name the file refers to.
     */
    private String playerName;

    /**
     * This is the constructor for a OutputFileHandler, it takes in the player's name as an
     * argument and then creates an acceptable filename and file which cab later be written to.
     * 
     * @param playerName The name of the player the file will refer to.
     */
    public OutputFileHandler(String playerName) {
        this.playerName = playerName;
        fileName = playerName+"_output.txt";

        File myFile = new File(fileName);
        try {
            int i = 1;
            while(!myFile.createNewFile()){
                i++;
                fileName = playerName + "_v" + i + "_output.txt";
                myFile = new File(fileName);
            }
        } catch (IOException e) {
            System.out.println("Error Creating file for "+fileName);
        }
    }

    /**
     * This method is used privately inside the class to write a given message to the file given
     * by the filename attribute.
     * 
     * @param message The message to be writted to the file.
     */
    private void writeMessageToFile(String message) {
        try {
            FileWriter myFileWriter = new FileWriter(fileName, true);
            BufferedWriter myBufferedWriter = new BufferedWriter(myFileWriter);
            PrintWriter myPrintWriter = new PrintWriter(myBufferedWriter);

            myPrintWriter.println(message);
            myPrintWriter.close();
        } catch (IOException e) {
            System.out.println("Error write to file for "+fileName);
        }
    }

    /**
     * This method is used to write the message shown underneath to the file whenever the player draws
     * a new rock from a black bag.
     * 
     * "(a) has drawn a (b) from BlackBag(c)"
     * 
     * where 
     *      (a) is the player's name
     *      (b) is the rock's weight
     *      (c) is the black bag's number
     * 
     * @param rockWeight The weight of the rock the player has drawn.
     * @param bagNum The location the player has drawn the rock from.
     */
    public void writeDrawnRockMessage(int rockWeight, int bagNum) {
        String message = String.format("%s has drawn a %d from BlackBag%d", playerName, rockWeight, bagNum);
        writeMessageToFile(message);
    }

    /**
     * This method is used to write the message shown underneath to the file whenever the player discards
     * a rock from their rocks.
     * 
     * "(a) has dicarded a (b) to WhiteBag(c)"
     * 
     * where 
     *      (a) is the player's name
     *      (b) is the rock's weight
     *      (c) is the white bag's number
     * 
     * @param rockWeight The weight of the rock the player has discarded.
     * @param bagNum The location the player has discarded the rock to.
     */
    public void writeDiscardRockMessage(int rockWeight, int bagNum) {
        String message = String.format("%s has discarded a %d to WhiteBag%d", playerName, rockWeight, bagNum);
        writeMessageToFile(message);
    }

    /**
     * This method is used to write the message shown underneath to the file describing what the rocks are
     * the player has currently in thir hand.
     * 
     * "(a) hand is (b)"
     * 
     * where
     *      (a) is the player's name
     *      (b) is the player's rocks in the form [rock1, rock2, .... rockn]
     * 
     * @param rocks The rocks the player has.
     */
    public void writeAllRocksMessage(String rocks) {
        String message = String.format("%s hand is %s", playerName, rocks);
        writeMessageToFile(message);
    }
}
