package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean isNext = false;
        for (int i = row; i < data.length && !isNext; i++) {
            if (column == data[i].length) {
                column = 0;
                continue;
            }
            for (int j = column; j < data[i].length; j++) {
                row = i;
                isNext = true;
                break;
            }
        }
        return isNext;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
