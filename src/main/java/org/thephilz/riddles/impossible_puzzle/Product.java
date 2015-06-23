package org.thephilz.riddles.impossible_puzzle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by phil on 6/22/15.
 */
public class Product {

    public static final class Divisor {
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

    protected int product;
    protected int x;
    protected int y;

    public Product(Divisor divisor) {
        this.product = divisor.getX() * divisor.getY();
        this.x = Math.min(divisor.getX(), divisor.getY());
        this.y = Math.max(divisor.getX(), divisor.getY());
    }

    public static Iterable<Divisor> getDivisors(int number) {
        ArrayList<Divisor> divisors = new ArrayList<>();

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                divisors.add(new Divisor(i, number/i));
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

    public static Iterable<Product> getPossibleProducts(Iterable<Integer> possibleSums) {
        TreeSet<Integer> orderedSums = new TreeSet<>();
        for (Integer sum: possibleSums) {
            orderedSums.add(sum);
        }

        int minProduct = getMinProduct(orderedSums.first());
        int maxProduct = getMaxProduct(orderedSums.last());

        ArrayList<Product> possibleProducts = new ArrayList<>();
        for (int i = minProduct; i <= maxProduct; i++) {

            boolean foundOnlyOneMatch = false;
            Divisor match = null;

            for (Divisor divisor: getDivisors(i)) {

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
                possibleProducts.add(new Product(match));
            }
        }

        return possibleProducts;
    }

    public static void main(String[] args) {
        System.out.println("Possible products");

        for (Product product: getPossibleProducts(Sum.getAvailableSums())) {
            System.out.printf("%s (%s, %s), ", product.product, product.x, product.y);
        }
    }
}
