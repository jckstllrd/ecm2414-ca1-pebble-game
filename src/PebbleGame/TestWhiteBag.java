package PebbleGame;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class TestWhiteBag {

    WhiteBag testWhiteBag;
    BlackBag testAssignedBlackBag;
    int testNumber;
    Rock testRock;
    Rock[] testRocks;

    @BeforeAll
    public void setup() {
        testAssignedBlackBag = new BlackBag(0, testWhiteBag, testRocks);
    }

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

    }

    @Test
    public void test_assignBlackBag() {

    }
}
