package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue("1");
        arb.enqueue("2");
        arb.enqueue("3");
        arb.enqueue("4"); // 1 2 3 4
        //test of dequeue
        assertEquals("1",arb.dequeue());//2 3 4
        assertEquals("2",arb.dequeue());//3 4
        //test of peek
        assertEquals("3",arb.peek());//3 4
        //test of capacity
        assertEquals("capacity",10,arb.capacity());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
