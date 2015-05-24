package org.thephilz.riddles.bulb;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class ParticipantsTest {

    @Test
    public void testConstructionAndSize() {
        final int noParticipants = 50;
        Participants participants = new Participants(noParticipants);
        assertEquals(noParticipants, participants.size());
    }

}