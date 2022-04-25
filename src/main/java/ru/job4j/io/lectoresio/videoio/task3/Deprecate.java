package ru.job4j.io.lectoresio.videoio.task3;
import java.io.*;

public class Deprecate {
    public static void main(String[] args) {
        String text = "Some text";
        byte[] buffer = text.getBytes();
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream("src/main/java/ru/job4j/io/lectoresio/videoio/task3/file.txt");
            for (byte eachBufferElement : buffer) {
                stream.write(eachBufferElement);
            }
        } catch (IOException exception) {
            System.out.println("Input / Output error");
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException exception) {
                System.out.println("Error of closing file.txt");
            }
        }
    }
}
