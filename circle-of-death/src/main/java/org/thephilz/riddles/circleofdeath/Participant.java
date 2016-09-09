package org.thephilz.riddles.circleofdeath;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class Participant {
    private final int number;
    private boolean isAlive;

    Participant(int number) {
        this.number = number;
        this.isAlive = true;
    }

    public static Participant create(int number) {
        checkArgument(number > 0);

        return new Participant(number);
    }

    public int getNumber() {
        return number;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die() {
        checkState(isAlive, "already dead brah");

        isAlive = false;
    }
}
