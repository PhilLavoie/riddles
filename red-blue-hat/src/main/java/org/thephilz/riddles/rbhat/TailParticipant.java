package org.thephilz.riddles.rbhat;

import java.util.Collection;

/**
 *
 */
public class TailParticipant extends Participant {

    protected Hat firstParticipantAnswer;

    public TailParticipant(Collection<Hat> visibleHats, Hat referenceColor) {
        super(visibleHats, referenceColor);
    }

    @Override
    protected Hat whatColorIsYourHat() {
        return null;
    }
}
