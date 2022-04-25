package ru.job4j.io.lectoresio.shildnio;
// Более компактный способ открытия канала

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class ExplicitChannelRead2 {
    public static void main(String[] args) {
        int count = 0;
        String namePath = "src/main/java/ru/job4j/io/lectoresio/shildnio/test.txt";
        // Здесь канал открывается по пути, возвращаемому
        // методом Paths.get() в виде объекта типа Path
        // Переменная типа filepath больше не нужна
        try (SeekableByteChannel fChan = Files.newByteChannel(Paths.get(namePath))) {
            // выделить память под буфер
            ByteBuffer mBuf = ByteBuffer.allocate(128);
            do {
                // читать данные из файла в буфер
                count = fChan.read(mBuf);
                if (count != -1) {
                    //подготовить буфер к чтению из него данных
                    mBuf.rewind();
                    //читать данные из буфера и выводить их на экран как символы
                    for (int i = 0; i < count; i++) {
                        System.out.print((char) mBuf.get());
                    }
                }
            } while (count != -1);
            System.out.println();
        } catch (InvalidPathException  e) {
            System.out.println("Ошибка указания пути " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }
}
