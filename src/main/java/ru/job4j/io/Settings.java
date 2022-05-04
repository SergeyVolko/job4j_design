package ru.job4j.io;

import java.io.InputStream;
import java.util.Properties;

public class Settings {
    private final Properties prs = new Properties();

    public void load(InputStream io) {
        try {
            this.prs.load(io);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return this.prs.getProperty(key);
    }

    public static void main(String[] args) throws Exception {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream io = loader.getResourceAsStream("log4j.properties")){
            settings.load(io);
        }
        System.out.println(settings.getValue("log4j.rootLogger"));
    }
}
