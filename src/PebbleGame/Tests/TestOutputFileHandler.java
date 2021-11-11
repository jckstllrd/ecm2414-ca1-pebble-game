package PebbleGame.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import PebbleGame.OutputFileHandler;

public class TestOutputFileHandler {

    OutputFileHandler fileHandler;
    String playerName;
    String fileName;

    @Test
    public void test_constructor() throws IOException {
        fileHandler = new OutputFileHandler("testFile");

        File f = new File("testFile_output.txt");

        assertEquals(true, f.exists()); 
    }

    @Test
    public void test_writeDrawnRockMessages() throws IOException {
        fileHandler = new OutputFileHandler("testFile");
        fileHandler.writeDrawnRockMessage(10, 1);

        Path path = Paths.get("testFile_output.txt");
        String allText = Files.readString(path);

        System.out.println(allText);

        assertEquals("", allText);
    }

    @Test
    public void test_writeDiscardRockMessages() throws IOException {
        fileHandler = new OutputFileHandler("testFile");
        fileHandler.writeDiscardRockMessage(10, 1);

        Path path = Paths.get("testFile_output.txt");
        String allText = Files.readString(path);

        System.out.println(allText);

        assertEquals("", allText);
    }

    @Test
    public void test_writeAllRocksMessages() throws IOException {
        fileHandler = new OutputFileHandler("testFile");
        String rocks = "[10]";
        fileHandler.writeAllRocksMessage(rocks);

        Path path = Paths.get("testFile_output.txt");
        String allText = Files.readString(path);

        System.out.println(allText);

        assertEquals("", allText);
    }
}
