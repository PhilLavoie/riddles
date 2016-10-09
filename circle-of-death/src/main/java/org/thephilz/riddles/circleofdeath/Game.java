package org.thephilz.riddles.circleofdeath;

import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import org.thephilz.riddles.circleofdeath.circleofdeath.CircleOfDeath;
import org.thephilz.riddles.circleofdeath.snapshot.CircleOfDeathSnapshot;
import org.thephilz.riddles.circleofdeath.snapshot.SnapshotTaker;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkState;

public class Game {

    Game() {
    }

    Result solve(CircleOfDeath circleOfDeath, SnapshotTaker snapshotTaker) {
        List<CircleOfDeathSnapshot> snapshots = Lists.newArrayList();
        Counters counters = Counters.create();

        final int noParticipants = circleOfDeath.participants().size();

        maybeTakeAndAddSnapshot(() -> snapshotTaker.onStart(circleOfDeath), snapshots);

        while (!circleOfDeath.oneManStanding()) {

            doCycle(circleOfDeath, snapshotTaker, snapshots, counters);
        }

        maybeTakeAndAddSnapshot(() -> snapshotTaker.onTermination(circleOfDeath), snapshots);

        return Result.create(snapshots, circleOfDeath.guyHoldingSword(), noParticipants,
            counters.getNoCycles(), counters.getNoTurns());
    }

    private void doCycle(CircleOfDeath circleOfDeath, SnapshotTaker SnapshotTaker,
        List<CircleOfDeathSnapshot> snapshots, Counters counters) {

        //This "algorithm" depends on the fact that the guy holding the sword starts at position
        //1... should find something more elegant...
        int previousOrdinal = circleOfDeath.guyHoldingSword().getOrdinal();

        doTurn(circleOfDeath, SnapshotTaker, snapshots, counters);

        int currentOrdinal = circleOfDeath.guyHoldingSword().getOrdinal();

        while (currentOrdinal > previousOrdinal) {
            doTurn(circleOfDeath, SnapshotTaker, snapshots, counters);
            previousOrdinal = currentOrdinal;
            currentOrdinal = circleOfDeath.guyHoldingSword().getOrdinal();
        }

        maybeTakeAndAddSnapshot(() -> SnapshotTaker.onCycle(circleOfDeath), snapshots);
        counters.incrementNoCycles();
    }

    private void doTurn(CircleOfDeath circleOfDeath, SnapshotTaker SnapshotTaker,
        List<CircleOfDeathSnapshot> snapshots, Counters counters) {
        checkState(!circleOfDeath.oneManStanding());
        circleOfDeath.killNextAndPassSword();

        maybeTakeAndAddSnapshot(() -> SnapshotTaker.onTurn(circleOfDeath), snapshots);
        counters.incrementNoTurns();
    }

    private void maybeTakeAndAddSnapshot(Supplier<Optional<CircleOfDeathSnapshot>> callable,
        List<CircleOfDeathSnapshot> snapshots) {
        Optional<CircleOfDeathSnapshot> optionalSnapshot = callable.get();
        if (optionalSnapshot.isPresent()) {
            snapshots.add(optionalSnapshot.get());
        }
    }
}
