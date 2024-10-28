package DataStructuresLib;

public abstract class MyMap<K,V> {
    public abstract void add(K key, V value);
    public abstract boolean contains(K key);
    public abstract V remove(K key);
    public abstract int size();
    public abstract V get(K key);
    public abstract V set(K key, V newValue);
    public abstract void clear();
}
