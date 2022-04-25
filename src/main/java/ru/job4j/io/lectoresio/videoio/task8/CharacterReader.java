package ru.job4j.io.lectoresio.videoio.task8;
import java.io.*;
public class CharacterReader {
    public static void main(String[] args) {
        String fileName = "src/main/java/ru/job4j/io/lectoresio/videoio/task6/folder/file.txt";
        try (FileReader fileReader = new FileReader(fileName)) {
            int symbol;
            while ((symbol = fileReader.read()) != -1) {
                System.out.print((char) symbol);
            }
        } catch (IOException exception) {
            System.out.println("Input error");
        }
    }
}
