package org.thephilz.riddles.bulb;

/**
 *
 */
public class RiddleFactory {

    public static RiddleInstance make(int noParticipants) {
        return new RiddleInstance(new Room(new Bulb()), noParticipants);
    }
}
