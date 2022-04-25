package ru.job4j.io.lectoresio.videonio.task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String text = "Hello world";
        byte[] bytes = text.getBytes();
        String fileName = "src/main/java/ru/job4j/io/lectoresio/videonio/task4/file.txt";
        try (OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            for (byte eachByte : bytes) {
                outputStream.write(eachByte);
            }
        } catch (IOException e) {
            System.out.println("Input / Output error");
        }
    }
}
