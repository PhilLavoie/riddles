package org.thephilz.riddles.bulb;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 */
public class TheChosenOneTest {

    private Room makeRoom(Bulb bulb) {
        Room room = mock(Room.class);
        when(room.getBulb()).thenReturn(bulb);
        return room;
    }

    @Test
    public void testOneParticipant() {
        TheChosenOne chosen = new TheChosenOne(1);
        Bulb bulb = mock(Bulb.class);
        Room room = makeRoom(bulb);

        when(bulb.isInInitialState()).thenReturn(true);

        chosen.goToRoom(room);
        assertTrue(chosen.hasEverybodyBeen());
    }

    @Test
    public void testDoNotSwitch() {
        TheChosenOne chosen = new TheChosenOne(Integer.MIN_VALUE);
        Bulb bulb = mock(Bulb.class);
        Room room = makeRoom(bulb);
        when(bulb.isInInitialState()).thenReturn(false);

        chosen.goToRoom(room);

        verify(bulb, never()).switchIt();
    }

    @Test
    public void testSwitch() {
        TheChosenOne chosen = new TheChosenOne(Integer.MIN_VALUE);
        Bulb bulb = mock(Bulb.class);
        Room room = makeRoom(bulb);
        when(bulb.isInInitialState()).thenReturn(true);

        chosen.goToRoom(room);

        verify(bulb, times(1)).switchIt();
    }

    @Test
    public void testParticipantsOptimal() {
        final int noParticipants = 99;
        TheChosenOne chosen = new TheChosenOne(noParticipants);


        boolean bulbOn = true;
        int counter = noParticipants;
        do {
            Bulb bulb = mock(Bulb.class);
            Room room = makeRoom(bulb);
            when(bulb.isInInitialState()).thenReturn(bulbOn);
            chosen.goToRoom(room);

            if (bulbOn) {
                counter--;
                assertEquals(counter == 0, chosen.hasEverybodyBeen());
            } else {
                assertFalse(chosen.hasEverybodyBeen());
            }
            bulbOn = !bulbOn;
        } while (!chosen.hasEverybodyBeen());
    }



}