package HW_2_Multithreading;

import HW_2_Multithreading.data.BlockingQueue;
import HW_2_Multithreading.data.BlockingQueueWithLocks;
import HW_2_Multithreading.threads.ExecutorsThreadFactory;
import HW_2_Multithreading.threads.ProducerReplacerThreadFactory;

public class Main {
    public static void main(String[] args) {

        Utilities.NUMBER_OF_PRODUCER_THREADS = 5;
        Utilities.NUMBER_OF_REPLACER_THREADS = 2;
        Utilities.NUMBER_OF_MESSAGES_TO_READ = 99;
        Utilities.STORAGE_CAPACITY = 100;

        BlockingQueue<String> source = new BlockingQueueWithLocks<>(Utilities.STORAGE_CAPACITY);
        BlockingQueue<String> destination = new BlockingQueueWithLocks<>(Utilities.STORAGE_CAPACITY);

        // Create, configure and start threads.
        ProducerReplacerThreadFactory producerReplacerThreadFactory = new ExecutorsThreadFactory();

        
        // ProducerReplacerThreadFactory producerReplacerThreadFactory = new WaitNotifyThreadFactory();
        //Utilities.spawnDaemonThreads("Producer", "Replacer", source, destination);

        producerReplacerThreadFactory.spawnDaemonThread("Producer", "Replacer", source,destination);

        for (int i = 0; i < Utilities.NUMBER_OF_MESSAGES_TO_READ; i++) {
            System.out.println(destination.poll());
        }
    }
}
