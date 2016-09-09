package org.thephilz.riddles.bulb.participants;

import org.thephilz.riddles.bulb.furniture.Room;

/**
 *
 */
public interface Participant {
    void goToRoom(Room theRoom);
    boolean hasEverybodyBeen();
}

