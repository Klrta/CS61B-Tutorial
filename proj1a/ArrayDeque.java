public class ArrayDeque<T> {
    private static final int INITIAL_LENGTH = 4;
    private static final int GROWTH_FACTOR = 2;
    private static final int DOWNSIZE_FACTOR = 4;

    private T[] arr;
    private int start;
    private int end;
    private int size;


    public ArrayDeque(){
        arr = (T[]) new Object(INITIAL_LENGTH);
        start = 0;
        end = arr.length - 1;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other){;
        System.arraycopy(items,0,other,0,size);
        items = other;
    }

    public void addLast(T item){
        if (size == arr.length){
            changeCapacity(arr.length * GROWTH_FACTOR);
        }
        end = (end + 1) % arr.length;
        arr[end] = item;
        size++;
    }

    public void addFirst(T item){
        if (size == arr.length){
            changeCapacity(arr.length * GROWTH_FACTOR);
        }
        start = (start - 1 + arr.length) % arr.length;
        arr[start] = item;
        size++;
    }

    private void changeCapacity(int length) {
        T[] resized = (T[]) new Object(length);
        for (int i = 0; i < size; i++) {
            int src = (start + i) % arr.length;
            int dest = i;
            resized[dest] = arr[src];
        }
        arr = resized;
        start = 0;
        end = size - 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for (int i = 0; i < size; i++) {
            int index = (start + i) % arr.length;
            System.out.println(arr[index] + " ");
        }
    }

    public T removeFirst(){
        if(size <= 0){
            return null;
        }
        if(size < arr.length /DOWNSIZE_FACTOR && size > INITIAL_LENGTH){
            changeCapacity(arr.length / GROWTH_FACTOR);
        }
        T item = arr[start];
        arr[start] = null;
        start = (start + 1) % arr.length;
        size--;
        return item;
    }

    public T removeLast(){
        if(size <= 0){
            return null;
        }
        if(size < arr.length / DOWNSIZE_FACTOR && size > INITIAL_LENGTH){
            changeCapacity(arr.length / GROWTH_FACTOR);
        }
        T item = arr[end];
        arr[end] = null;
        end = ((end - 1) + arr.length) % arr.length;
        size--;
        return item;
    }

    public T get(int index){
        if(index >= arr.length){
            return null;
        }
        return arr[(start+index)% arr.length];
    }


}
