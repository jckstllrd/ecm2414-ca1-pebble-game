package PebbleGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class TestOutputFileHandler {

    OutputFileHandler fileHandler;
    String playerName;
    String fileName;

    @BeforeAll
    public void setup() {
        playerName = "player0";
        fileName = "player0_output.txt";
        fileHandler = new OutputFileHandler(playerName);
    }

    @Test
    public void test_writeDrawnRockMessage() throws IOException {
        int rockWeight = 10;
        int bagNum = 0;
        String message = String.format("%s has drawn a %d from BlackBag%d", playerName, rockWeight, bagNum);
        fileHandler.writeDrawnRockMessage(rockWeight, bagNum);
        Path filePath = Path.of(fileName);
        String contents = Files.readString(filePath);

        assertEquals(message, contents);
    }
}
