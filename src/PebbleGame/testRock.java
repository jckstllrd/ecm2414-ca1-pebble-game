package PebbleGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class testRock {

    Rock testRock;
    int testWeight;

    @Test
    public void test_constructor() {
        testWeight = 10;
        testRock = new Rock(testWeight);

        assertEquals(testRock.getWeight(), testWeight);
    }
}
