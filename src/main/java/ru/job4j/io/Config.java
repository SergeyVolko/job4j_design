package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String[] res = new String[2];
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(str -> str.replaceAll("\\s+", "").length() != 0
                            && str.charAt(0) != '#'
                    )
                    .forEach(str -> {
                        int index = str.indexOf('=');
                        res[0] = str.substring(0, index);
                        res[1] = str.substring(index + 1);
                        values.put(res[0].equals("") ? null : res[0], res[1].equals("") ? null : res[1]);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (res[0].length() > 0 && res[0].charAt(res[0].length() - 1) == '.'
                || res[1].length() > 0 && res[1].charAt(res[1].length() - 1) == '.') {
            throw new IllegalArgumentException();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }

}
