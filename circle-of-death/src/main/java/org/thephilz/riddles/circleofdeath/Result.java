package org.thephilz.riddles.circleofdeath;

import com.google.common.collect.ImmutableList;
import org.thephilz.riddles.circleofdeath.circleofdeath.Participant;
import org.thephilz.riddles.circleofdeath.snapshot.CircleOfDeathSnapshot;

import java.util.List;

public class Result {

    private final ImmutableList<CircleOfDeathSnapshot> snapshots;
    private final Participant winner;

    Result(ImmutableList<CircleOfDeathSnapshot> initialState, Participant winner) {
        this.snapshots = initialState;
        this.winner = winner;
    }

    static Result create(List<CircleOfDeathSnapshot> snapshots, Participant winner) {
        return new Result(ImmutableList.copyOf(snapshots), winner.copy());
    }

    ImmutableList<CircleOfDeathSnapshot> getSnapshots() {
        return snapshots;
    }

    Participant getWinner() {
        return winner;
    }
}
