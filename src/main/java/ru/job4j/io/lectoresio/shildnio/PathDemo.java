package ru.job4j.io.lectoresio.shildnio;
//Получить сведения о пути к файлу и самом файле
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class PathDemo {
    public static void main(String[] args) {
        String pathName = "src/main/java/ru/job4j/io/lectoresio/shildnio/test.txt";
        Path filepath = Paths.get(pathName);
        System.out.println("Имя файла: " + filepath.getName(1));
        System.out.println("Пyть к файлу: " + filepath);
        System.out.println("Aбcoлютный путь к файлу: " + filepath.toAbsolutePath());
        System.out.println("Poдитeльcкий каталог: " + filepath.getParent());
        if (Files.exists(filepath)) {
            System.out.println("Фaйл существует");
        } else {
            System.out.println("Фaйл не существует");
        }
        try {
            if (Files.isHidden(filepath)) {
                System.out.println("Файл скрыт");
            } else {
                System.out.println("Файл не скрыт");
            }
        } catch (IOException e) {
            System.out.println("Omибкa ввода-вывода: " + e);
        }
        Files.isWritable(filepath);
        System.out.println("Фaйл доступен для записи");
        Files.isReadable(filepath);
        System.out.println("Файл доступен для чтения");
        try {
            BasicFileAttributes attribs = Files.readAttributes(
                    filepath, BasicFileAttributes.class);
            if (attribs.isDirectory()) {
                System.out.println("Этo каталог");
            } else {
                System.out.println("Этo не каталог");
            }
            if (attribs.isRegularFile()) {
                System.out.println("Этo обычный файл");
            } else {
                System.out.println("Этo не обычный файл");
            }
            if (attribs.isSymbolicLink()) {
                System.out.println("Этo символическая ссылка");
            } else {
                System.out.println("Этo не символическая ссылка");
            }
            System.out.println("Bpeмя последней модификации "
                    + "файла: " + attribs.lastModifiedTime());
            System.out.println("Paзмep файла: " + attribs.size()
                    + " байтов");
        } catch (IOException e) {
            System.out.println("Omибкa ввода-вывода: " + e);
        }
    }
}
