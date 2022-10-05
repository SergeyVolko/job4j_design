package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled
class GeneratorTest {

    @Test
    void whenProduceThenCorrect() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        );
        Generator generator = (template, args) -> null;
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThat(generator.produce(template, map)).isEqualTo("I am a Petr Arsentev, Who are you? ");
    }

    @Test
    void whenProduceThenExcessKeyInMap() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev",
                "subject", "you",
                "excess", "value"
        );
        Generator generator = (template, args) -> null;
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThrows(IllegalArgumentException.class, () -> generator.produce(template, map));
    }

    @Test
    void whenProduceThenExcessInTemplate() {
        Map<String, String> map = Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        );
        Generator generator = (template, args) -> null;
        String template = "I am a ${name}, Who are ${subject}? My excess key: ${key}";
        assertThrows(IllegalArgumentException.class, () -> generator.produce(template, map));
    }
}