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
        int index;
        String left;
        String right;
        boolean isException = false;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            while (read.ready()) {
                String str = read.readLine();
                if (str.replaceAll("\\s+", "").length() == 0
                || str.charAt(0) == '#') {
                    continue;
                }
                index = str.indexOf('=');
                left = str.substring(0, index == -1 ? 0 : index);
                right = str.substring(index + 1);
                if (index < 1
                        || index == str.length() - 1
                        || left.charAt(left.length() - 1) == '.'
                        || right.charAt(right.length() - 1) == '.'
                ) {
                    isException = true;
                    break;
                }
                values.put(left, right);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isException) {
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
