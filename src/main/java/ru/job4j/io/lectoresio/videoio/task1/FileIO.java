package ru.job4j.io.lectoresio.videoio.task1;
import java.io.*;

public class FileIO {
    public static void main(String[] args) {
        String path = "src/main/java/ru/job4j/io/lectoresio/videoio/task1/sample.txt";
        File file = new File(path);

        System.out.println("File name: " + file.getName() + "\n");

        System.out.println("Path: " + file.getPath());
        System.out.println("Absolutely path: " + file.getAbsolutePath() + "\n");

        System.out.println("Parent directory: " + file.getParent());
        System.out.println("File length: " + file.length() + " bytes\n");

        System.out.println("File " + (file.exists() ? "exists" : "does not exists"));
        System.out.println("File " + (file.canWrite() ? "writable" : "not writable"));
        System.out.println("File " + (file.canRead() ? "readable" : "not readable"));
        System.out.println("File " + (file.isDirectory() ? "is directory" : "is not directory"));
        System.out.println("File " + (file.isFile() ? "is regular file" : "is not regular file"));
        System.out.println("File " + (file.isHidden() ? "hidden" : "is not hidden") + "\n");

        System.out.println("Total space: " + CapacityFormatter.toGigabytes(file.getTotalSpace()) + "Gb");
        System.out.println("Free space: " + CapacityFormatter.toGigabytes(file.getFreeSpace()) + "Gb");
    }

    private static class CapacityFormatter {
        private static long toGigabytes(long capacity) {
            return capacity / (long) Math.pow(10, 9);
        }
    }
}
