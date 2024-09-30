package DataStructuresLib;

public class LinkedQueue<T> extends MyQueue<T> {

    private class Node<T>{
        T data;
        Node<T> next;
    }

    Node<T> start, end;
    int size;

    public LinkedQueue(){
        this.size = 0;
        this.start = null;
        this.end = null;
    }

    @Override
    public void enqueue(T newValue) {
        if(this.end == null){
            // if we are inserting the first element
            this.start = new Node();
            this.start.data = newValue;
            this.end = this.start;
            this.size++;
        } else {
            // if we're adding onto the back
            Node newNode = new Node();
            newNode.data = newValue;
            this.end.next = newNode;
            this.end = newNode;
            this.size++;
        }
    }

    @Override
    public T dequeue() {
        if(this.size == 0){ 
            throw new ArrayIndexOutOfBoundsException("Dequeuing from empty queue");
        }
        
        T retval = this.start.data;
        this.start = this.start.next;
        if(this.size == 1){
            this.end = null;
        }

        this.size--;
        
        return retval;
    }

    @Override
    public T getFront() {
        if(this.size == 0) return null;
        else return this.start.data;
    }

    @Override
    public int size() {
        return this.size;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node current = this.start;
        if(this.start == null) return "[ ]";
        while(current != this.end){
            sb.append(current.data + " ");
            current = current.next;
        }
        return sb.toString() + " Size " + this.size;
    }

    public static void main(String[] args){
        LinkedQueue<String> q = new LinkedQueue<String>();
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        System.out.println(q.size() + " should be 3");
        String output = q.dequeue();
        System.out.println(output + " should be A.");
        System.out.println(q.size() + " should be 2");
    }
}
