package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{
    int capacity();
    int fillCount();
    void enqueue(T x);
    T dequeue();
    T peek();
    default boolean isEmpty(){// is the buffer empty (fillCount equals zero)?
        return fillCount() == 0;
    }

    default boolean isFull(){// is the buffer full (fillCount is same as capacity)?
        return fillCount() == capacity();
    }
}
