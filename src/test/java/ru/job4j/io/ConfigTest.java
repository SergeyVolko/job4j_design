package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutOtherComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value(" "), is(" Petr Arsentev"));
        assertThat(config.value("surname "), is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutOtherCommentException() {
        String path = "./data/pair_without_comment_error.properties";
        Config config = new Config(path);
        config.load();
    }
}