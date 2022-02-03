package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor searchDuplicate = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("."), searchDuplicate);
        searchDuplicate.getDuplicate().forEach(p -> System.out.println(p.toAbsolutePath()));
    }
}
