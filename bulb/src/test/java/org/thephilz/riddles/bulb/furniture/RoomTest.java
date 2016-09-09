package org.thephilz.riddles.bulb.furniture;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 *
 */
public class RoomTest {

    @Test
    public void testConstructor() {
        Bulb bulb = mock(Bulb.class);
        Room room = new Room(bulb);
        assertSame(bulb, room.getBulb());
    }

}
