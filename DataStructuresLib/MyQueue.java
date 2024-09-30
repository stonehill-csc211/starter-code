package DataStructuresLib;

public abstract class MyQueue<T> {
    public abstract void enqueue(T newValue);
    public abstract T dequeue();
    public abstract T getFront();
    public abstract int size();
}
