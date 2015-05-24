package org.thephilz.riddles.bulb;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 *
 */
public class ResultsFormatter {

    protected RiddleResults results;

    public ResultsFormatter(RiddleResults results) {
        this.results = results;
    }

    public void printHere(PrintStream stream) {
        stream.format("hello");
    }
}
