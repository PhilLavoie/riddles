package org.thephilz.riddles.bulb;

import java.util.function.Supplier;

/**
 *
 */
class ParticipantsSupplier implements Supplier<Participant> {
    protected Participants participants;
    protected int currentIndex;

    protected ParticipantsSupplier(Participants participants) {
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
