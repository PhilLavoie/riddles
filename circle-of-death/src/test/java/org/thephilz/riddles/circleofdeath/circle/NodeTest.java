package org.thephilz.riddles.circleofdeath.circle;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class NodeTest {

    @Mock String element;

    @Before
    public void setUp() {
        element = "My gorgeous toto";
    }

    private <T> Node<T> makeNode(T element) {
        return new Node(element);
    }

    private <T> Node<T> mockedNode() {
        return mock(Node.class);
    }

    @Test
    public void testConstructor() {
        Node<String> node = new Node<>(element);

        assertSame(element, node.getElement());
    }

    @Test
    public void testCreate() {
        Node<String> node = Node.create(element);

        assertSame(element, node.getElement());
    }

    @Test
    public void testSetNext() {
        Node<String> node = makeNode(element);

        Node<String> nextNode = mockedNode();
        node.setNext(nextNode);
        assertSame(nextNode, node.getNext());
    }

    @Test
    public void testSetPrevious() {
        Node<String> node = makeNode(element);

        Node<String> previousNode = mockedNode();
        node.setPrevious(previousNode);
        assertSame(previousNode, node.getPrevious());
    }
}
