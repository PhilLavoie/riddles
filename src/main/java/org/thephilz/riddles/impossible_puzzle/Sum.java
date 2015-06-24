package org.thephilz.riddles.impossible_puzzle;

/**
 *
 */
public class Sum {
    protected int value;
    protected int x;
    protected int y;

    public Sum(int x, int y) {
        this.value = x + y;
        this.x = x;
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
