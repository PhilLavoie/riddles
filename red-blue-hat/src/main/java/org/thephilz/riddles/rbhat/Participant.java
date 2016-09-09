package org.thephilz.riddles.rbhat;

import java.util.Collection;

/**
 *
 */
abstract class Participant {

    protected final long visibleRedHatsCount;
    protected final long visibleBlueHatsCount;
    protected final Hat referenceColor;

    protected Participant(Collection<Hat> visibleHats, Hat referenceColor) {
        this.visibleBlueHatsCount = visibleHats.stream().filter(hat -> hat == Hat.Blue).count();
        this.visibleRedHatsCount = visibleHats.stream().filter(hat -> hat == Hat.Red).count();
        this.referenceColor = referenceColor;
    }

    protected abstract Hat whatColorIsYourHat();
}
