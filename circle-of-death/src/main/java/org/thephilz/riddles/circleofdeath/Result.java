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
    private final int noTurns;
    private final int noCycles;

    Result(ImmutableList<CircleOfDeathSnapshot> snapshots, Participant winner, int noParticipants,
        int noCycles, int noTurns) {
        this.snapshots = snapshots;
        this.winner = winner;
        this.noParticipants = noParticipants;
        this.noTurns = noTurns;
        this.noCycles = noCycles;
    }

    static Result create(List<CircleOfDeathSnapshot> snapshots, Participant winner, int noParticipants,
        int noCycles, int noTurns) {
        checkNotNull(snapshots);
        checkNotNull(winner);
        checkArgument(noParticipants > 0);
        checkArgument(noTurns >= 0);
        checkArgument(noTurns >= noCycles);
        checkArgument(noCycles >= 0);

        return new Result(ImmutableList.copyOf(snapshots), winner.copy(), noParticipants, noCycles,
            noTurns);
    }

    ImmutableList<CircleOfDeathSnapshot> getSnapshots() {
        return snapshots;
    }

    Participant getWinner() {
        return winner;
    }

    int getNoParticipants() {
        return noParticipants;
    }

    int getNoTurns() {
        return noTurns;
    }

    int getNoCycles() {
        return noCycles;
    }
}
