package ru.job4j.io.lectoresio.videonio.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) {
        String source = "src/main/java/ru/job4j/io/lectoresio/videonio/task3/input.txt";
        String destination = "src/main/java/ru/job4j/io/lectoresio/videonio/task3/output.txt";
        try {
            Path sourcePath = Paths.get(source), destinationPath = Paths.get(destination);
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Input / Output error");
        }
    }
}
