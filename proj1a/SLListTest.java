public class SLListTest {
    public static void main(String[] args) {
        checkSLList();
    }

    private static void checkSLList() {
        SLList list = new SLList(10);
        checkEquals(list.getFirst(),10,"check initial element");
        checkEquals(list.size(),1,"check initial size");

        // 测试 addFirst 方法
        list.addFirst(5);
        checkEquals(list.getFirst(),5, "check first element after addFirst");
        checkEquals(list.size(), 2, "check size after addFirst");

        // 测试 addLast 方法
        list.addLast(20);
        checkEquals(list.size(), 3, "check size after addLast");

        // 手动遍历链表，验证最后一个元素
        list.addLast(30);
        int lastElement = getLastElement(list);
        checkEquals(lastElement, 30, "check last element after addLast");
    }

    // 获取链表的最后一个元素（辅助方法）
    private static int getLastElement(SLList list) {
        SLList.IntNode current = list.sentinel.next;
        while (current.next != null){
            current = current.next;
        }
        return current.item;
    }
    // 断言方法，用来比较实际值与期望值
    private static void checkEquals(int actual, int expected, String message){
        if (actual != expected){
            System.out.printf("Test failed: " + message + ". Expected: " + expected + ", but got: " + actual);
        }else{
            System.out.println("Test passed: " + message);
        }
    }
}
