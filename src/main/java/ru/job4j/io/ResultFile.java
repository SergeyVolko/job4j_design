package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static void main(String[] args) {
        int size = 10;
        int[][] table = Matrix.multiple(size);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int[] row : table) {
                for (int col : row) {
                    out.write((col + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Matrix {
        public static int[][] multiple(int size) {
            int[][] result = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    result[i][j] = (i + 1) * (j + 1);
                }
            }
            return result;
        }
    }
}
