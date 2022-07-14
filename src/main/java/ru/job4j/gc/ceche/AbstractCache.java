package ru.job4j.gc.ceche;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V result = null;
        if (cache.containsKey(key)) {
            SoftReference<V> ref = cache.get(key);
            if (ref == null) {
                result = load(key);
            } else {
                result = ref.get();
            }
        }
        return result;
    }

    protected abstract V load(K key);

}
