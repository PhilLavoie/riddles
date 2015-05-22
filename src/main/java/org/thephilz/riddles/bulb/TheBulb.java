package org.thephilz.riddles.bulb;

/**
 *
 */
public class TheBulb {

    boolean initiallyOn;
    boolean on;

    public TheBulb(boolean initiallyOn) {
        this.initiallyOn = initiallyOn;
        this.on = initiallyOn;
    }

    public boolean isOn() { return this.on; }
    public boolean isOff() { return !isOn(); }

    public boolean isInitiallyOn() { return this.initiallyOn; }
    public boolean isInitiallyOff() { return !isInitiallyOn(); }

    public boolean isInInitialState() {
        return this.on == this.initiallyOn;
    }

    public void setOn() {
        this.on = true;
    }

    public void setOff() {
        this.on = false;
    }

    public void switchIt() {
        this.on = !this.on;
    }

    public void resetToInitial() {
        this.on = this.initiallyOn;
    }
}
