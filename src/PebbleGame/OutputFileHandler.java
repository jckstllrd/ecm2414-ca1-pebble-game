package PebbleGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputFileHandler {

    private String fileName;
    private String playerName;

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

    public void writeDrawnRockMessage(int rockWeight, int bagNum) {
        String message = String.format("%s has drawn a %d from BlackBag%d", playerName, rockWeight, bagNum);
        writeMessageToFile(message);
    }

    public void writeDiscardRockMessage(int rockWeight, int bagNum) {
        String message = String.format("%s has discarded a %d to WhiteBag%d", playerName, rockWeight, bagNum);
        writeMessageToFile(message);
    }

    public void writeAllRocksMessage(String rocks) {
        String message = String.format("%s hand is %s", playerName, rocks);
        writeMessageToFile(message);
    }
}
