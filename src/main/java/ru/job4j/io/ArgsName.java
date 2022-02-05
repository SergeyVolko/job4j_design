package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        String[] tokens;
        if (args.length == 0) {
            throw new IllegalArgumentException("No argument");
        }
        for (String str : args) {
            tokens = str.split("=");
            if (tokens.length != 2
                    || tokens[0].length() == 0
                    || tokens[1].length() == 0) {
                throw new IllegalArgumentException("Pattern violation."
                + Arrays.toString(tokens) + ":" + Arrays.toString(args));
            }
            values.put(tokens[0].substring(1), tokens[1]);
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
