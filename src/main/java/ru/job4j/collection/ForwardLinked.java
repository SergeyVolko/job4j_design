package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public T deleteFirst() {
        T result;
        if (head == null) {
            throw new NoSuchElementException();
        }
        result = head.value;
        Node<T> nextHead = head.next;
        head.next = null;
        head = nextHead;
        return result;
    }

    public boolean revert() {
        boolean result = false;
        if (head != null && head.next != null) {
            result = true;
            Node<T> prev = null;
            while (head != null) {
                Node<T> nextNode = head.next;
                head.next = prev;
                prev = head;
                head = nextNode;
            }
            head = prev;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
