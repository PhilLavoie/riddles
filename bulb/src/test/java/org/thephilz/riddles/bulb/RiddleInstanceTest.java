package org.thephilz.riddles.bulb;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 *
 */
public class RiddleInstanceTest {

//    @Test
//    public void testParticipantsInitialization() {
//        int noParticipants = new Random().nextInt(30000);
//
//        RiddleInstance instance = new RiddleInstance(null, noParticipants);
//        assertEquals(noParticipants, instance.noPartipants);
//        assertNotNull(instance.participants);
//        assertEquals(noParticipants, instance.participants.length);
//
//        for (int i = 0; i < noParticipants; i++) {
//            Participant participant = instance.getParticipant(i);
//            assertNotNull(participant);
//            assertEquals(i, participant.myModIndex);
//            assertEquals(noParticipants, participant.noParticipants);
//        }
//    }
//
//    @Test
//    public void testRiddleWith100Participants() {
//        RiddleInstance instance = new RiddleInstance(new Room(new Bulb(true)), 100);
//
//        instance.solve();
//
//        for (Participant participant: instance.participants) {
//            assertTrue(participant.beenToRoomCount > 0);
//        }
//
//        assertTrue(instance.pickCount >= instance.noPartipants);
//
//        System.out.println("It took " + instance.pickCount + " picks to solve the riddle");
//    }

}