package org.thephilz.riddles.bulb;

/**
 *
 */
class NormalParticipant implements Participant {
    protected boolean hasSwitchedTheBulb;

    protected NormalParticipant() {
        this.hasSwitchedTheBulb = false;
    }

    @Override
    public void goToRoom(Room theRoom) {
        if (hasSwitchedTheBulb) {
            return;
        }

        if (!theRoom.getBulb().isInInitialState()) {
            theRoom.getBulb().switchIt();
            hasSwitchedTheBulb = true;
        }
    }

    @Override
    public boolean hasEverybodyBeen() {
        //Always return false, not his responsibility.
        return false;
    }
}
