package ru.job4j.asserj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotNull()
                .startsWith("first", "second")
                .endsWith("four", "five")
                .contains("three");
    }

    @Test
    void checkToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set)
                .isNotNull()
                .hasSize(5)
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(7);
                    assertThat(e.length()).isGreaterThan(3);
                })
                .anyMatch("three"::equals)
                .allMatch(e -> e.length() >= 4)
                .contains("second", "four");
    }

    @Test
    void checkToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map)
                .hasSize(5)
                .containsKeys("first", "second", "three", "four", "five")
                .containsValues(0, 1, 2, 3, 4);
    }


}