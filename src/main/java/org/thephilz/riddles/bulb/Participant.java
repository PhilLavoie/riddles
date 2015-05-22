package org.thephilz.riddles.bulb;

/**
 *
 */
public class Participant {

    protected final int myModIndex;
    protected final int noParticipants;
    protected final boolean imTheLastOne;
    protected int beenToRoomCount;
    protected boolean bulbWasInGoodStateAtMyTurn;

    protected enum Turn {
        Before,
        Mine,
        After
    }

    public Participant(int modIndex, int noParticipants) {
        assert modIndex >= 0;
        assert modIndex < noParticipants;
        this.myModIndex = modIndex;
        this.noParticipants = noParticipants;
        this.imTheLastOne = modIndex == noParticipants - 1;
        this.beenToRoomCount = 0;
        this.bulbWasInGoodStateAtMyTurn = false;
    }

    protected boolean isInGoodState(TheBulb bulb) {
        return bulb.isInInitialState();
    }

    protected void setInGoodState(TheBulb bulb) {
        bulb.resetToInitial();
    }

    protected Turn turn(int pickCount) {
        int mod = (pickCount - 1) % noParticipants;
        if (mod < myModIndex) {
            return Turn.Before;
        }
        if (mod == myModIndex) {
            return Turn.Mine;
        }
        return Turn.After;
    }

    public boolean goToRoom(Room room, int pickCount) {
        this.beenToRoomCount++;

        TheBulb bulb = room.getBulb();
        boolean hasFinishedTheGame = false;

        switch (turn(pickCount)) {
            case Before:
                if (bulbWasInGoodStateAtMyTurn) {
                    setInGoodState(bulb);
                }
                break;
            case Mine:
                if (isInGoodState(bulb)) {
                    bulbWasInGoodStateAtMyTurn = true;
                    hasFinishedTheGame = imTheLastOne;
                }
                break;
            case After:
                break;
            default:
                assert false;
        }
        return hasFinishedTheGame;
    }
}
