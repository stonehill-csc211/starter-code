package DataStructuresLib;

import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> implements Iterable{

    T data;
    BinarySearchTree<T> left;
    BinarySearchTree<T> right;
    BinarySearchTree<T> parent;

    private class TreeIterator implements Iterator{
        LinkedQueue<T> visitOrder;
        private TreeIterator(BinarySearchTree t){
            this.visitOrder = new LinkedQueue<T>();
            this.add(t);
        }

        private void add(BinarySearchTree t){
            if(t.left != null) add(t.left);
            this.visitOrder.enqueue((T) t.data);
            if(t.right != null) add(t.right);
        }

        @Override
        public boolean hasNext() {
            return this.visitOrder.size() > 0;
        }

        public T peek(){
            return this.visitOrder.getFront();
        }

        @Override
        public Object next() {
            return this.visitOrder.dequeue();
        }
    }

    public BinarySearchTree(){
        this.data = null;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public BinarySearchTree(T data){
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public void add(T newData){
        if(this.data == null){
            this.data = newData;
            return;
        }
        if(newData.compareTo(this.data) > 0) {
            if(this.right != null) this.right.add(newData);
            else {
                this.right = new BinarySearchTree<T>(newData);
                this.right.parent = this;
            }
        }
        else if(newData.compareTo(this.data) < 0) {
            if(this.left != null) this.left.add(newData);
            else{
                this.left = new BinarySearchTree<T>(newData);
                this.left.parent = this;
            }
        }
    }

    public boolean contains(T query){
        if(this.data == null) return false;
        if(query.equals(this.data)) return true;
        if(query.compareTo(this.data) > 0){
            // right
            if(this.right == null) return false;
            else return this.right.contains(query);
        } else{
            // left
            if(this.left == null) return false;
            else return this.left.contains(query);
        }
    }

    private BinarySearchTree<T> getNode(T query){
        if(this.data == null) return null;
        if(query.equals(this.data)) return this;
        if(query.compareTo(this.data) > 0){
            // right
            if(this.right == null) return null;
            else return this.right.getNode(query);
        } else{
            // left
            if(this.left == null) return null;
            else return this.left.getNode(query);
        }
    }

    private boolean removeAndShift(){
        if(this.left != null){
            // if we have a left subtree
            this.data = this.left.data;
            boolean result = this.left.removeAndShift();
            if(!result) this.left = null;
            return true;
        } else if(this.right != null){
            // if we have a right subtree
            this.data = this.right.data;
            boolean result = this.right.removeAndShift();
            if(!result) this.right = null;
            return true;
        } else{
            // if we are a leaf
            return false;
        }
    }

    public void remove(T data){
        BinarySearchTree toRemove = getNode(data);
        toRemove.removeAndShift();
    }

    public String toString(){
        if(this.left == null && this.right == null){
            return this.data.toString();
        } else {
            String left = "-";
            if(this.left != null) left = this.left.toString();
            String right = "-";
            if(this.right != null) right = this.right.toString();
            return "(" + this.data.toString() + " " + left + " " + right + ")";
        }
    }

    public Iterator iterator(){
        return new TreeIterator(this);
    }

    public BinarySearchTree<T> intersection(BinarySearchTree<T> other){
        /*
         * Finds the intersection between this tree and other
         * Returns a new BinarySearchTree containing the intersection
         */
        BinarySearchTree<T> newTree = new BinarySearchTree<T>();
        TreeIterator thisIterator = (TreeIterator)this.iterator();
        TreeIterator otherIterator = (TreeIterator)other.iterator();
        while(thisIterator.hasNext() && otherIterator.hasNext()){
            if(thisIterator.peek().compareTo(otherIterator.peek()) < 0){
                thisIterator.next();
            } else if (thisIterator.peek().compareTo(otherIterator.peek()) > 0){
                otherIterator.next();
            } else {
                newTree.add((T)thisIterator.next());
                otherIterator.next();
            }
        }
        return newTree;
    }

    public static void main(String[] args){
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        int[] arr = new int[]{10,5,17,19,15,13,8,3,9};
        for(int a : arr){
            tree.add(a);
        }
        /*
        System.out.println(tree.contains(8));
        System.out.println(tree.contains(16));
        tree.remove(17);
        System.out.println(tree.contains(17));
        System.out.println(tree.contains(13));
        System.out.println(tree);
        */
        BinarySearchTree<Integer> tree2 = new BinarySearchTree<Integer>();
        int[] arr2 = new int[]{5,9,13,16,18};
        for(int a : arr2){
            tree2.add(a);
        }

        BinarySearchTree<Integer> intersection = tree.intersection(tree2);
        System.out.println(intersection);


    }
}