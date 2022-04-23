package ru.job4j.io.search;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.function.Predicate;

public class SearchCriteria {
    private File out;
    private ArgsName argsName;
    Predicate<Path> condition;
    public static void main(String[] args) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.writeFile(args);
    }

    private List<Path> validate(String[] args) {
        argsName = ArgsName.of(args);
        if (argsName.get("d") == null
                || argsName.get("n") == null
                || argsName.get("t") == null
                || argsName.get("o") == null) {
            throw new IllegalArgumentException("Wrong argument.");
        }
        File dir = new File(argsName.get("d"));
        if (!dir.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", dir.getAbsoluteFile()));
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", dir.getAbsoluteFile()));
        }
        out = new File(argsName.get("o"));
        if (out.isDirectory()) {
            throw new IllegalArgumentException(String.format("Is directory %s", out.getAbsoluteFile()));
        }
        setCondition();
        List<Path> pathList = null;
        try {
             pathList = Search.search(dir.toPath(), condition);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathList;
    }

    private void setCondition() {
        String typeSearch = argsName.get("t");
        if ("name".equals(typeSearch)) {
            condition = p -> p.toFile().getName().equals(argsName.get("n"));
        } else {
            PathMatcher matcher;
            String pattern;
            if ("mask".equals(typeSearch)) {
                pattern = "glob:" + argsName.get("n");
            } else if ("regex".equals(typeSearch)) {
                pattern = "regex:" + argsName.get("n");
            } else {
                throw new IllegalArgumentException("Wrong type mask.");
            }
            matcher = FileSystems.getDefault().getPathMatcher(pattern);
            condition = path -> matcher.matches(path.getFileName());
        }

    }

    public void writeFile(String[] args) {
        List<Path> pathList = this.validate(args);
        try (PrintWriter pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
            for (Path path : pathList) {
                pw.println(path.toAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Input / Output error: " + e);
        }
    }
}
