package org.thephilz.riddles.impossible_puzzle.dsl;

import org.thephilz.riddles.impossible_puzzle.Product;
import org.thephilz.riddles.impossible_puzzle.Prime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 *
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

    /**
     * Since P does not know X and Y, then the S must know a sum with special property.
     * This sum cannot be expressed in such a way that X + Y are the unique factors
     * of a product (excluding the product itself and 1).
     *
     * In other words, X and Y cannot be both prime at the same time. Since X and Y must
     * differ, we can also say that products cannot be a power of 4 of a prime,
     * because then the product would have only 3 factors.
     *
     * Example: 16^(1/4) = 2. Factors of 16 are (2, 8), and (4, 4). Since X and Y must differ,
     * there exists only one result candidate, (2, 8). So the sum cannot be 10 (2 + 8).
     *
     * Luckily, since the sum must be lower or equal to 100, this rule only applies to
     * 10 (2 + 8) and 30 (3 + 27), since the next one would be 130 (5 + 125). Also,
     * both 10 and 30 are covered by the first rule: 10 = 7 + 3 and 30 = 7 + 23.
     *
     * @return An object onto which to call the next reply.
     */
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
