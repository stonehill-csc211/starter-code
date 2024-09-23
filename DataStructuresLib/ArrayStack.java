package DataStructuresLib;

public class ArrayStack<T> extends MyStack<T>{
    Object[] data;
    int end;

    public ArrayStack(){
        this.data = new Object[10];
        this.end = 0;
    }

    @Override
    public void push(T val){
        this.data[end] = val;
        end++;
        if(end == this.data.length){
            Object[] newArr = new Object[this.data.length * 2];
            for(int i = 0; i < this.data.length; i++){
                newArr[i] = this.data[i];
            }
            this.data = newArr;
        }
    }

    public T pop(){
        if(this.end == 0){
            throw new ArrayIndexOutOfBoundsException("Popping from empty stack!");
        }

        @SuppressWarnings("unchecked")
        T retval = (T)this.data[this.end-1];
        this.end--;
        return retval;
    }

    public T peek(){
        if(this.end == 0){
            throw new ArrayIndexOutOfBoundsException("Popping from empty stack!");
        }
        return (T)this.data[this.end - 1];
    }

    public int size(){
        return this.end;
    }

    public void pushAll(T[] newValues){
        for(int i = 0; i < newValues.length; i++){
            this.push(newValues[i]);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.end; i++){
            sb.append(this.data[i].toString());
            sb.append(" ");
        }
        return sb.toString();
    }
}
