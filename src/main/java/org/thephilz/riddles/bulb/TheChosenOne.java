package org.thephilz.riddles.bulb;

/**
 *
 */
class TheChosenOne implements Participant {

    protected int remainingOccurrences;

    /**
     * @param noParticipants Including himself
     */
    protected TheChosenOne(int noParticipants) {
        this.remainingOccurrences = noParticipants;
    }

    @Override
    public void goToRoom(Room theRoom) {
        if (theRoom.getBulb().isInInitialState()) {
            this.remainingOccurrences--;
            theRoom.getBulb().switchIt();
        }
    }

    @Override
    public boolean hasEverybodyBeen() {
        return remainingOccurrences == 0;
    }
}
