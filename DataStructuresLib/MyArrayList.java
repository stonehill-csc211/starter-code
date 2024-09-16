package DataStructuresLib;

public class MyArrayList<T> extends MyList<T> {

    Object[] data;
    int end;

    public MyArrayList(){
        this.data = new Object[10];
        this.end = 0;
    }

    @Override
    public void add(T value) {
        this.data[end] = value;
        end++;
        if(end >= this.data.length){
            Object[] newData = new Object[this.data.length * 2];
            for(int i = 0; i < end; i++){
                newData[i] = this.data[i];
            }
            this.data = newData;
        }
    }

    @Override
    public void add(T value, int position) {
        // adds a new element in the middle of the array
        // loop over the values from position to end
        Object temp = this.data[position];
        Object temp2 = null;
        this.data[position] = value;
        for(int i = position+1; i < this.end; i++){
            // move each element to the next position
            temp2 = this.data[i];
            this.data[i] = temp;
            temp = temp2;
        }
        // if our list is now too large, resize
        this.add((T)temp);
    }

    @Override
    public T remove(int position) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int position) {
        if(position >= this.end){
            throw new ArrayIndexOutOfBoundsException(
                "Index" + position + 
                " is out of bounds for List with size " + this.end);
        }
        return (T)this.data[position];
    }

    @Override
    public void set(int position, T newValue) {
        if(position >= this.end){
            throw new ArrayIndexOutOfBoundsException(
                "Index " + position + 
                " is out of bounds for List with size " + this.end);
        }
        this.data[position] = newValue;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public boolean contains(T value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public void clear() {
        this.end = 0;
        this.data = new Object[10];
    }

    @SuppressWarnings("unchecked")
    public Object[] toArray(){
        // convert the ArrayList to an array
        Object[] retval = new Object[this.end];
        for(int i = 0; i < this.end; i++){
            retval[i] = (T)this.data[i];
        }
        return retval;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.end; i++){
            sb.append(Integer.toString((Integer)this.data[i]));
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MyArrayList<Integer> testList = new MyArrayList<Integer>();
        testList.add(1);
        testList.add(2);
        testList.add(5);
        testList.add(6);
        testList.add(10);

        testList.add(5,0);

        testList.set(0,10);
        System.out.println(testList.get(0) + " should be 10");
        System.out.println(testList);
        testList.set(100,0);
    }
    
}
