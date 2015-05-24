package org.thephilz.riddles.bulb;

import java.util.Random;
import java.util.stream.Stream;

/**
 *
 */
public class RiddleInstance {

    protected final Room room;
    protected final Participants participants;

    protected RiddleInstance(Room room, int noParticipants) {
        assert noParticipants > 0;

        this.room = room;
        this.participants = new Participants(noParticipants);
    }

    public void solve() {
        Stream.generate(new ParticipantsSupplier(this.participants))
                .anyMatch(
                        participant -> {
                            participant.goToRoom(room);
                            return participant.hasEverybodyBeen();
                        }
                );
    }

}
