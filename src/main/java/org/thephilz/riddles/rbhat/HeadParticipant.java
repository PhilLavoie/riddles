package org.thephilz.riddles.rbhat;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 */
class HeadParticipant extends Participant {

    protected final ArrayList<AnswerListener> listeners;

    protected HeadParticipant(Collection<Hat> visibleHats, Hat referenceColor) {
        super(visibleHats, referenceColor);
        this.listeners = new ArrayList<>();
    }



    @Override
    protected Hat whatColorIsYourHat() {
        if (referenceColor == null) {
            throw new IllegalStateException("must have a valid reference hat color");
        }

        Hat answer;
        if (referenceColor == Hat.Red) {
            //Blue means even, red means odd.
            answer = visibleRedHatsCount % 2 == 0 ? Hat.Blue : Hat.Red;
        }

        return visibleBlueHatsCount % 2 == 0 ? Hat.Blue : Hat.Red;
    }
}
