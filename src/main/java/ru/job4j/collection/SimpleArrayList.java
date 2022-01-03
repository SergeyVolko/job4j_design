package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            container = grow();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    private T[] grow() {
        return Arrays.copyOf(container, container.length == 0 ? 2 : 2 * size);
    }
    @Override
    public T set(int index, T newValue) {
        T result = get(index);
        container[index] = newValue;
        return result;
    }

    @Override
    public T remove(int index) {
        T element = get(index);
        System.arraycopy(container, index + 1,
                container, index,
                container.length - index - 1);
        container[size - 1] = null;
        size--;
        modCount++;
        return element;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                        throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}
