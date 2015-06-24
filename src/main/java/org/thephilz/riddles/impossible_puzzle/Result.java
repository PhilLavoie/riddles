package org.thephilz.riddles.impossible_puzzle;

/**
 *
 */
public final class Result {
    protected int x;
    protected int y;

    public Result(int x, int y) {
        this.x = Math.min(x, y);
        this.y = Math.max(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
