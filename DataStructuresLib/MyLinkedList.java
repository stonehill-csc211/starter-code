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
        /*
         * adds a new element at the specified position
         */
        // if adding to the start
        if(position == 0){
            Node<T> after = this.start;
            Node<T> newNode = new Node<T>();
            newNode.data = value;
            // change start to the new node
            this.start = newNode;
            // set the next node to the old start
            newNode.next = after;
            this.size++;
        } else if(position == this.size){
            // if we're adding to the end, call the other add method
            this.add(value);
        } else {
            // insert between position-1 and position
            Node<T> before = getNode(position - 1);
            Node<T> after = before.next;
            Node<T> newNode = new Node<T>();
            newNode.data = value;

            before.next = newNode;
            newNode.next = after;
            this.size++;
        }
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
        getNode(position).data = newValue;
    }

    @Override
    int size() {
        return this.size;
    }

    private boolean sublistContains(T value, Node n){
        /*
         * A recursive method to find an element at or after node n
         * This runs in O(n)
         */
        if(n.data == value) return true;
        else if(n.next == null) return false;
        else return sublistContains(value, n.next);

    }

    @Override
    boolean contains(T value) {
        return sublistContains(value, this.start);
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
        //ll.remove(1);
        //System.out.println(ll.get(1));
        System.out.println(ll.contains(4));
        System.out.println(ll.contains(5));
    }

}