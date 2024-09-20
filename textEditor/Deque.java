package textEditor;

public class Deque<T>{

    Node head, tail;
    int len;

    private class Node{
        Node next, prev;
        T value;
        Node(T value){
            this.value = value;
            this.next = null;
            this.prev = null;
        }
        void setNext(Node next){
            this.next = next;
        }
        Node getNext(){
            return this.next;
        }

        void setPrev(Node prev){
            this.prev = prev;
        }
        Node getPrev(){
            return this.prev;
        }

        T getValue(){
            return this.value;
        }
    }

    public Deque(){
        this.head = null;
        this.tail = null;
        this.len = 0;
    }

    void addToFront(T item){
        if(this.head == null){
            this.head = new Node(item);
            this.tail = this.head;
        } else{
            Node oldHead = this.head;
            Node newHead = new Node(item);
            oldHead.setNext(newHead);
            newHead.setPrev(oldHead);
            this.head = newHead;
        }
        this.len++;
    }

    T removeFront() throws IndexOutOfBoundsException{
        if(this.head == null){
            throw new IndexOutOfBoundsException();
        } else if(this.head.prev == null){
            T value = this.head.getValue();
            this.head = null;
            this.tail = null;
            this.len--;
            return value;
        }

        Node oldHead = this.head;
        this.head = this.head.getPrev();
        this.head.setNext(null);
        this.len--;

        return oldHead.getValue();
    }

    T getFront(){
        if(this.head == null) return null;
        return this.head.getValue();
    }

    void addToBack(T item){
        if(this.tail == null){
            this.head = this.tail = new Node(item);
        } else {
            Node newNode = new Node(item);
            Node oldTail = this.tail;
            oldTail.setPrev(newNode);
            newNode.setNext(oldTail);
            this.tail = newNode;
        }
        this.len++;
    }

    T removeBack(){
        if(this.tail == null){
            throw new IndexOutOfBoundsException();
        } else if(this.tail.next == null){
            T value = this.tail.getValue();
            this.head = null;
            this.tail = null;
            this.len--;
            return value;
        }

        Node oldTail = this.tail;
        this.tail = this.tail.getNext();
        this.tail.setPrev(null);
        this.len--;

        return oldTail.getValue();
    }

    T getBack(){
        if(this.tail == null) return null;
        return this.tail.getValue();
    }

    int size(){
        return this.len;
    }

    void clear(){
        this.head = null;
        this.tail = null;
    }

    public static void main(String[] args){
        Deque<Integer> deque = new Deque<Integer>();
        deque.addToFront(1);
        System.out.println(deque.removeBack() + " should be 1");
        deque.addToBack(2);
        System.out.println(deque.removeFront() + " should be 2");
        deque.addToFront(3);
        deque.addToFront(4);
        deque.addToFront(5);
        System.out.println(deque.getFront() + " should be 5");
        System.out.println(deque.getBack() + " should be 3");

    }
}
