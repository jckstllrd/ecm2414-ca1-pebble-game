package PebbleGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

/**
 * This class contains the Tests for the BagHandler Class.
 */
public class TestBlackBag {

    BlackBag testBlackBag;
    WhiteBag testWhiteBag;
    Rock[] testRocks;

    @BeforeAll
    public void setUp() {
        testWhiteBag = new WhiteBag();
        testRocks = new Rock[] {};
    }

    @Test
    public void test_constructor() {
        testBlackBag = new BlackBag(1, testWhiteBag, testRocks);
        assertEquals(BlackBag.class, testBlackBag.getClass());
        assertEquals(1, testBlackBag.getNumber());
    }
}