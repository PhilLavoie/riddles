package org.thephilz.riddles.circleofdeath.snapshot;

public enum SnapshotOccurrence {
    EVERY_TURN {
        @Override public boolean takeSnapshotOnTurn() {
            return true;
        }

        @Override public boolean takeSnapshotOnCycle() {
            return true;
        }

        @Override public boolean takeSnapshotOnStart() {
            return true;
        }

        @Override public boolean takeSnapshotOnTermination() {
            return true;
        }
    },
    NEVER {
        @Override public boolean takeSnapshotOnTurn() {
            return false;
        }

        @Override public boolean takeSnapshotOnCycle() {
            return false;
        }

        @Override public boolean takeSnapshotOnStart() {
            return false;
        }

        @Override public boolean takeSnapshotOnTermination() {
            return false;
        }
    };


    public abstract boolean takeSnapshotOnTurn();

    public abstract boolean takeSnapshotOnCycle();

    public abstract boolean takeSnapshotOnStart();

    public abstract boolean takeSnapshotOnTermination();
}
