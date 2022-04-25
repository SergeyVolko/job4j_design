package ru.job4j.io.lectoresio.shildio.file;

import java.io.File;
import java.io.FilenameFilter;

public class DirListOnly {
    public static void main(String[] args) {
        String dirname = "src/main/java/ru/job4j/io/lectoresio/shildio/file";
        File f1 = new File(dirname);
        System.out.println(f1.exists());
        FilenameFilter only = new OnlyExt("html");
        String[] s = f1.list(only);
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }
}
