package ru.job4j.io.lectoresio.shildio.io;
// Продемонстрировать применение класса FileReader
import java.io.*;

public class FileReaderDemo {
    public static void main(String[] args) {
        String name = "src/main/java/ru/job4j/io/lectoresio/shildio/io/FileReaderDemo.java";
        try (FileReader fr = new FileReader(name)) {
            int c;
            // прочитать и вывести содержимое файла
            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }
}
