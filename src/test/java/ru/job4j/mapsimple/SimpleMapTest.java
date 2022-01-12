package ru.job4j.mapsimple;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.map.User;

import javax.swing.*;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

import java.util.*;

public class SimpleMapTest  {
    List<User> users;
    @Before
    public void initData() {
        users = new ArrayList<>();
        users.add(new User("Ivan", 5,
                new GregorianCalendar(1986, Calendar.MAY, 14)));
        users.add(new User("Petr", 2,
                new GregorianCalendar(1990, Calendar.JUNE, 5)));
        users.add(new User("Stepan", 1,
                new GregorianCalendar(1991, Calendar.JULY, 11)));
        users.add(new User("Sergey", 2,
                new GregorianCalendar(455, Calendar.JANUARY, 3)));
        users.add(new User("gfrrg", 33,
                new GregorianCalendar(1488, Calendar.APRIL, 14)));
        users.add(new User("Andrey", 0,
                new GregorianCalendar(2014, Calendar.SEPTEMBER, 23)));
        users.add(new User("Zahar", 1,
                new GregorianCalendar(2000, Calendar.FEBRUARY, 19)));
        users.add(new User("Denis", 0,
                new GregorianCalendar(2020, Calendar.OCTOBER, 1)));
        users.add(new User("Alexey", 7,
                new GregorianCalendar(1957, Calendar.MARCH, 8)));
        users.add(new User("Igor", 2,
                new GregorianCalendar(1993, Calendar.AUGUST, 10)));
        users.add(new User("Ivan1", 5,
                new GregorianCalendar(1986, Calendar.MAY, 14)));
        users.add(new User("Petr1", 2,
                new GregorianCalendar(1990, Calendar.JUNE, 5)));
        users.add(new User("Stepan1", 1,
                new GregorianCalendar(1991, Calendar.JULY, 11)));
        users.add(new User("Sergey1", 2,
                new GregorianCalendar(34432, Calendar.JANUARY, 3)));
        users.add(new User("fefvfr", 444,
                new GregorianCalendar(1488, Calendar.APRIL, 14)));
        users.add(new User("Andrey1", 0,
                new GregorianCalendar(2014, Calendar.SEPTEMBER, 23)));
        users.add(new User("Zahar1", 1,
                new GregorianCalendar(2000, Calendar.FEBRUARY, 19)));
        users.add(new User("Denis1", 0,
                new GregorianCalendar(2020, Calendar.OCTOBER, 1)));
        users.add(new User("Alexey1", 7,
                new GregorianCalendar(1957, Calendar.MARCH, 8)));
        users.add(new User("Igor1", 2,
                new GregorianCalendar(1993, Calendar.AUGUST, 10)));
        users.add(new User("Ivan2", 5,
                new GregorianCalendar(1986, Calendar.MAY, 14)));
        users.add(new User("Petr2", 2,
                new GregorianCalendar(1990, Calendar.JUNE, 5)));
        users.add(new User("Stepan2", 1,
                new GregorianCalendar(1991, Calendar.JULY, 11)));
        users.add(new User("Sergey2", 2,
                new GregorianCalendar(1111, Calendar.JANUARY, 3)));
        users.add(new User("grfrfr", 11,
                new GregorianCalendar(1488, Calendar.APRIL, 14)));
        users.add(new User("Andrey2", 0,
                new GregorianCalendar(2014, Calendar.SEPTEMBER, 23)));
        users.add(new User("Zahar2", 1,
                new GregorianCalendar(2000, Calendar.FEBRUARY, 19)));
        users.add(new User("Denis2", 0,
                new GregorianCalendar(2020, Calendar.OCTOBER, 1)));
        users.add(new User("Alexey2", 7,
                new GregorianCalendar(1957, Calendar.MARCH, 8)));
        users.add(new User("Igor2", 2,
                new GregorianCalendar(1993, Calendar.AUGUST, 10)));
    }

    @Test
    public void whenPutThenGetValue() {
        SimpleMap<User, Integer> simpleMap = new SimpleMap<>();
        User user = new User("Ivan", 5,
                new GregorianCalendar(1986, Calendar.MAY, 14));
        simpleMap.put(user, 10);
        assertThat(simpleMap.get(user), is(10));
    }

