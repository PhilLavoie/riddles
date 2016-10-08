package org.thephilz.riddles.circleofdeath.circleofdeath;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import org.thephilz.riddles.circleofdeath.circle.Circle;
import org.thephilz.riddles.circleofdeath.circle.Position;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class CircleOfDeath {

    private final Circle<Participant> livingParticipants;
    private ImmutableList<Participant> participants;
    private Position<Participant> guyHoldingSwordPosition;

    @VisibleForTesting CircleOfDeath(ImmutableList<Participant> participants,
        Circle<Participant> participantCircle, Position guyHoldingSwordPosition) {
        this.participants = participants;
        this.livingParticipants = participantCircle;
        this.guyHoldingSwordPosition = guyHoldingSwordPosition;
    }

    public static CircleOfDeath withNumberOfParticipants(int noParticipants) {
        checkArgument(noParticipants > 0);

        ImmutableList<Participant> participants = ImmutableList.copyOf(
            IntStream.rangeClosed(1, noParticipants).mapToObj(Participant::create)
                .collect(Collectors.toList()));

        Circle<Participant> participantCircle = Circle.empty();
        participants.stream().forEach(participantCircle::append);

        Position<Participant> first = participantCircle.getFirst();
        first.getElement().holdSword();

        return new CircleOfDeath(participants, participantCircle, first);
    }

    public ImmutableList<Participant> participants() {
        return participants;
    }

    public boolean oneManStanding() {
        return livingParticipants.size() == 1;
    }

    public Participant guyHoldingSword() {
        return guyHoldingSwordPosition.getElement();
    }

    public void killNextAndPassSword() {
        checkState(!oneManStanding());

        Position<Participant> deadGuyPosition = livingParticipants.nextOf(guyHoldingSwordPosition);
        deadGuyPosition.getElement().die();

        Position<Participant> nextGuyHoldingSword = livingParticipants.removeAt(deadGuyPosition);
        guyHoldingSwordPosition.getElement().letGoOfSword();
        nextGuyHoldingSword.getElement().holdSword();
        guyHoldingSwordPosition = nextGuyHoldingSword;
    }

}
