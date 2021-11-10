package PebbleGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.Test;

public class TestRock {

    Rock testRock;
    
    @Test
    public void test_constructor() {
        testRock = new Rock(10);

        assertInstanceOf(Rock.class, testRock);
    }

    @Test
    public void test_getWeight() {
        testRock = new Rock(10);

        assertEquals(10, testRock.getWeight());
    }
}
