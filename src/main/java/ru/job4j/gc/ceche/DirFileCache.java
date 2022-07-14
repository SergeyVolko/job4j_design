package ru.job4j.gc.ceche;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        File dir = new File(cachingDir);
        validate(dir);
        File[] res = dir.listFiles((d, name) -> key.equals(name));
        if (res.length == 0) {
            throw new IllegalArgumentException("Not file in current directory!");
        }
        return putFile(key, res[0]);
    }

    public String putFile(String key, File file) {
        String result = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            result = reader.lines().collect(Collectors.joining("\n"));
            super.cache.put(key, new SoftReference<>(result));
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void validate(File dir) {
        if (!dir.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", dir.getAbsoluteFile()));
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", dir.getAbsoluteFile()));
        }
    }

    public String getCachingDir() {
        return cachingDir;
    }

    public static void main(String[] args) {
        DirFileCache dirFileCache = new DirFileCache("src/main/java/ru/job4j/gc/dirfiles");
        System.out.println(dirFileCache.load("file1.txt"));
    }
}
