package ru.job4j.io.lectoresio.videoio.task2;
import java.io.*;

public class Catalog {
    public static void main(String[] args) {
        /*
        File directory = new File("src/main/java/ru/job4j/io/lectoresio/videoio/task2/backup");
        if (!directory.exists()) {
            System.out.println(directory.mkdir() ? "Successfully" : "Failed");
        } else {
            System.out.println("Already exists");
        }
        */
        String path = "src/main/java/ru/job4j/io/lectoresio/videoio/task2/backup";
        File directory = new File(path);

        if (directory.isDirectory()) {
            String[] content = directory.list();
            if (content != null) {
                for (String eachFile : content) {
                    File file = new File(path + "/" + eachFile);
                    System.out.println(file.isDirectory() ? (eachFile + " is directory") : (eachFile + " is file"));
                }
            }
        }
    }
}
