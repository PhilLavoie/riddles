package org.thephilz.riddles.circleofdeath;

import org.thephilz.riddles.circleofdeath.circleofdeath.CircleOfDeath;
import org.thephilz.riddles.circleofdeath.snapshot.SnapshotOccurrence;
import org.thephilz.riddles.circleofdeath.snapshot.SnapshotTaker;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        Result result =
            game.solve(CircleOfDeath.withNumberOfParticipants(100), new SnapshotTaker(SnapshotOccurrence.EVERY_TURN));

        result.getSnapshots().stream().forEach(
            snapshot -> System.out.println(snapshot.outputString())
        );

        System.out.println("And the winner is.... " + result.getWinner().getOrdinal());
    }
}
