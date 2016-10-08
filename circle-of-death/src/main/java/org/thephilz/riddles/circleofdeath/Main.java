package org.thephilz.riddles.circleofdeath;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thephilz.riddles.circleofdeath.circleofdeath.CircleOfDeath;
import org.thephilz.riddles.circleofdeath.snapshot.SnapshotOccurrence;
import org.thephilz.riddles.circleofdeath.snapshot.SnapshotTaker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Path outputDirectory = Paths.get(args[0]);
        createOutputDirectoryIfNonExistent(outputDirectory);

        List<Result> results = Lists.newArrayList();
        SnapshotTaker snapshotTaker = new SnapshotTaker(SnapshotOccurrence.EVERY_TURN);
        IntStream.rangeClosed(1, 100).forEach(noParticipants -> {
            logger.info("solving circle of death for {} participants", noParticipants);

            Game game = new Game();
            Result result =
                game.solve(CircleOfDeath.withNumberOfParticipants(noParticipants), snapshotTaker);

            Path outputFile = outputDirectory.resolve("circle_of_death_" + noParticipants + ".log");

            outputResult(result, outputFile);
            logger.info("result file {} created", outputFile);
            results.add(result);
        });

        Path outputSummaryFile = outputDirectory.resolve("output_summary.log");

        outputSummary(results, outputSummaryFile);
        logger.info("summary file {} created", outputSummaryFile);

    }

    private static void outputSummary(List<Result> results, Path outputSummaryFile) {
        try (PrintWriter writer = new PrintWriter(
            new BufferedWriter(new FileWriter(outputSummaryFile.toFile())))) {

            outputSummary(results, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void outputSummary(List<Result> results, PrintWriter writer) {
        results.forEach(result -> writer.println(
            "Number of participants: " + result.getNoParticipants() + " winner: " + result.getWinner().getOrdinal())
        );
    }

    private static void createOutputDirectoryIfNonExistent(Path outputDirectory) {
        if (!Files.isDirectory(outputDirectory)) {
            logger.info("output directory {} does not exist, creating it", outputDirectory);

            try {
                Files.createDirectories(outputDirectory);
            } catch (IOException e) {
                logger.error("unable to create output directory {}", outputDirectory, e);
                throw new RuntimeException(e);
            }
        }
    }

    private static void outputResult(Result result, Path outputFile) {

        try (PrintWriter writer = new PrintWriter(
            new BufferedWriter(new FileWriter(outputFile.toFile())))) {

            outputResult(result, writer);
        } catch (IOException e) {
            logger.error("unable to output result to file {}", outputFile, e);
            throw new RuntimeException(e);
        }
    }

    private static void outputResult(Result result, PrintWriter outputWriter) {
        result.getSnapshots().forEach(
            circleOfDeathSnapshot -> outputWriter.print(circleOfDeathSnapshot.outputString()));
        outputWriter.print(
            "And the winner is...., participant #" + result.getWinner().getOrdinal() + "!!!!");
    }



}
