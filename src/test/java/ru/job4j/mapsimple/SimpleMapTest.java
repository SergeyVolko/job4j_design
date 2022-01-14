package ru.job4j.mapsimple;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

import java.util.*;

public class SimpleMapTest  {

    @Test
    public void whenPutThenGetValue() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        String key = "Ivan";
        simpleMap.put("Ivan", 10);
        assertThat(simpleMap.get(key), is(10));
    }

    @Test
    public void whenPutThenGetNullValue() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        String key = "Stepan";
        assertThat(simpleMap.get("Ivan"), is(nullValue()));
    }

    @Test
    public void whenPutTwoThenGetValue() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        String user = "Petr";
        String secondUser = "Stepan";
        String thirdUser = "Ivan";
        simpleMap.put(user, 10);
        simpleMap.put(secondUser, 20);
        simpleMap.put("Ivan", 30);
        assertThat(simpleMap.get(secondUser), is(20));
        assertThat(simpleMap.get(thirdUser), is(nullValue()));
    }

    @Test
    public void whenPutTenThenExpandGetValueAndRemove() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("user1", 10);
        simpleMap.put("user2", 20);
        simpleMap.put("user3", 30);
        simpleMap.put("user4", 40);
        simpleMap.put("user5", 50);
        simpleMap.put("user6", 60);
        simpleMap.put("user7", 70);
        simpleMap.put("user8", 80);
        assertThat(simpleMap.capacity(), is(16));
        assertThat(simpleMap.get("user7"), is(70));
        assertTrue(simpleMap.remove("user7"));
        assertThat(simpleMap.get("user7"), is(nullValue()));
    }

    @Test
    public void whenPutThenIterator() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("user1", 10);
        simpleMap.put("user2", 20);
        simpleMap.put("user3", 30);
        Iterator<String> iterator = simpleMap.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is("user2"));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is("user1"));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is("user3"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPutThenIteratorThenNoSuchException() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        Iterator<String> iterator = simpleMap.iterator();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutThenIteratorThenConcurrentException() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("user1", 10);
        simpleMap.put("user2", 20);
        Iterator<String> iterator = simpleMap.iterator();
        iterator.next();
        simpleMap.put("user3", 30);
        iterator.next();
    }
}