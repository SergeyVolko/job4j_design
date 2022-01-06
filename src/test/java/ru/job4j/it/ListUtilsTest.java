package ru.job4j.it;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveEven() {
        List<Integer> input = new ArrayList<>(
                Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ListUtils.removeIf(input, i -> i % 2 == 0);
        assertThat(input, is(Arrays.asList(1, 3, 5, 7, 9)));
    }

    @Test
    public void whenSetEven() {
        List<Integer> input = new ArrayList<>(
                Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        );
        ListUtils.replaceIf(input, i -> i % 2 == 0, 0);
        assertThat(input, is(Arrays.asList(0, 1, 0, 3, 0, 5, 0, 7, 0, 9, 0)));
    }

    @Test
    public void whenRemoveOtherList() {
        List<Integer> first = new ArrayList<>(
                Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        );
        List<Integer> second = new ArrayList<>(
                Arrays.asList(3, 4, 8, 9)
        );
        ListUtils.removeAll(first, second);
        assertThat(first, is(Arrays.asList(0, 1, 2, 5, 6, 7, 10)));
    }


}