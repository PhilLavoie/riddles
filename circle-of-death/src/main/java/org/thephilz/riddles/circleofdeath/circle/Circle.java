package org.thephilz.riddles.circleofdeath.circle;

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class Circle<T> implements Iterable<T> {

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

    public T getFirst() {
        checkState(!isEmpty());
        return start.getElement();
    }

    public void append(T element) {
        Node<T> newNode = Node.create(element);

        //They should always be null at the same time anyway.
        if (isEmpty()) {
            start = newNode;
            end = newNode;
            return;
        }

        link(end, newNode);
        link(newNode, end);
        incrementSize();
    }

    private void incrementSize() {
        size++;
    }

    private void decrementSize() {
        checkState(size > 0);
        size--;
    }

    public Iterator<T> removeAt(Iterator<T> position) {
        return removeAt((CircleIterator<T>) position);
    }

    public CircleIterator<T> removeAt(CircleIterator<T> position) {
        checkArgument(position.getNode() != null, "cannot remove on garbage iterator");
        position.invalidate();

        Node<T> removedNode = position.getNode();

        if (size() == 1) {
            start = null;
            end = null;
            return CircleIterator.empty();
        }

        link(removedNode.getPrevious(), removedNode.getNext());
        decrementSize();

        return CircleIterator.create(removedNode.getNext());
    }

    public boolean isEmpty() {
        return start == null && end == null;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return CircleIterator.create(start);
    }

}
