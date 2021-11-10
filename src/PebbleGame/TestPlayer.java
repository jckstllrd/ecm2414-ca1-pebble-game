package PebbleGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.lang.reflect.Method;

import org.junit.Test;

import PebbleGame.PebbleGame.Player;

public class TestPlayer {

    public static Player testPlayer;

    @Test
    public void test_constructor() {
        testPlayer = new Player(1);

        assertEquals("player1", testPlayer.getName());
        assertInstanceOf(OutputFileHandler.class, testPlayer.getOutputFileHandler());
    }

    // Attmpted to use Java Reflection but was unable to get it to work.
    // @Test
    // public void test_hasWon() throws Exception {
    // testPlayer = new Player(1);

    // Method begindgameMethod = Player.class.getDeclaredMethod("beginGame");
    // begindgameMethod.setAccessible(true);
    // begindgameMethod.invoke(testPlayer);

    // Method hasWonMethod = Player.class.getDeclaredMethod("hasWon");
    // hasWonMethod.setAccessible(true);

    // Boolean returnValue = (Boolean) hasWonMethod.invoke(testPlayer);

    // assertEquals(false, returnValue);
    // }
}
