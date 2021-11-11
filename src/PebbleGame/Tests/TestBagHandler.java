package PebbleGame.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.Test;

import PebbleGame.BagHandler;
import PebbleGame.WhiteBag;

/**
 * This class contains the Tests for the BagHandler Class.
 */
public class TestBagHandler {

    @Test
    public void test_getNextDiscardBag() {
        assertInstanceOf(WhiteBag.class, BagHandler.getNextDiscardBag());
    }

    @Test
    public void test_updateNextDiscard() {
        WhiteBag testWhiteBag = new WhiteBag();
        BagHandler.updateNextDiscard(testWhiteBag);
        assertEquals(testWhiteBag, BagHandler.getNextDiscardBag());
    }
}