package org.thephilz.riddles.bulb.participants;

import java.util.Iterator;
import java.util.function.Supplier;

/**
 *
 */
public class CyclicalSupplier<T> implements Supplier<T> {
    protected Iterable<T> payload;
    protected Iterator<T> theIter;

    public CyclicalSupplier(Iterable<T> payload) {
        this.payload = payload;
        this.theIter = payload.iterator();
        assert theIter.hasNext();
    }

    @Override
    public T get() {
        T answer = theIter.next();

        //Rewind.
        if (!theIter.hasNext()) {
            theIter = payload.iterator();
        }

        return answer;
    }
}
