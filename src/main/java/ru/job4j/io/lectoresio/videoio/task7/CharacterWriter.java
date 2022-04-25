package ru.job4j.io.lectoresio.videoio.task7;
import java.io.*;

public class CharacterWriter {
    public static void main(String[] args) {
        String text = "Hello world";
        String fileName = "src/main/java/ru/job4j/io/lectoresio/videoio/task7/file.txt";
        char[] chars = new char[text.length()];
        text.getChars(0, chars.length, chars, 0);
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (char eachChar : chars) {
                fileWriter.write(eachChar);
            }
        } catch (IOException exception) {
            System.out.println("Output error");
        }
    }
}
