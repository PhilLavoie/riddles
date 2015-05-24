package org.thephilz.riddles.bulb.furniture;

import org.junit.Test;
import org.thephilz.riddles.bulb.furniture.Bulb;
import org.thephilz.riddles.bulb.furniture.Room;

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