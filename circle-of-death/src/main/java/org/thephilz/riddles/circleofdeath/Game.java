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

        maybeTakeAndAddSnapshot(() -> snapshotTaker.onStart(circleOfDeath), snapshots);
        
        while (!circleOfDeath.oneManStanding()) {

            doCycle(circleOfDeath, snapshotTaker, snapshots);
        }

        maybeTakeAndAddSnapshot(() -> snapshotTaker.onTermination(circleOfDeath), snapshots);
        
        return Result.create(snapshots, circleOfDeath.guyHoldingSword());
    }

    private void doCycle(CircleOfDeath circleOfDeath, SnapshotTaker SnapshotTaker,
        List<CircleOfDeathSnapshot> snapshots) {

        int previousOrdinal = circleOfDeath.guyHoldingSword().getOrdinal();

        doTurn(circleOfDeath, SnapshotTaker, snapshots);
        
        int currentOrdinal = circleOfDeath.guyHoldingSword().getOrdinal();

        while (currentOrdinal > previousOrdinal) {
            doTurn(circleOfDeath, SnapshotTaker, snapshots);
            previousOrdinal = currentOrdinal;
            currentOrdinal = circleOfDeath.guyHoldingSword().getOrdinal();
        }

        maybeTakeAndAddSnapshot(() -> SnapshotTaker.onCycle(circleOfDeath), snapshots);
    }

    private void doTurn(CircleOfDeath circleOfDeath, SnapshotTaker SnapshotTaker,
        List<CircleOfDeathSnapshot> snapshots) {
        checkState(!circleOfDeath.oneManStanding());
        circleOfDeath.killNextAndPassSword();

        maybeTakeAndAddSnapshot(() -> SnapshotTaker.onTurn(circleOfDeath), snapshots);        
    }

    private void maybeTakeAndAddSnapshot(Supplier<Optional<CircleOfDeathSnapshot>> callable,
        List<CircleOfDeathSnapshot> snapshots) {
        Optional<CircleOfDeathSnapshot> optionalSnapshot = callable.get();
        if (optionalSnapshot.isPresent()) {
            snapshots.add(optionalSnapshot.get());
        }
    }
}
