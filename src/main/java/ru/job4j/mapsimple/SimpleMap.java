package ru.job4j.mapsimple;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count  >= LOAD_FACTOR * capacity) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        boolean result = table[index] == null;
        if (result) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 8;
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }
    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] tableNew = new MapEntry[capacity];
        int length = tableNew.length;
        Arrays.stream(table)
                        .filter(Objects::nonNull)
                        .forEach(n -> tableNew[(length - 1) & hash(n.key.hashCode())] = n);
        table = tableNew;
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> mapEntry = table[indexFor(hash(key.hashCode()))];
        return  mapEntry == null || !mapEntry.key.equals(key) ? null : mapEntry.value;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].key.equals(key)) {
            result = true;
            table[index] = null;
            count--;
            modCount++;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int mod = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
