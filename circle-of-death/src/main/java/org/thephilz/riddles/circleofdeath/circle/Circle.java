package org.thephilz.riddles.circleofdeath.circle;

import net.jcip.annotations.NotThreadSafe;

import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

@NotThreadSafe
public class Circle<T> {

    private Node<T> start;
    private Node<T> end;

    private int size;

    Circle() {
    }

    public static <T> Circle<T> empty() {
        return new Circle<>();
    }

    private static <T> void link(Node<T> previous, Node<T> next) {
        previous.setNext(next);
        next.setPrevious(previous);
    }

    public Position<T> getFirst() {
        checkState(!isEmpty());
        return Position.of(start);
    }

    public Position<T> getLast() {
        checkState(!isEmpty());
        return Position.of(end);
    }

    public Position<T> nextOf(Position<T> position) {
        return Position.of(position.getNode().getNext());
    }

    public Position<T> previousOf(Position<T> position) {
        return Position.of(position.getNode().getPrevious());
    }

    public void append(T element) {
        Node<T> newNode = Node.create(element);

        if (isEmpty()) {
            start = newNode;
        } else {
            link(end, newNode);
        }
        //Make it loop over itself if it's one node, or make it wrap around otherwise.
        link(newNode, start);
        end = newNode;
        incrementSize();
    }

    private void incrementSize() {
        size++;
    }

    private void decrementSize() {
        checkState(size > 0);
        size--;
    }

    /**
     * Removes at the given position.
     *
     * Invalidates the position so the user shall throw it away after the call.
     * Returns a new position referencing the next element to the currently removed one.
     * I.e., if this is the circle:
     * 1 - 5 - 10 - 15 - 20
     *         *
     * And the * shows where the position is at, then after the removal the state
     * of things looks like this:
     * 1 - 5 - 15 - 20
     *         *
     * If the removed position is the last element, then the returned position becomes the
     * first one (given there is at least one element left after removal).
     *
     * @param position Where to remove
     * @return A position right next to where the removal happened, null if no such element exists.
     * @throws IllegalArgumentException if the position is invalid.
     */
    public Position<T> removeAt(Position<T> position) {
        checkArgument(position.isValid(), "cannot remove on garbage position");

        Node<T> removedNode = position.getNode();
        position.invalidate();

        if (size() == 1) {
            start = null;
            end = null;
            decrementSize();
            return null;
        }

        link(removedNode.getPrevious(), removedNode.getNext());

        if (start == removedNode) {
            start = removedNode.getNext();
        } else if (end == removedNode) {
            end = removedNode.getPrevious();
        }

        decrementSize();
        return Position.of(removedNode.getNext());
    }

    public boolean isEmpty() {
        return start == null && end == null;
    }

    public int size() {
        return size;
    }

    public Circle<T> shallowCopy() {
        Circle<T> copy = Circle.empty();
        if (isEmpty()) {
            return copy;
        }

        forEach(copy::append);
        assert copy.size() == size();

        return copy;
    }

    public void forEach(Consumer<T> elementConsumer) {
        if (isEmpty()) {
            return;
        }

        Position<T> current = getFirst();
        elementConsumer.accept(current.getElement());

        final Position<T> last = getLast();
        while (!current.equals(last)) {
            current = nextOf(current);
            elementConsumer.accept(current.getElement());
        }
    }
}
