package ru.job4j.gc.ceche;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Emulator {
    public static final int SET_DIRECTORY = 1;
    public static final int LOAD_FILE = 2;
    public static final int GET_FILE = 3;
    public static final int EXIT = 4;
    public static final String DELIMITER_MENU = "-".repeat(30);
    public static final String DELIMITER_ACTION = "=".repeat(30);
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
        String menu = """
        %s
        1 set directory
        2 load file
        3 get file
        4 exit
        %s
        Input variant:
        """.formatted(DELIMITER_MENU, DELIMITER_MENU);
        System.out.print(menu);
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean exit = false;
            while (!exit) {
                menu();
                int variant = Integer.parseInt(reader.readLine());
                if (variant == SET_DIRECTORY) {
                    System.out.println(DELIMITER_ACTION);
                    System.out.println("Input directory:");
                    setCache(reader.readLine());
                    System.out.println(DELIMITER_ACTION);
                } else if (variant == LOAD_FILE) {
                    System.out.println(DELIMITER_ACTION);
                    System.out.println("Input load file:");
                    System.out.println(loadFile(reader.readLine()));
                    System.out.println(DELIMITER_ACTION);
                } else if (variant == GET_FILE) {
                    System.out.println(DELIMITER_ACTION);
                    System.out.println("Input get file:");
                    System.out.println(getFile(reader.readLine()));
                    System.out.println(DELIMITER_ACTION);
                } else if (variant == EXIT) {
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
