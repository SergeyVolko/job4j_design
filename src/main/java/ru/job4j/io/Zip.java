package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        sources.forEach(f -> packSingleFile(f, target));
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        String directory  = argsName.get("d");
        String output  = argsName.get("o");
        if (directory == null || output == null) {
            throw new IllegalArgumentException("Invalid parameters.");
        }
        File file = new File(directory);
        File zipFile =  new File(output);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        String exclude = argsName.get("e");

        List<Path> pathList =
                Search.search(file.toPath(), p -> !p.toFile().getName().endsWith(exclude == null ? "" : exclude));
        List<File> source = pathList.stream()
                                .map(Path::toFile)
                                .collect(Collectors.toList());
        packFiles(source, zipFile);
    }
}
