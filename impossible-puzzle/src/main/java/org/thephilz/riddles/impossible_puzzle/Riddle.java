package org.thephilz.riddles.impossible_puzzle;

import static org.thephilz.riddles.impossible_puzzle.dsl.FirstStatement.pDoesNotKnowXAndY;

/**
 * X and Y are two different integers, greater than 1, with sum less than or equal to 100, with Y greater than X.
 * S and P are two mathematicians; S knows the sum X+Y, P knows the value X*Y, and both are perfect logicians.
 * Both S and P know the information in these two sentences. The following conversation occurs:
 * - S says "P does not know X and Y."
 * - P says "Now I know X and Y."
 * - S says "Now I also know X and Y!"
 * What are X and Y?
 */
public class Riddle {

    public Result solve() {
        return pDoesNotKnowXAndY()
                .nowIKnowXAndY()
                .nowIAlsoKnowXAndY()
                .whatAreXAndY();
    }

    public static void main(String[] args) {
        Result result = new Riddle().solve();

        System.out.printf("X is %s and Y is %s", result.getX(), result.getY());
    }

}
