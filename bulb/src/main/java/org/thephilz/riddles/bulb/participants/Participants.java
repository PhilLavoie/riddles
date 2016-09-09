package org.thephilz.riddles.bulb.participants;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class Participants implements Iterable<Participant> {

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

    @Override
    public Iterator<Participant> iterator() {
        return this.participants.iterator();
    }
}
