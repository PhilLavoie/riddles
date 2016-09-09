package org.thephilz.riddles.bulb.furniture;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class BulbTest {

    @Test
    public void testConstructorOn() {
        Bulb bulb = new Bulb();
        assertTrue(bulb.isInInitialState());
    }

    @Test
    public void testSwitch() {
        Bulb bulb = new Bulb();

        bulb.switchIt();
        assertFalse(bulb.isInInitialState());

        bulb.switchIt();
        assertTrue(bulb.isInInitialState());
    }
}
