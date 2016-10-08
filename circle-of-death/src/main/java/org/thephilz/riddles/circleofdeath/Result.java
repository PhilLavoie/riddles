package org.thephilz.riddles.circleofdeath;

import com.google.common.collect.ImmutableList;
import org.thephilz.riddles.circleofdeath.circleofdeath.Participant;
import org.thephilz.riddles.circleofdeath.snapshot.CircleOfDeathSnapshot;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Result {

    private final ImmutableList<CircleOfDeathSnapshot> snapshots;
    private final Participant winner;
    private final int noParticipants;

    Result(ImmutableList<CircleOfDeathSnapshot> snapshots, Participant winner, int noParticipants) {
        this.snapshots = snapshots;
        this.winner = winner;
        this.noParticipants = noParticipants;
    }

    static Result create(List<CircleOfDeathSnapshot> snapshots, Participant winner, int noParticipants) {
        checkNotNull(snapshots);
        checkNotNull(winner);
        checkArgument(noParticipants > 0);

        return new Result(ImmutableList.copyOf(snapshots), winner.copy(), noParticipants);
    }

    ImmutableList<CircleOfDeathSnapshot> getSnapshots() {
        return snapshots;
    }

    Participant getWinner() {
        return winner;
    }

    public int getNoParticipants() {
        return noParticipants;
    }
}
