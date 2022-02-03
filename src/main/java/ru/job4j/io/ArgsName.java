package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.size() == 0) {
            throw new IllegalArgumentException("Empty args.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        String left;
        String right;
        int index;
        for (String str : args) {
            index = str.indexOf('=');
            if (str.charAt(0) != '-' || index <= 0 || index == str.length() - 1) {
                throw new IllegalArgumentException("Pattern violation.");
            }
            left = str.substring(1, index);
            right = str.substring(index + 1);
            values.put(left, right);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
