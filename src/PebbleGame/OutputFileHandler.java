package PebbleGame;

import java.io.File;
import java.io.IOException;

public class OutputFileHandler {

    private String fileName;

    public OutputFileHandler(String playerName) {
        fileName = playerName+"_output.txt";

        File myFile = new File(fileName);
        try {
            int i = 0;
            while(!myFile.createNewFile()){
                i++;
                fileName = fileName + "v" + i;
            }
        } catch (IOException e) {
            System.out.println("Error Creating file for "+fileName);
        }
    }

    private void writeMessageToFile(String message) {

    }

    public void writeDrawnRockMessage(String PlayerName, int rockWeight, String bagName) {
        String message = String.format("%s has drawn a %s from %s", PlayerName, rockWeight, bagName);
        writeMessageToFile(message);
    }

    // Currently working on this

    public void wirteDiscardRockMessage(String playerName, int rockWeight, ) {
        String message = String.format("%s has discarded a %s to %s", args)
    }

    public void writeAllRocksMessage() {
        
    }
}
