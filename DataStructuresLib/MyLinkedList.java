package DataStructuresLib;


public class MyLinkedList<T> extends MyList<T>{

    private class Node<T>{
        T data;
        Node<T> next;
    }

    Node<T> start, end;
    int size;

    private Node<T> getNode(int position){
        Node<T> current = this.start;
        for(int i = 0; i < position; i++){
            current = current.next;
        }
        return current;
    }

    public MyLinkedList(){
        this.start = null;
        this.end = null;
        this.size = 0;
    }

    @Override
    void add(T value) {
        Node<T> nodeToAdd = new Node<T>();
        nodeToAdd.data = value;

        if(this.start == null){
            // if we start from an empty list
            this.start = nodeToAdd;
            this.end = this.start;
        } else {
            // if we start from a nonempty list
            this.end.next = nodeToAdd;
            this.end = nodeToAdd;
        }
        this.size++;
    }

    @Override
    void add(T value, int position) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    T remove(int position) {
        Node<T> toRemove;
        if(position == 0){
            toRemove = this.start;
            this.start = this.start.next;
        } else if(position == this.size() - 1){
            Node<T> before = getNode(position - 1);
            toRemove = before.next;
            before.next = null;
            this.end = before;
        } else{
            Node<T> before = getNode(position - 1);
            toRemove = before.next;
            Node<T> after = toRemove.next;
            before.next = after;
        }
        this.size--;
        return toRemove.data;
    }

    @Override
    T get(int position) {
        return getNode(position).data;
    }

    @Override
    void set(int position, T newValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    int size() {
        return this.size;
    }

    @Override
    boolean contains(T value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> ll = new MyLinkedList<Integer>();
        ll.add(2);
        ll.add(1);
        ll.add(4);
        ll.remove(1);
        System.out.println(ll.get(1));
    }

}