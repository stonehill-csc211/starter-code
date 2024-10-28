package DataStructuresLib;

public class MyTreeMap<K extends Comparable<K>,V> extends MyMap<K,V> {

    private class Record implements Comparable<Record>{
        K key;
        V value;
        private Record(K key, V value){
            this.key = key;
            this.value = value;
        }

        private Record(K key){
            this.key = key;
            this.value = null;
        }

        @Override
        public int compareTo(Record other){
            return this.key.compareTo(other.key);
        }


        public boolean equals(Record other){
            return this.key.equals(other.key);
        }
    }

    public MyTreeMap(){
        tree = new BinarySearchTree<>();
    }

    BinarySearchTree<Record> tree;
    @Override
    public void add(K key, V value) {
        Record r = new Record(key, value);
        tree.add(r);
    }
    @Override
    public boolean contains(K key) {
        Record r = new Record(key);
        return tree.contains(r);
    }

    @Override
    public V remove(K key) {
        Record r = new Record(key);
        Record removed = tree.remove(r);
        return removed.value;
    }

    @Override
    public V get(K key) {
        Record query = new Record(key);
        Record retrieved = tree.get(query);
        return retrieved.value;
    }

    @Override
    public V set(K key, V newValue) {
        Record query = new Record(key);
        Record retrieved = tree.get(query);
        V oldValue = retrieved.value;
        retrieved.value = newValue;
        return oldValue;
    }
    
}
