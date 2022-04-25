package ru.job4j.io.lectoresio.shildnio;
// Записать данные в файл средствами системы ввода-вывода NIO
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class ExplicitChannelWrite {
    public static void main(String[] args) {
        String namePath = "src/main/java/ru/job4j/io/lectoresio/shildnio/test2.txt";
        //получить канал к файлу в блоке try с ресурсами
        try (FileChannel fChan = (FileChannel) Files.newByteChannel(Paths.get(namePath),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            //создать буфер
            ByteBuffer mBuf = ByteBuffer.allocate(26);
            //записать некоторое количество байтов в буфер
            for (int i = 0; i < 26; i++) {
                mBuf.put((byte) ('A' + i));
            }
            //подготовить буфер к записи данных
            mBuf.rewind();
            //записать данные из буфера в выходной канал
            fChan.write(mBuf);
        } catch (InvalidPathException e) {
            System.out.println("Ошибка выбора пути " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода" + e);
            System.exit(1);
        }
    }
}
