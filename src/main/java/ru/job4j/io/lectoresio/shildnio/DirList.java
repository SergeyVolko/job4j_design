package ru.job4j.io.lectoresio.shildnio;
// Вывести содержимое каталога
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class DirList {
    public static void main(String[] args) {
        String dirname = "src/main/java/ru/job4j/io/lectoresio/shildnio";
        // получить и обработать поток ввода каталога
        // в блоке оператора try
        try (DirectoryStream<Path> dirstrm = Files.newDirectoryStream(Paths.get(dirname), "test*.txt")) {
            System.out.println("Kaтaлoг " + dirname);
            // Класс DirectoryStream реализует
            // интерфейс Iterable, поэтому для вывода
            // содержимого каталога можно организовать
            // цикл for в стиле for each
            for (Path entry : dirstrm) {
                BasicFileAttributes attribs = Files.readAttributes(
                        entry, BasicFileAttributes.class);
                if (attribs.isDirectory()) {
                    System.out.print("<DIR> ");
                } else {
                    System.out.print("      ");
                }
                System.out.println(entry.getName(8));
            }
        } catch (InvalidPathException e) {
            System.out.println("Oшибкa указания пути" + e);
        } catch (NotDirectoryException e) {
            System.out.println(dirname + "не является каталогом.");
        } catch (IOException e) {
            System.out.println("Oшибкa ввода-вывода: " + e);
        }
    }
}
