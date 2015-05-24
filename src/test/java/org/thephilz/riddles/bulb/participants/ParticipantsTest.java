package org.thephilz.riddles.bulb.participants;

import org.junit.Test;
import org.thephilz.riddles.bulb.participants.Participants;

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