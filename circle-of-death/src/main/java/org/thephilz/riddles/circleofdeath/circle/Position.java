package org.thephilz.riddles.circleofdeath.circle;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class Position<T> {
    private final Node<T> node;
    private boolean isValid;

    Position(Node<T> node) {
        this.node = node;
        this.isValid = true;
    }

    static <T> Position of(Node<T> node) {
        checkNotNull(node);
        return new Position(node);
    }

    @Override public boolean equals(Object o) {
        checkInvariant();
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Position<T> position = (Position<T>) o;

        return node.equals(position.node);
    }

    @Override public int hashCode() {
        return node.hashCode();
    }

    public T getElement() {
        checkInvariant();
        return node.getElement();
    }

    void invalidate() {
        checkInvariant();
        this.isValid = false;
    }

    Node<T> getNode() {
        return node;
    }

    boolean isValid() {
        return isValid;
    }

    private void checkInvariant() {
        checkState(isValid);
        checkNotNull(node);
    }
}
