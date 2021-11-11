package PebbleGame.Tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import PebbleGame.BlackBag;
import PebbleGame.Rock;
import PebbleGame.WhiteBag;

public class TestWhiteBag {

    WhiteBag testWhiteBag;
    BlackBag testAssignedBlackBag;
    int testNumber;
    Rock testRock;
    Rock[] testRocks;

    @Test
    public void test_constructor() {
        testWhiteBag = new WhiteBag();
        testRocks = new Rock[] {};
        assertArrayEquals(testWhiteBag.getRocks(), testRocks);
    }

    @Test
    public void test_addToWhiteBag() {
        testWhiteBag = new WhiteBag();
        testRock = new Rock(10);
        testRocks = new Rock[] {testRock};

        testWhiteBag.addToWhiteBag(testRock);

        assertArrayEquals(testWhiteBag.getRocks(), testRocks);
    }

    @Test
    public void test_drainWhiteBag() {
        testWhiteBag = new WhiteBag();
        testRock = new Rock(10);
        testRocks = new Rock[] {};

        testWhiteBag.addToWhiteBag(testRock);

        testAssignedBlackBag = new BlackBag(1, testWhiteBag, testRocks);

        testWhiteBag.drainWhiteBag();

        testRocks = new Rock[] {testRock};

        assertArrayEquals(testRocks, testAssignedBlackBag.getRocks());
    }

    @Test
    public void test_assignBlackBag() {
        testWhiteBag = new WhiteBag();
        testRocks = new Rock[] {};
        testAssignedBlackBag = new BlackBag(1, testWhiteBag, testRocks);

        testWhiteBag.assignBlackBag(1, testAssignedBlackBag);

        assertEquals(testWhiteBag.getAssignedBlackBag(), testAssignedBlackBag);
    }
}
