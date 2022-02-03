package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> map = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty =
                new FileProperty(file.toFile().length(), file.toFile().getName());
        if (map.containsKey(fileProperty)) {
            map.get(fileProperty).add(file);
        } else {
            List<Path> paths = new ArrayList<>();
            paths.add(file);
            map.put(fileProperty, paths);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getDuplicate() {
        return map.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.toList());
    }

}
