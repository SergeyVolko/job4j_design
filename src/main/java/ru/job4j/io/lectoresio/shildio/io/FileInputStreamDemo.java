package ru.job4j.io.lectoresio.shildio.io;
import java.io.*;

// Продемонстрировать применение класса FileinputStream
public class FileInputStreamDemo {
    public static void main(String[] args) {
        int size;
        //Для автоматического закрытия потока ввода
        //используется оператор try с ресурсами
        String fileName = "src/main/java/ru/job4j/io/lectoresio/shildio/io/FileInputStreamDemo.java";
        try (FileInputStream f = new FileInputStream(fileName)) {
            size = f.available();
            System.out.println("Общее количество доступных байтов: " + size);
            int n = size / 40;
            System.out.println("Первые " + n + " байтов, "
                                + " прочитанных из файла по очереди "
                                + "методом read()");
            for (int i = 0; i < n; i++) {
                System.out.print((char) f.read());
            }
            System.out.println("\nBce еще доступно: " + f.available());
            System.out.println("Чтeниe следующих " + n + " байтов по очереди методом read(b[])");
            byte[] b = new byte[n];
            if (f.read(b) != n) {
                System.err.println("Нельзя прочитать " + n + " байтов.");
            }
            System.out.println(new String(b, 0, n));
            size = f.available();
            System.out.println("\nBce еще доступно: " + size);
            System.out.println("Пpoпycтить половину оставшихся байтов методом skip() ");
            f.skip(size / 2);
            System.out.println("Bce еще доступно: " + f.available());
            System.out.println("Чтeниe " + n / 2
                    + " байтов, размещаемых в конце массива");
            if (f.read(b, n / 2, n / 2) != n / 2) {
                System.err.println("Heльзя прочитать" + n / 2 + "байтов.");
            }
            System.out.println(new String(b, 0, b.length));
            System.out.println("\nBce еще доступно: " + f.available());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
