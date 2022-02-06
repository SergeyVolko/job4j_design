package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static ArgsName argsName;
    private static File file;

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validateParameters(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Invalid parameters.");
        }
        argsName = ArgsName.of(args);
        file = new File(argsName.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    public static void main(String[] args) throws IOException {
        validateParameters(args);
        File zipFile =  new File(argsName.get("o"));
        String exclude = argsName.get("e");
        List<Path> pathList =
                Search.search(file.toPath(), p -> !p.toFile().getName().endsWith(exclude == null ? "" : exclude));
        List<File> source = pathList.stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
        packFiles(source, zipFile);
    }
}
