package ru.job4j.io.lectoresio.shildnio;
// Простой пример применения метода walkFileTree()
//для вывода дерева каталогов
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

// создать специальную версию класса SimpleFileVisitor,
// в которой переопределяется метод visitFile()
public class MyFileVisitor extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attribs) {
        System.out.println(path);
        return FileVisitResult.CONTINUE;
    }
}
