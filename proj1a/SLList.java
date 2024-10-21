public class SLList {
    public class IntNode{
        public int item;
        public IntNode next;
        public IntNode(int i,IntNode n){
            item = i;
            next = n;
        }
    }

    public int size;
    public IntNode sentinel;

    public SLList(){
        sentinel = new IntNode(63,null);
        size = 0;
    }
    //初始化单链表
    public SLList(int x){
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x,null);
        size = 1;
    }
    //在头部添加一个元素
    public void addFirst(int x){
        sentinel.next = new IntNode(x,sentinel.next);
        size += 1;
    }
    //获取链表第一个元素
    public int getFirst(){
        return sentinel.next.item;
    }
    //在尾部添加一个元素
    public void addLast(int x){
        IntNode p = sentinel;
        while(p.next != null){
            p = p.next;
        }
        p.next = new IntNode(x,null);
        size += 1;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        SLList L = new SLList();
        L.addLast(20);
        System.out.println(L.size());
    }

}
