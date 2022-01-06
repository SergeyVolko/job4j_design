package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddForElementNull() {
        Set<Integer> set = new SimpleSet<>();
        set.add(null);
        set.add(1);
        set.add(1);
        set.add(2);
        Iterator<Integer> iterator = set.iterator();
        assertTrue(null == iterator.next());
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertFalse(iterator.hasNext());
    }

}