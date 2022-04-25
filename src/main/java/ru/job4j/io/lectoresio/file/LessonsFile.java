package ru.job4j.io.lectoresio.file;

import java.io.File;

public class LessonsFile {
    public static void main(String[] args) {
        String path = "C:/projects/job4j_design/src/main/java/ru/job4j/io/lectoresio/file/sample.txt";
        File file = new File(path);

        System.out.println("File name: " + file.getName() + "\n");

        System.out.println("Path: " + file.getPath());
        System.out.println("Absolute path: " + file.getAbsolutePath() + "\n");

        System.out.println("Parent directory: " + file.getParent());
        System.out.println("File length: " + file.length() + " bytes \n");

        System.out.println("File " + (file.exists() ? "exists" : "does not exist"));
        System.out.println("File " + (file.canWrite() ? "writable" : "not writable"));
        System.out.println("File " + (file.canRead() ? "readable" : "not readable"));
        System.out.println("File " + (file.isDirectory() ? "is directory" : "is not directory"));
        System.out.println("File " + (file.isFile() ? "is regular file" : "is not regular file"));
        System.out.println("File " + (file.isHidden() ? "hidden" : "is not hidden" + "\n"));

        System.out.println("Total space: " + CapacityFormatter.toGigabytes(file.getTotalSpace()) + "Gb");
        System.out.println("Free space: " + CapacityFormatter.toGigabytes(file.getFreeSpace()));
    }

    private static class CapacityFormatter {
        private static long toGigabytes(long capacity) {
            return capacity / (long) Math.pow(10, 9);
        }
    }
}
