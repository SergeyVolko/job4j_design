package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    @Test
    void max() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of("Gamma", "Betta", "Delta", "Alpha", "Omega");
        String max = maxMin.max(list, String::compareTo);
        assertThat(max).isEqualTo("Omega");
    }

    @Test
    void min() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of("Gamma", "Betta", "Delta", "Alpha", "Omega");
        String min = maxMin.min(list, String::compareTo);
        assertThat(min).isEqualTo("Alpha");
    }

    @Test
    void whenMaxThenNull() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of();
        String max = maxMin.max(list, String::compareTo);
        assertThat(max).isNull();
    }
}