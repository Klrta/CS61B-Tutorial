import edu.princeton.cs.algs4.In;

import java.util.TreeMap;

public class LinkedListDeque<T> {


    public class IntNode{
        public T item;
        public IntNode prev;
        public IntNode next;
        public IntNode(T i,IntNode p,IntNode n){
            item = i;
            prev = p;
            next = n;
        }
    }

    private int size;
    private IntNode sentinel;

    public LinkedListDeque(){
        sentinel = new IntNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    //初始化双端链表
    public LinkedListDeque(T item){
        sentinel = new IntNode(null,null,null);
        IntNode node = new IntNode(item,sentinel,sentinel);
        sentinel.next = node;
        sentinel.prev = node;
        size = 1;
    }

    public void addFirst(T item){
        IntNode node = new IntNode(item,sentinel,sentinel.next);
        sentinel.next = node;
        sentinel.prev.prev = node;
        size += 1;
    }

    public void addLast(T item){
        IntNode node = new IntNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = node;  // 更新原最后一个节点的 next
        sentinel.prev = node;  // 将新节点设为最后一个节点
        size += 1;  // 增加 size
    }

    public T getLast(){
        return sentinel.prev.item;
    }

    public void removeLast(){
        if (isEmpty()) {
            return;  // 如果链表为空，什么也不做
        }
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
    }

    public void removeFirst() {
        if (isEmpty()){
            return;
        }
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
    }
    public int size(){
        return size;
    }

    public boolean isEmpty() {
        if (size == 0){
            return true;
        }else {
            return false;
        }
    }

    public void printDeque() {
        IntNode current = sentinel.next;
        while (current != sentinel){
            System.out.print(current.item + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }


}
