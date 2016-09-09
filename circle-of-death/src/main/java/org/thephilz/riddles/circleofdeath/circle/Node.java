package org.thephilz.riddles.circleofdeath.circle;

import com.google.common.annotations.VisibleForTesting;

class Node<T> {
    private T element;

    private Node<T> next;
    private Node<T> previous;

    @VisibleForTesting
    Node(T element) {
        this.element = element;
    }

    static <T> Node<T> create(T element) {
        return new Node<>(element);
    }

    T getElement() {
        return element;
    }

    Node<T> getNext() {
        return next;
    }

    Node<T> getPrevious() {
        return previous;
    }

    void setNext(Node<T> next) {
        this.next = next;
    }

    void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}
