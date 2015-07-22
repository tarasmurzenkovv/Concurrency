package HW_2_Multithreading.data_structure;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Blocking queue based on java Lock object.
 * Poll and offer are using Lock object instead of wait-notify. 
 */
public class BlockingQueueWithLocks<E> extends BlockingQueueImpl<E> {

    private Lock lock = new ReentrantLock(false);// look at another options to use.
    private Condition empty = lock.newCondition();
    private Condition full = lock.newCondition();

    /**
     * Creates a blocking queue with the fixed capacity.
     *
     * @param capacity - number of elements/capacity of the queue.
     */
    public BlockingQueueWithLocks(int capacity) {
        super(capacity);
    }

    @Override
    public void offer(E e){
        lock.lock();
        try{
            while (isFull()){
                full.await();
            }
            super.storage.add(e);
            empty.signalAll();

        }catch (InterruptedException ex){
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public E poll(){
        E polledElement = null;
        lock.lock();
        try {
            while (isEmpty()){
                empty.await();
            }
            polledElement = storage.poll();
            full.signalAll();

        }catch (InterruptedException ex){
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
        return polledElement;
    }
}
