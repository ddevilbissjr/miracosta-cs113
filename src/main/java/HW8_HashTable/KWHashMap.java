package HW8_HashTable;

import java.lang.*;

public interface KWHashMap<K, V> {
    public V get(Object key);
    V put(K key, V value);
    V remove(Object key);
    boolean isEmpty();
    int size();
}
