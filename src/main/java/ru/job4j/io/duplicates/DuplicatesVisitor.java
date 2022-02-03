package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, Path> map = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty =
                new FileProperty(file.toFile().length(), file.toFile().getName());
        Path path;
        if (map.containsKey(fileProperty)) {
            path = map.get(fileProperty);
            if (path != null) {
                System.out.println(path.toAbsolutePath());
                map.replace(fileProperty, path, null);
            }
            System.out.println(file.toAbsolutePath());
        } else {
            map.put(fileProperty, file);
        }

        return super.visitFile(file, attrs);
    }

}
