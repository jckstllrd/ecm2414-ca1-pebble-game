package PebbleGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.Test;

import PebbleGame.PebbleGame.Player;

public class TestPlayer {

    Player testPlayer;
    
    @Test
    public void test_constructor() {
        testPlayer = new Player(1);

        assertEquals("player1", testPlayer.getName());
        assertInstanceOf(OutputFileHandler.class, testPlayer.getOutputFileHandler());
    }
}
