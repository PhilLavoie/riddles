package org.thephilz.riddles.circleofdeath;

public class Counters {
    private int noTurns;
    private int noCycles;

    Counters() {}

    public int getNoTurns() {
        return noTurns;
    }

    public int getNoCycles() {
        return noCycles;
    }

    void incrementNoTurns() {
        noTurns++;
    }

    void incrementNoCycles() {
        noCycles++;
    }

    public static Counters create() {
        return new Counters();
    }
}
