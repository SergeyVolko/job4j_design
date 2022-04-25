package ru.job4j.io.lectoresio.shildnio;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class DirTreeList {
    public static void main(String[] args) {
        String dirname = "src/main/java";
        System.out.println("Дepeвo каталогов, "
                + "начиная с каталога" + dirname + ":\n");
        try {
            Files.walkFileTree(Paths.get(dirname), new MyFileVisitor());
        } catch (IOException e) {
            System.out.println("Oшибкa ввода-вывода");
        }
    }
}
