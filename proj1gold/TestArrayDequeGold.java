import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Random;

public class TestArrayDequeGold {

    @Test
    public void randomTestArrayDeque(){
        //测试次数
        int NUM_TESTS = 8;
        //两个数据类型
        //测试的数据类型
        StudentArrayDeque<Integer> dequeTest = new StudentArrayDeque<>();
        //参考的数据类型(已完成)
        ArrayDequeSolution<Integer> dequeRef = new ArrayDequeSolution<>();

        //提示信息
        String message = "";
        String methodCallString = "";

        //给参考实现和测试实现添加数字
        for (int i = 0; i < NUM_TESTS; i++) {
            Integer randInt = StdRandom.uniform(-10000,10000);

            if(i % 2 == 0){
                //更新日志
                methodCallString = "addFirst(" + randInt + ")" + "\n";
                message += methodCallString;
                //i为偶数,使用 addFirst
                dequeTest.addFirst(randInt);
                dequeRef.addFirst(randInt);

            }else {
                methodCallString = "addLast(" + randInt + ")" + "\n";
                message += methodCallString;
                //i为奇数,使用addLast
                dequeTest.addLast(randInt);
                dequeRef.addLast(randInt);
            }
        }

        //测试参考实现和测试实现是否相同
        while(!dequeRef.isEmpty()){
            double randDouble = StdRandom.uniform();

            if(randDouble < 0.5){//是removeFirst
                //更新日志
                methodCallString = "removeFirst()" + "\n";
                message += methodCallString;

                //assertEqual
                assertEquals(message,dequeRef.removeFirst(),dequeTest.removeFirst());
            } else {//是removeLast
                //更新日志
                methodCallString = "removeLast()" + "\n";
                message += methodCallString;

                //assertEqual
                assertEquals(message,dequeRef.removeLast(),dequeTest.removeLast());
            }
        }
    }
}
