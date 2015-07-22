package HW_2_Multithreading.producer_replacer;

import HW_2_Multithreading.Utilities;
import HW_2_Multithreading.data_structure.BlockingQueue;

/**
 * This class encapsulates insertion logic into an instance of a Runnable
 * A thread terminates its execution if source is full.
 *
 * @author Taras Murzenkov
 * @version 1.0.1
 * @date 15.07.15
 */
public class Producer implements Runnable {

    volatile BlockingQueue<String> source;
    String threadName;
    int numberOfReadMessages;

    public Producer(BlockingQueue<String> source, String threadName) {
        this.source = source;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (true) {
            numberOfReadMessages++;
            source.offer(Utilities.generateMessage(threadName+numberOfReadMessages));
        }
    }
}
