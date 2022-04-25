package ru.job4j.io.lectoresio.shildio.io;
// Продемонстрировать применение класса ByteArrayOutputStream
import java.io.*;

public class ByteArrayOutputStreamDemo {
    public static void main(String[] args) {
        ByteArrayOutputStream f = new ByteArrayOutputStream();
        String s = "Эти данные должны быть выведены в массив";
        byte[] buf = s.getBytes();
        try {
            f.write(buf);
        } catch (IOException e) {
            System.out.println("Ошибка записи в буфер");
        }
        System.out.println("Буфер в виде символьной строки");
        System.out.println(f.toString());
        System.out.println("В массив");
        byte[] b = f.toByteArray();
        for (int i = 0; i < b.length; i++) {
            System.out.print((char) b[i]);
        }
        System.out.println("\nВ поток вывода типа OutputStream()");
        // использовать оператор try с ресурсами для
        // управления потоком ввода-вывода данных в файл
        try (FileOutputStream f2 =
                     new FileOutputStream("src/main/java/ru/job4j/io/lectoresio/shildio/io/test.txt")) {
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
