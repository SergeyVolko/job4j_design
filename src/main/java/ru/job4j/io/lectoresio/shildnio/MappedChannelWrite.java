package ru.job4j.io.lectoresio.shildnio;
// Записать данные в сопоставляемый файл
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class MappedChannelWrite {
    public static void main(String[] args) {
        String namePath = "src/main/java/ru/job4j/io/lectoresio/shildnio/test3.txt";
        //получить канал к файлу в блоке try с ресурсами
        try (FileChannel fChan = (FileChannel) Files.newByteChannel(Paths.get(namePath),
                StandardOpenOption.WRITE,
                StandardOpenOption.READ,
                StandardOpenOption.CREATE)) {
            //затем сопоставить файл с буфером
            MappedByteBuffer mBuf = fChan.map(FileChannel.MapMode.READ_WRITE, 0, 26);
            //записать заданное количество байтов в буфер
            for (int i = 0; i < 26; i++) {
                mBuf.put((byte) ('A' + i));
            }
        } catch (InvalidPathException e) {
            System.out.println("Ошибка указаия пути " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }
}
