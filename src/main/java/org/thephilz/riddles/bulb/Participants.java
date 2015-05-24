package org.thephilz.riddles.bulb;

import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 *
 */
class Participants {

    protected ArrayList<Participant> participants;

    protected Participants(int noParticipants) {
        assert noParticipants > 1;

        this.participants = new ArrayList<>(noParticipants);

        this.participants.add(new TheChosenOne(noParticipants));
        for (int i = 1; i < noParticipants; i++) {
            this.participants.add(new NormalParticipant());
        }
    }

    protected int size() {
        return this.participants.size();
    }

    protected Participant get(int index) {
        return this.participants.get(index);
    }
}
