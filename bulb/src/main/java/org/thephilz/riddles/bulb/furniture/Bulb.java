package org.thephilz.riddles.bulb.furniture;

/**
 *
 */
public class Bulb {

    protected static boolean INITIAL_STATE = true;
    protected boolean state;

    public Bulb() {
        this.state = INITIAL_STATE;
    }

    public boolean isInInitialState() {
        return this.state == INITIAL_STATE;
    }

    public void switchIt() {
        this.state = !this.state;
    }
}
