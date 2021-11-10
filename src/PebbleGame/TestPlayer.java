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

    @Test
    public void test_hasWon() throws Exception {
        testPlayer = new Player(1);
        Method method = Player.class.getDeclaredMethod("hasWon");
        method.setAccessible(true);

        Boolean returnValue = (Boolean) method.invoke(testPlayer);
        assertEquals(false, returnValue);
    }
}
