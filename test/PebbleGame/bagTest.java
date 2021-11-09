package PebbleGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestBag {

    Bag testBag;
    Rock testRock;

    @BeforeEach
    void setUp() {
        testBag = new Bag();
        testRock = new Rock(10);
    }

    @Test
    void testAddRock() {
        Rock[] testRocks = {testRock};
        testBag.addRock(testRock);
        assertEqual(testBag.getRocks(), testRocks);
    }
}