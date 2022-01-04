package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int sizeIn = 0;
    public T poll() {
        if (sizeIn == 0) {
            throw new NoSuchElementException();
        }

        for (int i = 0; i < sizeIn; i++) {
            out.push(in.pop());
        }
        T result = out.pop();
        sizeIn--;
        for (int i = 0; i < sizeIn; i++) {
            in.push(out.pop());
        }
        return result;
    }

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}
