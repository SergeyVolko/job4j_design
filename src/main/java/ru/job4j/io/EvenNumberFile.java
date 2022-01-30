package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            Arrays.stream(text.toString().split(System.lineSeparator()))
                    .map(Integer::valueOf)
                    .forEach(e -> {
                        System.out.println(e % 2 == 0);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
