package ru.job4j.gc.ceche;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Emulator {
    public static final String SET_DIRECTORY = "1 set directory\n";
    public static final String LOAD_FILE = "2 load file\n";
    public static final String GET_FILE = "3 get file\n";
    public static final String EXIT = "4 exit\n";
    public static final String DELIMITER_MENU = "-".repeat(30) + "\n";
    public static final String DELIMITER_ACTION = "=".repeat(30);
    public static final String INPUT = "Input variant:";
    private DirFileCache cache;

    public void setCache(String dir) {
        cache = new DirFileCache(dir);
    }

    public String loadFile(String file) {
        validate();
        return cache.get(file);
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
        String menu = DELIMITER_MENU + SET_DIRECTORY + LOAD_FILE + GET_FILE + EXIT + DELIMITER_MENU + INPUT;
        System.out.println(menu);
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean exit = false;
            while (!exit) {
                menu();
                int variant = Integer.parseInt(reader.readLine());
                if (variant == 1) {
                    System.out.println(DELIMITER_ACTION);
                    System.out.println("Input directory:");
                    setCache(reader.readLine());
                    System.out.println(DELIMITER_ACTION);
                } else if (variant == 2) {
                    System.out.println(DELIMITER_ACTION);
                    System.out.println("Input load file:");
                    System.out.println(loadFile(reader.readLine()));
                    System.out.println(DELIMITER_ACTION);
                } else if (variant == 3) {
                    System.out.println(DELIMITER_ACTION);
                    System.out.println("Input get file:");
                    System.out.println(getFile(reader.readLine()));
                    System.out.println(DELIMITER_ACTION);
                } else if (variant == 4) {
                    System.out.println(DELIMITER_ACTION);
                    System.out.println("Exit.");
                    System.out.println(DELIMITER_ACTION);
                    exit = true;
                } else {
                    System.out.println(DELIMITER_ACTION);
                    System.out.println("Wrong menu.");
                    System.out.println(DELIMITER_ACTION);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        Emulator emulator = new Emulator();
        emulator.run();
    }
}
