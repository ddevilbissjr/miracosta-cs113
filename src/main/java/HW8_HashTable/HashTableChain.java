package HW8_HashTable;

import java.util.*;

public class HashTableChain<K, V> implements Map<K, V> {
    private LinkedList<Map<K, V>>[] table;
    private int numKeys;
    private static final int CAPACITY = 101;
    private static final double LOAD_THRESHOLD = 3.0;

    public HashTableChain() {
        table = new LinkedList[CAPACITY];
    }

    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null;
        }
        for (Map<K, V> nextItem : table[index]) {
            if (nextItem.key.equals(key)) {
                return nextItem.value;
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (Map<K, V> nextItem : table[index]) {
            if (nextItem.key.equals(key)) {
                V oldVal = nextItem.value;
                nextItem.setValue(value);
                return oldVal;
            }
        }

        numKeys++;
        table[index].addFirst(new Map<K, V>(key, value));
        if (numKeys > (LOAD_THRESHOLD * table.length)) {
            rehash();
        }

        return null;
    }

    @Override
    public void putAll(java.util.Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    private void rehash() {
        LinkedList<Map<K, V>>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2 + 1];
        numKeys = 0;
        for (LinkedList<Map<K, V>> list : oldTable) {
            if (list != null) {
                for (Map<K, V> entry : list) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    @Override
    public int hashCode() {
        Hashtable<K, V> table = new Hashtable<>();
        for (int i = 0; i < this.table.length; i++) {
            for (Map<K, V> nextItem : this.table[i]) {
                if (nextItem != null) {
                    table.put(nextItem.key, nextItem.value);
                }
            }
        }

        return table.hashCode();
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>(size());
        for (LinkedList<Map<K, V>> list : table) {
            if (list != null) {
                for (Map<K, V> entry : list) {
                    if (entry != null) {
                        keySet.add(entry.getKey());
                    }
                }
            }
        }

        return keySet;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return false;
        }

        for (Map<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
            } else {
                for (Map<K, V> nextItem : table[i]) {
                    if (nextItem.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null;
        }
        for (Map<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                V value = entry.getValue();
                table[index].remove(entry);
                numKeys--;
                if (table[index].isEmpty()) {
                    table[index] = null;
                }
                return value;
            }
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return numKeys;
    }

    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        numKeys = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Hashtable)) {
            return false;
        }

        Hashtable c = (Hashtable) obj;

        return c.equals(this);
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
            } else {
                for (Map<K, V> nextItem : table[i]) {
                    toReturn += nextItem.getValue() + "\n";
                }
            }
        }

        return toReturn;
    }

    private static class Map<K, V> implements Entry<K, V> {
        private K key;
        private V value;


        public Map(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private class EntrySet extends AbstractSet<Entry<K, V>> {
        @Override
        public int size() {
            return numKeys;
        }

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new SetIterator();
        }
    }

    private class SetIterator implements Iterator<Entry<K, V>> {
        int index = 0;
        Map<K, V> lastItemReturned = null;
        Iterator<Map<K, V>> iter = null;

        @Override
        public boolean hasNext() {
            if (iter != null && iter.hasNext()) {
                return true;
            }
            do {
                index++;
                if (index >= table.length) {
                    return false;
                }
            }
            while (table[index] == null);

            iter = table[index].iterator();
            return iter.hasNext();
        }

        @Override
        public Entry<K, V> next() {
            if (iter.hasNext()) {
                lastItemReturned = iter.next();
                return lastItemReturned;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() throws IllegalStateException {
            if (lastItemReturned == null) {
                throw new IllegalStateException();
            } else {
                iter.remove();
                lastItemReturned = null;
            }
        }
    }
}
