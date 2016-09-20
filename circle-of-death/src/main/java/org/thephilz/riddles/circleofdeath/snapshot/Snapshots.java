package org.thephilz.riddles.circleofdeath.snapshot;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import org.thephilz.riddles.circleofdeath.circleofdeath.CircleOfDeath;
import org.thephilz.riddles.circleofdeath.circleofdeath.Participant;

import java.util.List;
import java.util.stream.Collectors;

class Snapshots {

    private final static int SEPARATOR_LINE_LENGTH = 80;

    private Snapshots() {
    }

    public static CircleOfDeathSnapshot onStartSnapshot(CircleOfDeath circleOfDeath) {
        List<Participant> snapshot = participantsSnapshot(circleOfDeath);

        return new CircleOfDeathSnapshot() {
            @Override public String outputString() {
                String initialStateString = "Initial state of the circle of death: ";
                StringBuilder builder =
                    new StringBuilder();

                String newLine = System.lineSeparator();

                builder.append(initialStateString).append(newLine);
                builder.append(Strings.repeat("=", SEPARATOR_LINE_LENGTH)).append(newLine);
                builder.append(Strings.repeat("=", SEPARATOR_LINE_LENGTH)).append(newLine);

                outputParticipants(builder, snapshot);

                builder.append(Strings.repeat("=", SEPARATOR_LINE_LENGTH)).append(newLine);
                builder.append(Strings.repeat("=", SEPARATOR_LINE_LENGTH)).append(newLine);

                return builder.toString();
            }
        };
    }

    public static CircleOfDeathSnapshot onTurnSnapshot(CircleOfDeath circleOfDeath) {
        List<Participant> snapshot = participantsSnapshot(circleOfDeath);

        return new CircleOfDeathSnapshot() {
            @Override public String outputString() {
                StringBuilder builder =
                    new StringBuilder();

                String newLine = System.lineSeparator();

                builder.append("Turn state string:").append(newLine);
                builder.append(Strings.repeat("-", SEPARATOR_LINE_LENGTH)).append(newLine);

                outputParticipants(builder, snapshot);

                builder.append(Strings.repeat("-", SEPARATOR_LINE_LENGTH)).append(newLine);

                return builder.toString();
            }
        };
    }

    public static CircleOfDeathSnapshot onCycleSnapshot(CircleOfDeath circleOfDeath) {
        List<Participant> snapshot = participantsSnapshot(circleOfDeath);

        return new CircleOfDeathSnapshot() {
            @Override public String outputString() {
                StringBuilder builder =
                    new StringBuilder();

                String newLine = System.lineSeparator();

                builder.append("Cycle state string:").append(newLine);
                builder.append(Strings.repeat("*", SEPARATOR_LINE_LENGTH)).append(newLine);

                outputParticipants(builder, snapshot);

                builder.append(Strings.repeat("*", SEPARATOR_LINE_LENGTH)).append(newLine);

                return builder.toString();
            }
        };
    }

    public static CircleOfDeathSnapshot onTerminationSnapshot(CircleOfDeath circleOfDeath) {
        List<Participant> snapshot = participantsSnapshot(circleOfDeath);

        return new CircleOfDeathSnapshot() {
            @Override public String outputString() {
                String finalStateString = "Final state of the circle of death: ";
                StringBuilder builder =
                    new StringBuilder();

                String newLine = System.lineSeparator();

                builder.append(finalStateString).append(newLine);
                builder.append(Strings.repeat("=", SEPARATOR_LINE_LENGTH)).append(newLine);
                builder.append(Strings.repeat("=", SEPARATOR_LINE_LENGTH)).append(newLine);

                outputParticipants(builder, snapshot);

                builder.append(Strings.repeat("=", SEPARATOR_LINE_LENGTH)).append(newLine);
                builder.append(Strings.repeat("=", SEPARATOR_LINE_LENGTH)).append(newLine);

                return builder.toString();
            }
        };
    }

    private static void outputParticipants(StringBuilder builder, List<Participant> participants) {
        String newLine = System.lineSeparator();
        participants.stream().forEach(participant -> {
            builder.append(String.format("%6d",participant.getOrdinal()));
        });
        builder.append(newLine);

        participants.stream().forEach(participant -> {
            if (participant.isHoldingSword()) {
                builder.append(" SWORD");
            } else {
                builder.append(participant.isAlive() ? " alive" : "  dead");
            }
        });
        builder.append(newLine);
    }

    private static ImmutableList<Participant> participantsSnapshot(CircleOfDeath circleOfDeath) {
        return ImmutableList.copyOf(
            circleOfDeath.participants().stream().map(participant -> participant.copy())
                .collect(Collectors.toList()));
    }
}
