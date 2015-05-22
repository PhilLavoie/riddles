package org.thephilz.riddles.bulb;

import java.util.Random;

/**
 *
 */
public class RiddleInstance {

    protected final Room room;
    protected final int noPartipants;
    protected final Participant[] participants;
    protected int pickCount;

    public RiddleInstance(Room room, int noParticipants) {
        assert noParticipants > 0;
        this.noPartipants = noParticipants;
        this.room = room;

        Participant[] participants = new Participant[noParticipants];
        for (int i = 0; i < noParticipants; i++) {
            participants[i] = new Participant(i, noParticipants);
        }
        this.participants = participants;

        this.pickCount = 1;
    }

    protected Participant getParticipant(int index) {
        return this.participants[index];
    }

    protected boolean pickAndCheckIfFinished(int pickCount) {

        //Randomly pick a participant.
        int randomParticipantIdx = new Random().nextInt(noPartipants);
        Participant randomParticipant = getParticipant(randomParticipantIdx);

        return randomParticipant.goToRoom(this.room, pickCount);
    }

    public void solve() {
        while (!pickAndCheckIfFinished(pickCount)) {
            pickCount++;
        }
    }

}
