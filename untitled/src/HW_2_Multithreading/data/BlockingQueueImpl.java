package HW_2_Multithreading.data;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Class {@code BlockingQueueImpl} is the implementation of the Blocking Queue using wait-notify mechanism.
 * This class provides thread-safe operations poll and offer.
 *
 * @author Taras Murzenkov
 * @version 1.0.1
 * @date 15.07.15
 */
public class BlockingQueueImpl<E> implements BlockingQueue<E> {

    protected volatile Queue<E> storage;
    protected volatile int capacity;

    /**
     * Creates a blocking queue with the fixed capacity.
     *
     * @param capacity - number of elements/capacity of the queue.
     */
    public BlockingQueueImpl(int capacity) {

        this.capacity = capacity;
        this.storage = new PriorityQueue<E>(capacity);
    }

    /**
     * Adds element to the queue. This method waits if the queue is full.
     * If it is not full then the notifyAll() method invokes.
     * After this, the counter will be incremented and the element will be added to a queue.
     *
     * @param element - generic type element to be added to a queue
     */
    @Override
    public synchronized void offer(E element) {

        while (isFull()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(element);
        notifyAll();
    }

    /**
     * Returns the last element in a queue. If queue is empty, the thread  will wait when an
     * element will be added. Else, method will call notifyAll(), take the last element and
     * decrement the counter of an elements.
     *
     * @return polledElement.
     */
    @Override
    public synchronized E poll() {
        E polledElement;
        while (isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        polledElement = storage.poll();
        notifyAll();
        return polledElement;
    }

    public synchronized boolean isFull() {
        return storage.size() == capacity;
    }

    public synchronized boolean isEmpty() {
        return storage.size() == 0;
    }
}
