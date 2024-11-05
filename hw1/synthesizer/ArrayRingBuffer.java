package synthesizer;// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        if(capacity < 1){
            throw new RuntimeException("Attempt to initialize Array Ring Buffer to size < 1.");
        }
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
        this.rb = (T[])new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if(this.isFull()){
            throw new RuntimeException("Ring Buffer overflow.");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;
    }


    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if(this.isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T item = rb[first];
        rb[first] = null;
        first = (first + 1) % capacity;
        fillCount -= 1;
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if(this.isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorAB<T>(this.rb);
    }

    private class IteratorAB<T> implements Iterator<T>{
        private final T[] items;
        private int index;

        private IteratorAB(T[] items){
            this.items = items;
            this.index = 0;
        }
        @Override
        public boolean hasNext() {
            return index < fillCount();
        }
        @Override
        public T next() {
            if(!hasNext()){
                throw new RuntimeException("Attempt to iterate beyond the end of Array Buffer.");
            }
            T item = items[index];
            index += 1;
            return item;
        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
