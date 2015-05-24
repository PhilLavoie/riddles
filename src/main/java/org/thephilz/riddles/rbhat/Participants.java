package org.thephilz.riddles.rbhat;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class Participants implements Iterable<TailParticipant> {

    protected final ArrayList<TailParticipant> payload;

    protected interface HatDispenser {
        Hat dispense(int participantIndex);
    }

    protected Participants(final int noParticipants, HatDispenser dispenser, Hat referenceHat) {
        this.payload = new ArrayList<TailParticipant>(noParticipants);

        for (int i = 0; i < noParticipants; i++) {
            assert false;
            //this.payload.add(new TailParticipant(dispenser.dispense(i)));
        }
    }

    public Iterator<TailParticipant> iterator() {
        return null;
    }
}
