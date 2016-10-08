package org.thephilz.riddles.bulb.main;

import org.thephilz.riddles.bulb.RiddleFactory;
import org.thephilz.riddles.bulb.RiddleResults;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        RiddleResults results = RiddleFactory.make(100).solve();
    }
}
