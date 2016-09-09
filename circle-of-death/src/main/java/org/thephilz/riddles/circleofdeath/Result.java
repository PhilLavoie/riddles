package org.thephilz.riddles.circleofdeath;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;

public class Result {

    private final List<List<Participant>> alivePerRound;

    Result() {
        this.alivePerRound = Lists.newArrayList();
    }

    public static Result create() {
        return new Result();
    }

    /**
     * Makes a copy
     */
    public void appendAliveParticipantsForRound(List<Participant> participants) {
        alivePerRound.add(ImmutableList.copyOf(participants));
    }
}
