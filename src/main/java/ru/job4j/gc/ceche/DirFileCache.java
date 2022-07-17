package ru.job4j.gc.ceche;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String result = null;
        try {
            result = Files.readString(Path.of(cachingDir, key));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getCachingDir() {
        return cachingDir;
    }

    public static void main(String[] args) {
        DirFileCache dirFileCache = new DirFileCache("src/main/java/ru/job4j/gc/dirfiles");
        System.out.println(dirFileCache.load("file1.txt"));
    }
}
