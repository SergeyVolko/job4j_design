package ru.job4j.io.lectoresio.videoio.task5;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        String fileName = "src/main/java/ru/job4j/io/lectoresio/videoio/task5/file.txt";
        try (FileInputStream stream = new FileInputStream(fileName)) {
            int symbol;
            while ((symbol = stream.read()) != -1) {
                System.out.print((char) symbol);
            }
        } catch (IOException exception) {
            System.out.println("Input error");
        }
    }
}
