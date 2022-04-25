package ru.job4j.io.lectoresio.videonio.task7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {
    public static void main(String[] args) throws IOException {
        String namePath = "src/main/java/ru/job4j/io/lectoresio/videonio/task6/file.txt";
        Path path = Paths.get(namePath);

        System.out.println("File name: " + path.getFileName());
        System.out.println("Path to file: " + path + "\n");

        System.out.println("File: " + (Files.exists(path) ? "exist" : "does not exist"));
        System.out.println("File: " + (Files.isHidden(path) ? "is hidden" : "is not hidden"));
        System.out.println("File: "
                + (Files.isWritable(path) ? "is available for writing" : "is not available for writing"));
        System.out.println("File: "
                + (Files.isReadable(path) ? "is available for reading" : "is not available for reading"));

        BasicFileAttributes fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
        System.out.println("File: " + (fileAttributes.isDirectory() ? "is directory" : "is not directory"));
        System.out.println("File: " + (fileAttributes.isRegularFile() ? "is regular file" : "is not regular file"));
        System.out.println("File: " + (fileAttributes.isSymbolicLink() ? "is symbolic link" : "is not symbolic link\n"));

        System.out.println("Last modification of file: " + fileAttributes.lastModifiedTime());
        System.out.println("File size: " + fileAttributes.size() + " bytes");
    }
}
