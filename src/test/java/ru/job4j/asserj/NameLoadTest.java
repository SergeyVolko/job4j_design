package ru.job4j.asserj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyArgument() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkNotSymbolEquality() {
        NameLoad nameLoad = new NameLoad();
        String word = "test";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(word)
                .hasMessageContaining("the symbol \"=\"");
    }

    @Test
    void checkStartWithSymbolEquality() {
        NameLoad nameLoad = new NameLoad();
        String word = "=test";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(word)
                .hasMessageContaining("a key");
    }

    @Test
    void checkLastWithSymbolEquality() {
        NameLoad nameLoad = new NameLoad();
        String word = "test=";
        assertThatThrownBy(() -> nameLoad.parse(word))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(word)
                .hasMessageContaining("a value");
    }


}