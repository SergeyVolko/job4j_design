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
            container = Arrays.copyOf(container, 2 * size);
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T result = container[index];
        container[index] = newValue;
        modCount++;
        return result;
    }

    @Override
    public T remove(int index) {
        T element = get(index);
        System.arraycopy(container, index + 1,
                container, index,
                container.length - index - 1);
        container[container.length - 1] = null;
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
                return index != size && expectedModCount == modCount;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    if (index == size) {
                        throw new NoSuchElementException();
                    } else {
                        throw new ConcurrentModificationException();
                    }
                }
                return container[index++];
            }
        };
    }
}
