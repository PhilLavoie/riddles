package org.thephilz.riddles.impossible_puzzle;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by phil on 6/22/15.
 */
public class Product {

    protected int value;
    protected int x;
    protected int y;
    public Product(int x, int y) {
        this.value = x * y;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }
}
