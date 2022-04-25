package ru.job4j.io.lectoresio.videonio.task5;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        int content;
        String fileName = "src/main/java/ru/job4j/io/lectoresio/videonio/file.txt";
        try (InputStream inputStream = Files.newInputStream(Paths.get(fileName))) {
            do {
                content = inputStream.read();
                if (content != -1) {
                    System.out.print((char) content);
                }
            } while (content != -1);
        } catch (IOException e) {
            System.out.println("Input / Output error");
        }
    }
}
