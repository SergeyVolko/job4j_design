package ru.job4j.io.lectoresio.videoio.task4;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {
    public static void main(String[] args) {
        String text = "Hello world";
        byte[] buffer = text.getBytes();
        try (FileOutputStream stream =
                     new FileOutputStream("src/main/java/ru/job4j/io/lectoresio/videoio/task4/file.txt")) {
            for (byte eachBufferElement : buffer) {
                stream.write(eachBufferElement);
            }
        } catch (IOException exception) {
            System.out.println("Output error");
        }
    }
}
