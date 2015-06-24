package org.thephilz.riddles.impossible_puzzle.dsl;

import org.thephilz.riddles.impossible_puzzle.Product;
import org.thephilz.riddles.impossible_puzzle.Result;
import org.thephilz.riddles.impossible_puzzle.Sum;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by phil on 6/23/15.
 */
public class SecondStatement {

    protected Result result;

    protected SecondStatement(Iterable<Product> candidateProducts) {
        this.result = computeResult(candidateProducts);
    }

    protected static Result computeResult(Iterable<Product> products) {

        HashMap<Integer, SeenSum> processedSums = new HashMap<Integer, SeenSum>();

        for (Product product : products) {
            int x = product.getX();
            int y = product.getY();
            int sum = x + y;

            if (!processedSums.containsKey(sum)) {
                processedSums.put(sum, new SeenSum(new Sum(x, y)));
            } else {
                processedSums.get(sum).invalidate();
            }
        }

        ArrayList<Sum> results = new ArrayList<>();

        processedSums.forEach(
                (key, value) -> {
                    if (value.isValid()) {
                        results.add(value.getSum());
                    }
                }
        );

        assert results.size() == 1;

        return new Result(results.get(0).getX(), results.get(0).getY());
    }

    public Solution nowIAlsoKnowXAndY() {
        return new Solution(this.result);
    }

    private static class SeenSum {
        protected Sum sum;
        boolean valid;

        public SeenSum(Sum sum) {
            this.sum = sum;
            this.valid = true;
        }

        public void invalidate() {
            this.valid = false;
        }

        public boolean isValid() {
            return valid;
        }

        public Sum getSum() {
            return this.sum;
        }
    }
}
