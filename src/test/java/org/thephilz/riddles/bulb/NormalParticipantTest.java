package org.thephilz.riddles.bulb;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 */
public class NormalParticipantTest {

    private Room makeRoom(Bulb bulb) {
        Room room = mock(Room.class);
        when(room.getBulb()).thenReturn(bulb);
        return room;
    }

    @Test
    public void testSwitchWhenNotInInitialState() {
        Bulb bulb = mock(Bulb.class);
        when(bulb.isInInitialState()).thenReturn(false);
        Room room = makeRoom(bulb);

        NormalParticipant participant = new NormalParticipant();
        participant.goToRoom(room);

        verify(bulb).switchIt();
    }

    @Test
    public void testDoNotSwitchWhenInInitialState() {
        Bulb bulb = mock(Bulb.class);
        when(bulb.isInInitialState()).thenReturn(true);
        Room room = makeRoom(bulb);

        NormalParticipant participant = new NormalParticipant();
        participant.goToRoom(room);

        verify(bulb, never()).switchIt();
    }

    @Test
    public void testDoNotSwitchTwice() {
        Bulb bulb = mock(Bulb.class);
        when(bulb.isInInitialState()).thenReturn(false);
        Room room = makeRoom(bulb);

        NormalParticipant participant = new NormalParticipant();
        participant.goToRoom(room);

        verify(bulb, times(1)).switchIt();

        participant.goToRoom(room);

        verify(bulb, times(1)).switchIt();
    }

    @Test
    public void testReturnFalseOnQuestioning() {
        Bulb bulb = mock(Bulb.class);
        when(bulb.isInInitialState()).thenReturn(true);
        Room room = makeRoom(bulb);

        NormalParticipant participant = new NormalParticipant();
        assertFalse(participant.hasEverybodyBeen());

        participant.goToRoom(room);

        assertFalse(participant.hasEverybodyBeen());

        Bulb bulb2 = mock(Bulb.class);
        when(bulb2.isInInitialState()).thenReturn(false);
        Room room2 = makeRoom(bulb2);

        participant = new NormalParticipant();
        participant.goToRoom(room2);

        assertFalse(participant.hasEverybodyBeen());
    }

}