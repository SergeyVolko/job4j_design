package ru.job4j.io.lectoresio.shildnio;
// Использовать отображение для чения данных из файла
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class MappedChannelRead {
    public static void main(String[] args) {
        String namePath = "src/main/java/ru/job4j/io/lectoresio/shildnio/test.txt";
        //получить канал к файлу в блоке операторов try с ресурсами
        try (FileChannel fChan = (FileChannel) Files.newByteChannel(Paths.get(namePath))) {
            //получить размер файла
            long fSize = fChan.size();
            // а теперь отобразить файл в буфер
            MappedByteBuffer mBuf = fChan.map(FileChannel.MapMode.READ_ONLY, 0, fSize);
            //читать байты из буфера и выводить их на экран
            for (int i = 0; i < fSize; i++) {
                System.out.print((char) mBuf.get());
            }
            System.out.println();
        } catch (InvalidPathException e) {
            System.out.println("Ошибка указания пути " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }
}
