package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        index += (int) IntStream.range(index, data.length)
                .takeWhile(i -> data[i] % 2 != 0)
                .count();
        return  index < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
