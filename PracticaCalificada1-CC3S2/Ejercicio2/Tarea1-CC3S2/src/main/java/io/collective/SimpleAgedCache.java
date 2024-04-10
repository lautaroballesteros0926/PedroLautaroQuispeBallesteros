
package io.collective;

import java.time.Clock;
import java.util.List;

public class SimpleAgedCache {
    private static final int Capacidadinicial = 10;  // Definimos la capacida inicial

    private CacheEntry[] cache;             // Creamos una array de CacheEntry
    private int size;

    public SimpleAgedCache() {
        this.cache = new CacheEntry[Capacidadinicial];
        this.size = 0;
    }
    // verificamos que la longitud de cahe sea vacia
    public void put(String key, String value, long maxAgeMillis) {
        if (size == cache.length) {
            expandCapacity();
        }
        cache[size++] = new CacheEntry(key, value, maxAgeMillis);
    }

    // solamente retornamos si es vacia
    public boolean isEmpty() {
        return size == 0;
    }

    // retornamos tamaño
    public int size() {
        return size;
    }
    // verificamos
    public String get(String key) {
        for (int i = 0; i < size; i++) {
            if (cache[i].getKey().equals(key) && !cache[i].isExpired()) {
                return cache[i].getValue();
            }
        }
        return null;
    }
    //  expansion de espacio 

    private void expandCapacity() {
        int newCapacity = cache.length * 2;
        CacheEntry[] newCache = new CacheEntry[newCapacity];
        System.arraycopy(cache, 0, newCache, 0, size);
        cache = newCache;
    }

    private static class CacheEntry {
        private final String key;
        private final String value;
        private final long expiryTimeMillis;

        CacheEntry(String key, String value, long maxAgeMillis) {
            this.key = key;
            this.value = value;
            this.expiryTimeMillis = System.currentTimeMillis() + maxAgeMillis;
        }

        String getKey() {
            return key;
        }

        String getValue() {
            return value;
        }

        boolean isExpired() {
            return System.currentTimeMillis() >= expiryTimeMillis;
        }
    }

}

