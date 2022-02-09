package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private ArgsName argsName;
    private File file;

    public void packFiles(List<File> sources, File target) {
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

    public void validateParameters(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Invalid parameters.");
        }
        argsName = ArgsName.of(args);
        String par = argsName.get("e");
        if (par == null) {
            throw new IllegalArgumentException("There is no parameter e");
        }
        if (!par.startsWith(".")) {
            throw new IllegalArgumentException("Invalid parameter e");
        }
        file = new File(argsName.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    public ArgsName getArgsName() {
        return argsName;
    }

    public File getFile() {
        return file;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.validateParameters(args);
        File zipFile =  new File(zip.getArgsName().get("o"));
        String exclude = zip.getArgsName().get("e");
        List<Path> pathList =
                Search.search(zip.getFile().toPath(), p -> !p.toFile().getName().endsWith(exclude));
        List<File> source = pathList.stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
        zip.packFiles(source, zipFile);
    }
}
