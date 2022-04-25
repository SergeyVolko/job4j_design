package ru.job4j.io.lectoresio.videoio.task3;

import java.io.FileOutputStream;
import java.io.IOException;

public class DeprecateTry {
    public static void main(String[] args) {
        String text = "Some text";
        byte[] buffer = text.getBytes();
        try (FileOutputStream stream =
                     new FileOutputStream("src/main/java/ru/job4j/io/lectoresio/videoio/task3/file.txt")) {
            for (byte eachBufferElement : buffer) {
                stream.write(eachBufferElement);
            }
        } catch (IOException exception) {
            System.out.println("Input / Output error");
        }
    }
}
