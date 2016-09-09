package org.thephilz.riddles.bulb.participants;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
