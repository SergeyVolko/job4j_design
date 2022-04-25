package ru.job4j.io.lectoresio.shildio.io;

import java.io.*;

public class CharArrayWriterDemo {
    public static void main(String[] args) {
        CharArrayWriter f = new CharArrayWriter();
        String s = "Эти данные должны быть выведены в массив";
        char[] buf = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);
        try {
            f.write(buf);
        } catch (IOException e) {
            System.out.println("Ошибка записи в буфер");
        }
        System.out.println("Буфер в виде символьной строки");
        System.out.println(f.toString());
        System.out.println("В массив");
        char[] c = f.toCharArray();
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]);
        }
        System.out.println("\nВ поток вывода типа  FileWriter()");
        // использовать оператор try с ресурсами для
        // управления потоком ввода-вывода данных в файл
        try (FileWriter f2 =
                     new FileWriter("src/main/java/ru/job4j/io/lectoresio/shildio/io/test.txt")) {
            f.writeTo(f2);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
            return;
        }
        System.out.println("Установка в исходное попложение");
        f.reset();
        for (int i = 0; i < 3; i++) {
            f.write('X');
        }
        System.out.println(f.toString());
    }
}
