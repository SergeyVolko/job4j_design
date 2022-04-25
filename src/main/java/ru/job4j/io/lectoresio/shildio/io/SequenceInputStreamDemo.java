package ru.job4j.io.lectoresio.shildio.io;
// Продемонстрировать организацию последовательного ввода
// В этой программе используется традиционный способ
// закрытия файла
import java.util.Vector;
import java.io.*;

public class SequenceInputStreamDemo {
    public static void main(String[] args) {
        int c;
        Vector<String> files = new Vector<String>();
        files.addElement("src/main/java/ru/job4j/io/lectoresio/shildio/io/file1.txt");
        files.addElement("src/main/java/ru/job4j/io/lectoresio/shildio/io/file2.txt");
        files.addElement("src/main/java/ru/job4j/io/lectoresio/shildio/io/file3.txt");
        InputStreamEnumerator ise = new InputStreamEnumerator(files);
        InputStream input = new SequenceInputStream(ise);
        try {
            while ((c = input.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка открытия файла.");
        } catch (IOException e) {
            System.out.println("Ошибка закрытия потока ввода SequenceInputStream");
        }
    }
}
