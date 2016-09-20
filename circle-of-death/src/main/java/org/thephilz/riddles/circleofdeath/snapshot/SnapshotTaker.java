package org.thephilz.riddles.circleofdeath.snapshot;

import org.thephilz.riddles.circleofdeath.circleofdeath.CircleOfDeath;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class SnapshotTaker {
    private final SnapshotOccurrence snapshotOccurrence;

    public SnapshotTaker(SnapshotOccurrence snapshotOccurrence) {
        this.snapshotOccurrence = snapshotOccurrence;
    }

    private static Optional<CircleOfDeathSnapshot> maybeTakeSnapshot(boolean takeSnapshot,
        Function<CircleOfDeath, CircleOfDeathSnapshot> snapshotFunction,
        CircleOfDeath circleOfDeath) {
        return maybeTakeSnapshot(takeSnapshot, () -> snapshotFunction.apply(circleOfDeath));
    }

    private static Optional<CircleOfDeathSnapshot> maybeTakeSnapshot(boolean takeSnapshot,
        Supplier<CircleOfDeathSnapshot> snapshotSupplier) {

        if (takeSnapshot) {
            return Optional.of(snapshotSupplier.get());
        }

        return Optional.empty();
    }

    public Optional<CircleOfDeathSnapshot> onTurn(CircleOfDeath circleOfDeath) {
        return maybeTakeSnapshot(snapshotOccurrence.takeSnapshotOnTurn(), Snapshots::onTurnSnapshot, circleOfDeath);
    }

    public Optional<CircleOfDeathSnapshot> onCycle(CircleOfDeath circleOfDeath) {
        return maybeTakeSnapshot(snapshotOccurrence.takeSnapshotOnCycle(), Snapshots::onCycleSnapshot, circleOfDeath);
    }

    public Optional<CircleOfDeathSnapshot> onStart(CircleOfDeath circleOfDeath) {
        return maybeTakeSnapshot(snapshotOccurrence.takeSnapshotOnStart(), Snapshots::onStartSnapshot, circleOfDeath);
    }

    public Optional<CircleOfDeathSnapshot> onTermination(CircleOfDeath circleOfDeath) {
        return maybeTakeSnapshot(snapshotOccurrence.takeSnapshotOnTermination(), Snapshots::onTerminationSnapshot, circleOfDeath);
    }
}
