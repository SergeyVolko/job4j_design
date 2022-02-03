package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
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
    public void whenPairWithoutCommentFail1() {
        String path = "./data/fail1.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithoutCommentTest1() {
        String path = "./data/test1.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"), is("val="));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutCommentFail2() {
        String path = "./data/fail2.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutCommentFail3() {
        String path = "./data/fail3.properties";
        Config config = new Config(path);
        config.load();
    }

}