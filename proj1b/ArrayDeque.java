public class ArrayDeque<T> implements Deque<T>{
    private static final int INITIAL_LENGTH = 4;
    private static final int GROWTH_FACTOR = 2;
    private static final int DOWNSIZE_FACTOR = 4;

    private T[] arr;
    private int start;
    private int end;
    private int size;

    public ArrayDeque() {
        arr = (T[]) new Object[INITIAL_LENGTH];  // 修正类型转换错误
        start = 0;
        end = arr.length - 1;
        size = 0;
    }

//    // 拷贝构造函数
//    public ArrayDeque(ArrayDeque<T> other) {
//        arr = (T[]) new Object[other.arr.length];
//        System.arraycopy(other.arr, 0, arr, 0, other.size);
//        start = other.start;
//        end = other.end;
//        size = other.size;
//    }
    @Override
    public void addLast(T item) {
        if (size == arr.length) {
            changeCapacity(arr.length * GROWTH_FACTOR);
        }
        end = (end + 1) % arr.length;
        arr[end] = item;
        size++;
    }
    @Override
    public void addFirst(T item) {
        if (size == arr.length) {
            changeCapacity(arr.length * GROWTH_FACTOR);
        }
        start = (start - 1 + arr.length) % arr.length;
        arr[start] = item;
        size++;  // 忘记了size++
    }

    private void changeCapacity(int length) {
        T[] resized = (T[]) new Object[length];
        for (int i = 0; i < size; i++) {
            int src = (start + i) % arr.length;
            int dest = i;
            resized[dest] = arr[src];
        }
        arr = resized;
        start = 0;
        end = size - 1;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int index = (start + i) % arr.length;
            System.out.print(arr[index] + " ");
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        if (size < arr.length / DOWNSIZE_FACTOR && size > INITIAL_LENGTH) {
            changeCapacity(arr.length / GROWTH_FACTOR);
        }
        T item = arr[start];
        arr[start] = null;
        start = (start + 1) % arr.length;
        size--;
        return item;
    }
    @Override
    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        if (size < arr.length / DOWNSIZE_FACTOR && size > INITIAL_LENGTH) {
            changeCapacity(arr.length / GROWTH_FACTOR);
        }
        T item = arr[end];
        arr[end] = null;
        end = ((end - 1) + arr.length) % arr.length;
        size--;
        return item;
    }
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {  // 修正边界检查
            return null;
        }
        return arr[(start + index) % arr.length];
    }

    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }
}
