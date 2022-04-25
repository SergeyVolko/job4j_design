package ru.job4j.io.lectoresio.shildnio;
// Продемонстрировать потоковый вывод на основе системы NIO
import java.io.*;
import java.nio.file.*;

public class NIOStreamWrite {
    public static void main(String[] args) {
        String pathName = "src/main/java/ru/job4j/io/lectoresio/shildnio/test4.txt";
        // открыть файл и получить связанный с ним поток вывода
        try (OutputStream fout =
                     new BufferedOutputStream(Files.newOutputStream(Paths.get(pathName)))) {
            //вывести в поток заданное количество байт
            for (int i = 0; i < 26; i++) {
                fout.write((byte) ('A' + i));
            }
        } catch (InvalidPathException e) {
            System.out.println("Ошибка указания пути " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }

    }

}
