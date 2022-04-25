package ru.job4j.io.lectoresio.shildio.io;
// Продемонстрировать применение класса FileWriter
import java.io.*;

public class FileWriterDemo {
    public static void main(String[] args) {
        String source = "Now is the tirne for all good men\n"
                + " to соmе to the aid of their country\n"
                + " and рау their due taxes.";
        char[] buffer = new char[source.length()];
        source.getChars(0, source.length(), buffer, 0);
        try (FileWriter f0 =
                     new FileWriter("src/main/java/ru/job4j/io/lectoresio/shildio/io/file1.txt");
             FileWriter f1 =
                     new FileWriter("src/main/java/ru/job4j/io/lectoresio/shildio/io/file2.txt");
             FileWriter f2 =
                     new FileWriter("src/main/java/ru/job4j/io/lectoresio/shildio/io/file3.txt")) {
            // записать данные в первый файл
            for (int i = 0; i < buffer.length; i += 2) {
                f0.write(buffer[i]);
            }
            // записать данные во второй файл
            f1.write(buffer);
            // записать данные в третий файл
            f2.write(buffer, buffer.length - buffer.length / 4, buffer.length / 4);

        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода-вывода.");
        }
    }
}
