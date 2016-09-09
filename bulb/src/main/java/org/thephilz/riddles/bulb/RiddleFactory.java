package org.thephilz.riddles.bulb;

import org.thephilz.riddles.bulb.furniture.Bulb;
import org.thephilz.riddles.bulb.furniture.Room;

/**
 *
 */
public class RiddleFactory {

    public static RiddleInstance make(int noParticipants) {
        return new RiddleInstance(new Room(new Bulb()), noParticipants);
    }
}
