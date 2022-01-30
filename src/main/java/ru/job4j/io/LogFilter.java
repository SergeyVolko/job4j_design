package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> result = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            result = in.lines()
                    .filter(s -> {
                        String[] arr = s.split(" ");
                        return arr[arr.length - 2].equals("404");
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
