package ru.job4j.gc.ceche;

public class Emulator {
    private AbstractCache<String, String> cache;

    public void setCache(String dir) {
        cache = new DirFileCache(dir);
    }

    public void loadFile(String file) {
        if (((DirFileCache) cache).getCachingDir() == null) {
            throw new IllegalArgumentException("Directory not exist.");
        }
        cache.load(file);
    }

    public String getFile(String file) {
        return cache.get(file);
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.setCache("src/main/java/ru/job4j/gc/dirfiles");
        emulator.loadFile("file1.txt");
        System.out.println(emulator.getFile("file1.txt"));
    }
}
