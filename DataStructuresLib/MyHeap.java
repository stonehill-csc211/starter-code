public class MyHeap<T extends Comparable> {
    /*
     * Our implementation of a MaxHeap
     * Useful for making priority queues!
     */

    Comparable<T>[] heap;
    int size;

    public MyHeap(){
        heap = new Comparable[10];
        size = 0;
    }

    void swap(int i, int j){
        Comparable<T> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void bubbleUp(){
        // start from the bottom
        int current = this.size;
        int parent;
        while(current >= 1){
            parent = current / 2;
            // if the current element is larger than its parent
            if(this.heap[current].compareTo((T)this.heap[parent]) > 0){
                // swap with the parent
                swap(current, parent);
                current = parent;
            } else break;
        }
    }

    public void add(T newItem){
        if(this.size == heap.length - 1){
            // resize
        }
        heap[this.size+1] = newItem;
        this.size++;
        this.bubbleUp();

    }

    private void reheap(int current){
        int leftChild,rightChild,largerChild;
        while(current <= this.size){
            leftChild = current * 2;
            rightChild = current * 2 + 1;

            if(this.heap[leftChild].compareTo((T)this.heap[rightChild]) > 0){
                largerChild = leftChild;
            } else {
                largerChild = rightChild;
            }
            if(this.heap[largerChild].compareTo((T)this.heap[current]) > 0){
                swap(current, largerChild);
                current = largerChild;
            } else {
                break;
            }
        }
    }

    public T removeMax(){
        T retval = (T) this.heap[1];
        this.heap[1] = this.heap[this.size];
        this.size--;
        reheap(this.size);

        return retval;
    }

    public void heapify(T[] data){
        /*
         * Convert data into a heap and store it in this object
         */
        this.heap = new Comparable[data.length + 1];
        for(int i = 0; i < data.length; i++){
            this.heap[i+1] = data[i];
        }
        for(int i = this.size / 2; i >= 1; i--){
            reheap(i);
        }
    }

    public static void heapSort(Comparable[] data){
        MyHeap h = new MyHeap<Comparable>();
        h.heapify(data);
        for(int i = data.length-1; i  >= 0; i--){
            data[i] = h.removeMax();
        }
    }
}
