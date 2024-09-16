package DataStructuresLib;

abstract class MyList<T> {
    abstract void add(T value);
    abstract void add(T value, int position);
    abstract T remove(int position);
    abstract T get(int position);
    abstract void set(int position, T newValue);
    abstract int size();
    abstract boolean contains(T value);
    abstract boolean isEmpty();
    abstract void clear();
}
