package PebbleGame;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.Test;

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
        assertArrayEquals(bag.getRocks(), rocks);
    }

    @Test
    public void test_add_rock() {
        bag = new Bag();
        rock = new Rock(10);
        rocks = new Rock[] {rock};

        bag.addRock(rock);

        assertArrayEquals(bag.getRocks(), rocks);
    }

    @Test
    public void test_get_rocks() {
        bag = new Bag();
        
        assertInstanceOf(Rock[].class, bag.getRocks());
    }
}