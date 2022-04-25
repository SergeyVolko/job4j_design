package ru.job4j.io.lectoresio.videoio.task6;
import java.io.*;
public class CopyFile {
    public static void main(String[] args) {
        String source = "src/main/java/ru/job4j/io/lectoresio/videoio/task5/file.txt";
        String target = "src/main/java/ru/job4j/io/lectoresio/videoio/task6/folder";
        try (FileInputStream inputStream = new FileInputStream(source)) {
            File directory = new File(target);
            if (!directory.exists()) {
                directory.mkdir();
            }
            try (FileOutputStream outputStream = new FileOutputStream(target + "/file.txt")) {
               byte[] bytes = new byte[inputStream.available()];
               int length;
               while ((length = inputStream.read(bytes)) != -1) {
                   outputStream.write(bytes, 0, length);
               }
            } catch (IOException exception) {
                System.out.println("Output error");
            }
        } catch (IOException exception) {
            System.out.println("Input error");
        }
    }
}
