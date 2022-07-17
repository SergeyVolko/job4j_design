package ru.job4j.gc.ceche;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Emulator {
    private DirFileCache cache;

    public void setCache(String dir) {
        cache = new DirFileCache(dir);
    }

    public void loadFile(String file) {
        validate();
        cache.put(file, cache.load(file));
    }

    public String getFile(String file) {
        return cache.get(file);
    }

    private void validate() {
        File dir = new File(cache.getCachingDir());
        if (!dir.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", dir.getAbsoluteFile()));
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", dir.getAbsoluteFile()));
        }
    }

    private void menu() {
        String stringBuilder = "-".repeat(30)
                + "\n"
                + "1 set directory\n"
                + "2 load file\n"
                + "3 get file\n"
                + "4 exit\n"
                + "-".repeat(30)
                + "\n";
        System.out.println(stringBuilder);
    }

    public void run() throws IOException {
        boolean exit = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            menu();
            int variant = Integer.parseInt(reader.readLine());
            if (variant == 1) {
                System.out.println("Input directory:");
                setCache(reader.readLine());
                System.out.println("=".repeat(30));
            } else if (variant == 2) {
                System.out.println("Input load file:");
                loadFile(reader.readLine());
                System.out.println("=".repeat(30));
            } else if (variant == 3) {
                System.out.println("Input get file:");
                System.out.println(getFile(reader.readLine()));
                System.out.println("=".repeat(30));
            } else if (variant == 4) {
                System.out.println("Exit.");
                System.out.println("=".repeat(30));
                exit = true;
            } else {
                System.out.println("Wrong menu.");
                System.out.println("=".repeat(30));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Emulator emulator = new Emulator();
        emulator.run();
    }
}
