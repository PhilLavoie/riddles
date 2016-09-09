package org.thephilz.riddles.impossible_puzzle;

/**
 *
 */
public class Prime {

    //First primes number that are under or equals to 100.
    private static int[] firstPrimes = {
       2,  3,  5,  7, 11, 13, 17, 19, 23, 29,
      31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
      73, 79, 83, 89, 97
    };

    /**
     * A matrix holding the sums of the primes listed above.
     */
    private static int[][] primesSums;

    static {
        final int size = firstPrimes.length;
        primesSums = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                primesSums[i][j] = firstPrimes[i] + firstPrimes[j];
            }
        }
    }

    public static int[][] getPrimesSums() {
        return primesSums;
    }
}
