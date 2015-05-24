package org.thephilz.riddles.bulb;

/**
 *
 */
class Bulb {

    protected static boolean INITIAL_STATE = true;
    protected boolean state;

    protected Bulb() {
        this.state = INITIAL_STATE;
    }

    protected boolean isInInitialState() {
        return this.state == INITIAL_STATE;
    }

    protected void switchIt() {
        this.state = !this.state;
    }
}
