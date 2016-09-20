package org.thephilz.riddles.circleofdeath.circle;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CircleTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testInitialState() {
        Circle<Integer> circle = Circle.empty();

        assertTrue(circle.isEmpty());
        assertEquals(0, circle.size());
    }

    @Test
    public void testGetFirstThrowsOnEmpty() {
        Circle<Integer> circle = Circle.empty();

        expectedException.expect(IllegalStateException.class);

        circle.getFirst();
    }

    @Test
    public void testGetLastThrowsOnEmpty() {
        Circle<Integer> circle = Circle.empty();

        expectedException.expect(IllegalStateException.class);

        circle.getLast();
    }

    @Test
    public void testNextOfThrowsOnNull() {
        Circle<Integer> circle = makeCircle();

        expectedException.expect(NullPointerException.class);
        circle.nextOf(null);
    }

    @Test
    public void testPreviousOfThrowsOnNull() {
        Circle<Integer> circle = makeCircle();

        expectedException.expect(NullPointerException.class);
        circle.previousOf(null);
    }

    @Test
    public void testOneElementState() {
        Circle<Integer> circle = makeCircle(10);

        assertFalse(circle.isEmpty());
        assertEquals(1, circle.size());
    }

    @Test
    public void testGetFirstOneElement() {
        Integer element = 13;
        Circle<Integer> circle = makeCircle(element);

        assertPositionEquals(element, circle.getFirst());
    }

    @Test
    public void testGetLastOneElement() {
        Integer element = 13;
        Circle<Integer> circle = makeCircle(element);

        assertPositionEquals(element, circle.getLast());
    }

    @Test
    public void testNextOneOneElement() {
        Circle<Integer> circle = makeCircle(45);
        Position<Integer> first = circle.getFirst();
        assertEquals(first, circle.nextOf(first));
    }

    @Test
    public void testPreviousOneOneElement() {
        Circle<Integer> circle = makeCircle(45);
        Position<Integer> last = circle.getLast();
        assertEquals(last, circle.previousOf(last));
    }

    @Test
    public void testStateMultipleElements() {
        List<Integer> elements = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Circle<Integer> circle = makeCircle(elements);

        assertFalse(circle.isEmpty());
        assertEquals(elements.size(), circle.size());
    }

    @Test
    public void testGetFirstMultipleElements() {
        List<Integer> elements = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Circle<Integer> circle = makeCircle(elements);

        assertPositionEquals(elements.get(0), circle.getFirst());
    }

    @Test
    public void testGetLastMultipleElements() {
        List<Integer> elements = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Circle<Integer> circle = makeCircle(elements);

        assertPositionEquals(elements.get(elements.size() - 1), circle.getLast());
    }

    @Test
    public void testNextOneMultipleElements() {
        List<Integer> elements = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Circle<Integer> circle = makeCircle(elements);

        Position<Integer> position = circle.getFirst();

        for (Integer integer : elements) {
            assertPositionEquals(integer, position);
            position = circle.nextOf(position);
        }

        //Rewound at the beginning
        assertPositionEquals(elements.get(0), position);
    }

    @Test
    public void testPreviousOneMultipleElements() {
        List<Integer> elements = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Circle<Integer> circle = makeCircle(elements);

        Position<Integer> position = circle.getLast();

        for (Integer integer : Lists.reverse(elements)) {
            assertPositionEquals(integer, position);
            position = circle.previousOf(position);
        }

        //Rewound at the end
        assertPositionEquals(elements.get(elements.size() - 1), position);
    }

    private void assertPositionEquals(Integer expected, Position<Integer> first) {
        assertEquals(expected, first.getElement());
    }

    private <T> Circle<T> makeCircle(Iterable<T> elements) {
        Circle<T> circle = Circle.empty();

        for (T element : elements) {
            circle.append(element);
        }

        return circle;
    }

    private <T> Circle<T> makeCircle(T element) {
        return makeCircle(Lists.newArrayList(element));
    }

    private <T> Circle<T> makeCircle() {
        return makeCircle(Collections.emptyList());
    }

}
