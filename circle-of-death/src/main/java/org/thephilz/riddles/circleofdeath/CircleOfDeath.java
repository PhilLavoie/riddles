package org.thephilz.riddles.circleofdeath;

import org.thephilz.riddles.circleofdeath.circle.Circle;

public class CircleOfDeath {

    private final Circle<Participant> partipantCircle;

    CircleOfDeath(Circle<Participant> partipantCircle) {
        this.partipantCircle = partipantCircle;
    }

    public static CircleOfDeath withNoParticipants(int noParticipants) {
        Circle<Participant> participantCircle = Circle.empty();

        for (int i = 1; i <= noParticipants; i++) {
            participantCircle.append(Participant.create(i));
        }

        return new CircleOfDeath(participantCircle);
    }

    public Result solve() {
        Result result = Result.create();

        Participant guyHoldingSword = partipantCircle.getFirst();

        throw new UnsupportedOperationException("finis this brah");
    }



}
