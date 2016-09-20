package org.thephilz.riddles.circleofdeath.circleofdeath;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class Participant {
    private final int ordinal;
    private boolean isAlive;
    private boolean isHoldingSword;

    Participant(int ordinal) {
        this(ordinal, true, false);
    }

    private Participant(int ordinal, boolean isAlive, boolean isHoldingSword) {
        this.ordinal = ordinal;
        this.isAlive = isAlive;
        this.isHoldingSword = isHoldingSword;
    }

    public static Participant create(int number) {
        checkArgument(number > 0);

        return new Participant(number);
    }

    public int getOrdinal() {
        return ordinal;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isHoldingSword() {
        return isHoldingSword;
    }

    void die() {
        checkState(!isHoldingSword(), "the guy holding the sword can't die");
        checkState(isAlive, "already dead brah");

        isAlive = false;
    }

    void holdSword() {
        checkState(isAlive(), "cannot hold the sword when dead");
        checkState(!isHoldingSword, "already holding the sword");

        this.isHoldingSword = true;
    }

    void letGoOfSword() {
        checkState(isHoldingSword, "not holding the sword");

        this.isHoldingSword = false;
    }

    public Participant copy() {
        return new Participant(getOrdinal(), isAlive(), isHoldingSword());
    }
}
