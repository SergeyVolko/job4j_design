package ru.job4j.io.lectoresio.shildio.io;

import java.io.*;

public class FileOutputStreamDemoTryRecource {
    public static void main(String[] args) {
        String source = "Now is the tirne for all good men\n"
                + " to соmе to the aid of their country\n"
                + " and рау their due taxes.";
        byte[] buf = source.getBytes();
        try (FileOutputStream f0 =
                     new FileOutputStream("src/main/java/ru/job4j/io/lectoresio/shildio/io/file1.txt");
             FileOutputStream f1 =
                     new FileOutputStream("src/main/java/ru/job4j/io/lectoresio/shildio/io/file2.txt");
             FileOutputStream f2 =
                     new FileOutputStream("src/main/java/ru/job4j/io/lectoresio/shildio/io/file3.txt")) {
            // записать данные в первый файл
            for (int i = 0; i < buf.length; i += 2) {
                f0.write(buf[i]);
            }
            // записать данные во второй файл
            f1.write(buf);
            // записать данные в третий файл
            f2.write(buf, buf.length - buf.length / 4, buf.length / 4);

        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода-вывода.");
        }
    }
}
