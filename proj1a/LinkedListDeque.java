public class LinkedListDeque<T>{
    private static class Node<T>{
        T value;
        Node<T> next;
        Node<T> prev;

        Node(){}

        Node(T value){
            this.value = value;
        }
    }

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node<T>();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T value){
        Node<T> node = new Node<T>(value);
        Node<T> next = sentinel.next;

        node.next = next;
        next.prev = node;
        sentinel.next = node;
        node.prev = sentinel;

        size++;
    }

    public void addLast(T value){
        Node<T> node = new Node<T>(value);
        Node<T> prev = sentinel.prev;

        node.prev = prev;
        prev.next = node;
        sentinel.prev = node;
        node.next = sentinel;

        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(Node<T> p = sentinel.next;p!=sentinel;p=p.next){
            System.out.println(p.value + " ");
        }
    }

    public T removeFirst(){
        return remove(sentinel.next);
    }

    public T removeLast(){
        return remove(sentinel.prev);
    }

    public T get(int index){
        if(index > size){
            return null;
        }
        int i = 0;
        Node<T> n = sentinel.next;

        while(true){
            if( i == index || n == sentinel){
                break;
            }
            n = n.next;
            i++;
        }

        return n.value;
    }

    public T getRecursive(int index){
        return getRecursive(index,sentinel.next);
    }

    private T remove(Node<T> node){
        if (node == sentinel){
            return null;
        }

        Node<T> next = node.next;
        Node<T> prev = node.prev;

        prev.next = next;
        next.prev = prev;
        size--;

        return node.value;
    }

    private T getRecursive(int distance,Node<T> n){
        if(distance == 0 || n == sentinel){
            return n.value;
        }
        return getRecursive(distance - 1,n.next);
    }
}
