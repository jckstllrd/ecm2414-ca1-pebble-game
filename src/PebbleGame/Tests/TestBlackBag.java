package PebbleGame.Tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import PebbleGame.BlackBag;
import PebbleGame.Rock;
import PebbleGame.WhiteBag;

/**
 * This class contains the Tests for the Black Bag Class.
 */
public class TestBlackBag {

    BlackBag testBlackBag;
    WhiteBag testWhiteBag;
    Rock[] testRocks;

    @Test
    public void test_constructor() {
        testWhiteBag = new WhiteBag();
        testRocks = new Rock[] {};
        testBlackBag = new BlackBag(1, testWhiteBag, testRocks);
        assertEquals(BlackBag.class, testBlackBag.getClass());
        assertEquals(1, testBlackBag.getNumber());
        assertEquals(testWhiteBag, testBlackBag.getAssignedWhiteBag());
        assertEquals(testRocks, testBlackBag.getRocks());
    }

    @Test
    public void test_refillBag() {
        testWhiteBag = new WhiteBag();
        testRocks = new Rock[] {};
        testBlackBag = new BlackBag(1, testWhiteBag, testRocks);

        Rock rock1 = new Rock(10);
        testRocks = new Rock[] {rock1};

        testBlackBag.refillBag(rock1);

        assertArrayEquals(testRocks, testBlackBag.getRocks());
    }

    @Test
    public void test_removeRock() {
        testWhiteBag = new WhiteBag();
        Rock rock1 = new Rock(10);
        testRocks = new Rock[] {rock1};
        testBlackBag = new BlackBag(1, testWhiteBag, testRocks);

        testBlackBag.removeRock(0);

        assertEquals(0, testBlackBag.getRocks().length);
    }

    @Test
    public void test_getNumber() {
        testWhiteBag = new WhiteBag();
        testRocks = new Rock[] {};
        testBlackBag = new BlackBag(1, testWhiteBag, testRocks);

        assertEquals(1, testBlackBag.getNumber());
    }

    @Test
    public void test_getAssigndWhiteBag() {
        testWhiteBag = new WhiteBag();
        testRocks = new Rock[] {};
        testBlackBag = new BlackBag(1, testWhiteBag, testRocks);

        assertEquals(testWhiteBag, testBlackBag.getAssignedWhiteBag());
    }

    @Test
    public void test_getRocks() {
        testWhiteBag = new WhiteBag();
        testRocks = new Rock[] {};
        testBlackBag = new BlackBag(1, testWhiteBag, testRocks);

        assertArrayEquals(testRocks, testBlackBag.getRocks());
    }
}