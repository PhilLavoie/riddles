package org.thephilz.riddles.circleofdeath.circle;

import com.google.common.annotations.VisibleForTesting;

import java.util.Iterator;

import static com.google.common.base.Preconditions.checkState;

class CircleIterator<T> implements Iterator<T> {

    private boolean isValid;
    private Node<T> currentNode;

    @VisibleForTesting CircleIterator(Node<T> startingNode) {
        this.currentNode = startingNode;
        this.isValid = true;
    }

    static <T> CircleIterator<T> create(Node<T> startingNode) {
        return new CircleIterator<>(startingNode);
    }

    public static <T> CircleIterator<T> empty() {
        return create(null);
    }

    Node<T> getNode() {
        return currentNode;
    }

    @Override public boolean hasNext() {
        checkValid();
        return currentNode == null;
    }

    @Override public T next() {
        checkValid();
        Node<T> resultNode = currentNode;
        currentNode = currentNode.getNext();
        return resultNode.getElement();
    }

    private void checkValid() {
        checkState(isValid, "invalidated iterator");
    }

    void invalidate() {
        this.isValid = false;
    }
}