    @Test
    public void whenPutThenGetNullValue() {
        SimpleMap<User, Integer> simpleMap = new SimpleMap<>();
        User user = new User("Ivan", 5,
                new GregorianCalendar(1986, Calendar.MAY, 14));
        User secondUser = new User("Vovan", 1,
                new GregorianCalendar(2001, Calendar.SEPTEMBER, 20));
        simpleMap.put(user, 10);
        assertThat(simpleMap.get(secondUser), is(nullValue()));
    }

    @Test
    public void whenPutTwoThenGetValue() {
        SimpleMap<User, Integer> simpleMap = new SimpleMap<>();
        User user = new User("Ivan", 5,
                new GregorianCalendar(1986, Calendar.MAY, 14));
        User secondUser = new User("Vovan", 1,
                new GregorianCalendar(2001, Calendar.SEPTEMBER, 20));
        simpleMap.put(user, 10);
        simpleMap.put(secondUser, 20);
        assertThat(simpleMap.get(secondUser), is(20));
    }

    @Test
    public void whenPutTenThenExpandGetValue() {
        SimpleMap<User, Integer> simpleMap = new SimpleMap<>();
        int score = 0;
        for (User user : users) {
            score += 10;
            simpleMap.put(user, score);
        }
        System.out.println();
        assertThat(simpleMap.get(new User("Denis1", 0,
                new GregorianCalendar(2020, Calendar.OCTOBER, 1))), is(180));
        assertThat(simpleMap.get(new User("Zahar1", 1,
                new GregorianCalendar(2000, Calendar.FEBRUARY, 19))), is(170));
    }

    @Test
    public void whenRemoveThenGetValueNull() {
        SimpleMap<User, Integer> simpleMap = new SimpleMap<>();
        User user = new User("Ivan", 5,
                new GregorianCalendar(1986, Calendar.MAY, 14));
        User secondUser = new User("Vovan", 1,
                new GregorianCalendar(2001, Calendar.SEPTEMBER, 20));
        simpleMap.put(user, 10);
        simpleMap.put(secondUser, 20);
        assertThat(simpleMap.get(secondUser), is(20));
        assertTrue(simpleMap.remove(secondUser));
        assertThat(simpleMap.get(secondUser), is(nullValue()));
    }

    @Test
    public void whenPutThenIterator() {
        SimpleMap<User, Integer> simpleMap = new SimpleMap<>();
        User user = new User("Ivan", 5,
                new GregorianCalendar(1986, Calendar.MAY, 14));
        User secondUser = new User("Vovan", 1,
                new GregorianCalendar(2001, Calendar.SEPTEMBER, 20));
        simpleMap.put(user, 10);
        simpleMap.put(secondUser, 20);
        Iterator<User> iterator = simpleMap.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(user));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(secondUser));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNoSuchElementException() {
        SimpleMap<User, Integer> simpleMap = new SimpleMap<>();
        User user = new User("Ivan", 5,
                new GregorianCalendar(1986, Calendar.MAY, 14));
        User secondUser = new User("Vovan", 1,
                new GregorianCalendar(2001, Calendar.SEPTEMBER, 20));
        simpleMap.put(user, 10);
        simpleMap.put(secondUser, 20);
        Iterator<User> iterator = simpleMap.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(user));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(secondUser));
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void shouldReturnConcurrentModificationException() {
        SimpleMap<User, Integer> simpleMap = new SimpleMap<>();
        User user = new User("Ivan", 5,
                new GregorianCalendar(1986, Calendar.MAY, 14));
        User secondUser = new User("Vovan", 1,
                new GregorianCalendar(2001, Calendar.SEPTEMBER, 20));
        simpleMap.put(user, 10);
        simpleMap.put(secondUser, 20);
        Iterator<User> iterator = simpleMap.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(user));
        simpleMap.put(new User("Igor2", 2,
                new GregorianCalendar(1993, Calendar.AUGUST, 10)), 30);
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(secondUser));
        iterator.next();
    }
}