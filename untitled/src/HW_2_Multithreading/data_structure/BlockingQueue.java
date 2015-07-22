package HW_2_Multithreading.data_structure;

/**
 * Interface that provides methods of the Blocking queue
 */
public interface BlockingQueue<E> {

    //Inserts a generic type element into a queue.
    void offer(E element);

    //Takes a generic type element from a queue.
    E poll();

    boolean isFull();

    boolean isEmpty();
}
