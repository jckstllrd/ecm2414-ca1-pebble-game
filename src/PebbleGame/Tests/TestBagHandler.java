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
        BagHandler bagHandler = new BagHandler();
        assertInstanceOf(WhiteBag.class, bagHandler.getNextDiscardBag());
    }

    @Test
    public void test_updateNextDiscard() {
        BagHandler bagHandler = new BagHandler();
        WhiteBag testWhiteBag = new WhiteBag();
        bagHandler.updateNextDiscard(testWhiteBag);
        assertEquals(testWhiteBag, bagHandler.getNextDiscardBag());
    }
}