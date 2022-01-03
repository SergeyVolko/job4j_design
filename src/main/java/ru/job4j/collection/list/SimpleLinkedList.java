package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    Node<E> first;
    Node<E> last;
    int size;
    int modCount;

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int index;
            final int expectedModCount = modCount;
            Node<E> currentNode = first;

            @Override
            public boolean hasNext() {
                return currentNode != null && expectedModCount == modCount;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    if (currentNode == null) {
                        throw new NoSuchElementException();
                    } else {
                        throw new ConcurrentModificationException();
                    }
                }
                E result = currentNode.item;
                currentNode = currentNode.next;
                return result;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
