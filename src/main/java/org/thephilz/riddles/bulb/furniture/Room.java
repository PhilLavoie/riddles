package org.thephilz.riddles.bulb.furniture;

/**
 *
 */
public class Room {

    protected final Bulb bulb;

    public Room(Bulb bulb) {
        this.bulb = bulb;
    }

    public Bulb getBulb() {
        return this.bulb;
    }
}
