package org.thephilz.riddles.impossible_puzzle.dsl;

import org.thephilz.riddles.impossible_puzzle.Prime;
import org.thephilz.riddles.impossible_puzzle.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by phil on 6/23/15.
 */
public class FirstStatement {
    Iterable<Product> candidateProducts;

    protected FirstStatement(Iterable<Integer> possibleSums) {
        this.candidateProducts = computeProducts(possibleSums);
    }

    protected static Iterable<Divisor> getDivisors(int number) {
        ArrayList<Divisor> divisors = new ArrayList<>();

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                divisors.add(new Divisor(i, number / i));
            }
        }

        return divisors;
    }

    protected static int getMinProduct(int minPossibleProduct) {
        return (minPossibleProduct - 2) * 2;
    }

    protected static int getMaxProduct(int maxPossibleProduct) {
        int mid = maxPossibleProduct / 2;
        return (mid) * (maxPossibleProduct - mid);
    }

    protected static Iterable<Product> computeProducts(Iterable<Integer> possibleSums) {
        TreeSet<Integer> orderedSums = new TreeSet<>();
        for (Integer sum : possibleSums) {
            orderedSums.add(sum);
        }

        int minProduct = getMinProduct(orderedSums.first());
        int maxProduct = getMaxProduct(orderedSums.last());

        ArrayList<Product> possibleProducts = new ArrayList<>();
        for (int i = minProduct; i <= maxProduct; i++) {

            boolean foundOnlyOneMatch = false;
            Divisor match = null;

            for (Divisor divisor : getDivisors(i)) {

                if (divisor.getX() == divisor.getY()) {
                    continue;
                }

                int divisorSum = divisor.getX() + divisor.getY();

                if (orderedSums.contains(divisorSum)) {
                    if (!foundOnlyOneMatch) {
                        foundOnlyOneMatch = true;
                        match = divisor;
                    } else {
                        foundOnlyOneMatch = false;
                        match = null;
                        break;
                    }
                }
            }

            if (foundOnlyOneMatch) {
                possibleProducts.add(new Product(match.getX(), match.getY()));
            }
        }

        return possibleProducts;
    }

    public static FirstStatement pDoesNotKnowXAndY() {
        return new FirstStatement(computeSums());
    }

    private static Iterable<Integer> computeSums() {
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

    public SecondStatement nowIKnowXAndY() {
        return new SecondStatement(this.candidateProducts);
    }

    private static final class Divisor {
        protected int x;
        protected int y;

        protected Divisor(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
