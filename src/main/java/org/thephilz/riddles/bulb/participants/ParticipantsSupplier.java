package org.thephilz.riddles.bulb.participants;

import java.util.function.Supplier;

/**
 *
 */
public class ParticipantsSupplier implements Supplier<Participant> {
    protected Participants participants;
    protected int currentIndex;

    public ParticipantsSupplier(Participants participants) {
        this.participants = participants;
        this.currentIndex = 0;
    }

    @Override
    public Participant get() {
        Participant answer = participants.get(currentIndex);
        if (currentIndex >= participants.size() - 1) {
            currentIndex = 0;
        } else {
            currentIndex++;
        }
        return answer;
    }
}
