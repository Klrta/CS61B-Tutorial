public class AList {
    private int size;
    private int[] items;
    public AList(){
        items = new int[100];
        size = 0;
    }

    public void addLast(int x){
        if(size == items.length){
            resize(size + 1);
        }
        items[size] = x;
        size += 1;
    }

    public int getLast(){
        return items[size-1];
    }

    public int get(int i){
        return items[i];
    }

    public int size(){
        return size;
    }

    public int removeLast(){
        int returnItem = items[size - 1];
        items[size - 1] = 0;
        size -= 1;
        return returnItem;
    }

    public void resize(int capacity){
        int[] a = new int[capacity];
        System.arraycopy(items,0,a,0,size);
        items = a;
    }
}
