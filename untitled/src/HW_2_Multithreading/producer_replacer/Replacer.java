package HW_2_Multithreading.producer_replacer;

import HW_2_Multithreading.Utilities;
import HW_2_Multithreading.data.BlockingQueue;

/**
 * This class encapsulates "take - modify - insert" logic into an instance of a Runnable
 * A thread terminates its execution if destination is full or source is empty.
 *
 * @author Taras Murzenkov
 * @version 1.0.1
 * @date 15.07.15
 */
public class Replacer implements Runnable {

    volatile BlockingQueue<String> source; //Source of the messages, where they will be taken from, null is not allowed.
    volatile BlockingQueue<String> destination; //Destination of the message, where they will be inserted, null is not allowed.
    String threadName; //Name of a thread that performs replace operation.
    volatile int numberOfReadMessages;

    /**
     * Takes a pre initialised entities of the BlockingQueue to implement lose coupling.
     *
     * @param source      - represents a BlockingQueue where messages are stored.
     * @param destination - represents a BlockingQueue where messages must be put.
     * @param threadName  - name of thread that performs "take - modify - insert" step.
     */
    public Replacer(BlockingQueue<String> source,
                    BlockingQueue<String> destination,
                    String threadName) {

        this.source = source;
        this.destination = destination;
        this.threadName = threadName;
    }

    /**
     * Encapsulates "take - modify - insert" logic.
     * <p>
     * Takes a message from a source by calling its method poll.
     * Increments the counter of the messages.
     * Transforms the taken message by calling the Utilities static method transformMessage
     * Inserts the transformed message into the destination queue.
     * <p>
     * This method is not synchronised because the poll and offer are synchronised methods.
     * The variable {@code numberOfReadMessages} is at the unique thread memory thus it might not be synchronised.
     */
    private synchronized void replace() {
        String polledMessage;
        String transformedMessage;

        polledMessage = source.poll();
        numberOfReadMessages++;
        transformedMessage = Utilities.transformMessage(threadName+numberOfReadMessages, polledMessage);
        destination.offer(transformedMessage);
    }

    @Override
    public void run() {
        while (true) {
            replace();
        }
    }
}
