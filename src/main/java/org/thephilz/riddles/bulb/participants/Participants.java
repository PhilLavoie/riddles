package org.thephilz.riddles.bulb.participants;

import java.util.ArrayList;

/**
 *
 */
public class Participants {

    protected ArrayList<Participant> participants;

    public Participants(int noParticipants) {
        assert noParticipants > 1;

        this.participants = new ArrayList<>(noParticipants);

        this.participants.add(new TheChosenOne(noParticipants));
        for (int i = 1; i < noParticipants; i++) {
            this.participants.add(new NormalParticipant());
        }
    }

    public int size() {
        return this.participants.size();
    }

    public Participant get(int index) {
        return this.participants.get(index);
    }
}
