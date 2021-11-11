package PebbleGame.Tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.Test;

import PebbleGame.Bag;
import PebbleGame.Rock;

/**
 * This class contains the Tests for the Bag Class.
 */
public class TestBag {

    Bag bag;
    Rock rock;
    Rock[] rocks;

    @Test
    public void test_constrcutor() {
        bag = new Bag();
        rocks = new Rock[] {};
        assertInstanceOf(Bag.class, bag);
        assertArrayEquals(rocks, bag.getRocks());
    }

    @Test
    public void test_addRock() {
        bag = new Bag();
        rock = new Rock(10);
        rocks = new Rock[] {rock};

        bag.addRock(rock);

        assertArrayEquals(bag.getRocks(), rocks);
    }

    @Test
    public void test_getRocks() {
        bag = new Bag();
        rocks = new Rock[] {};
        assertInstanceOf(Rock[].class, bag.getRocks());
        assertArrayEquals(rocks, bag.getRocks());

        rock = new Rock(10);

        bag.addRock(rock);
        rocks = new Rock[] {rock};

        assertArrayEquals(rocks, bag.getRocks());
    }
}