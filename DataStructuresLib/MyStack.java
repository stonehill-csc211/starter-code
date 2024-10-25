package DataStructuresLib;


public abstract class MyStack<T> {
    public abstract void push(T val);
    public abstract T pop();
    public abstract T peek();
    public abstract int size();
}