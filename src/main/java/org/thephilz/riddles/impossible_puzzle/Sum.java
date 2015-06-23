package org.thephilz.riddles.impossible_puzzle;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by phil on 6/22/15.
 */
public class Sum {

    public static Iterable<Integer> getAvailableSums() {
        final int[][] primesSums = Prime.getPrimesSums();
        final int size = primesSums.length; //Expected to be a square matrix.

        //Put in a set to remove duplicates.
        HashSet<Integer> uniquePrimesSums = new HashSet<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                uniquePrimesSums.add(primesSums[i][j]);
            }
        }

        //Iterate over all possible sums from 5 to 100 and exclude the
        //sums of two prime numbers.
        //Start at 5, because X and Y can't be 1 and can't be the same, so the first
        //meaningful sum is 5.
        ArrayList<Integer> sums = new ArrayList<>();
        for (int i = 5; i <= 100; i++) {
            if (!uniquePrimesSums.contains(i)) {
                sums.add(i);
            }
        }

        return sums;
    }

    public static void main(String[] args) {
        //Print available sums.
        System.out.println("Available sums");

        for (int sum: getAvailableSums()) {
            System.out.printf("%s, ", sum);
        }
    }
}
