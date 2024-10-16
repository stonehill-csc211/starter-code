package DataStructuresLib;

public class BinarySearchTree<T extends Comparable<T>>{

    T data;
    BinarySearchTree<T> left;
    BinarySearchTree<T> right;
    BinarySearchTree<T> parent;

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

    public static void main(String[] args){
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        int[] arr = new int[]{10,5,17,19,15,13,8,3,9};
        for(int a : arr){
            tree.add(a);
        }
        System.out.println(tree.contains(8));
        System.out.println(tree.contains(16));
        tree.remove(17);
        System.out.println(tree.contains(17));
        System.out.println(tree.contains(13));
        System.out.println(tree);
    }
}