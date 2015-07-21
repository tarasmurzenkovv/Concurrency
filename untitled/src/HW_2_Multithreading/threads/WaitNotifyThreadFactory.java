package HW_2_Multithreading.threads;

import HW_2_Multithreading.producer_replacer.Producer;
import HW_2_Multithreading.producer_replacer.Replacer;
import HW_2_Multithreading.Utilities;
import HW_2_Multithreading.data.BlockingQueue;

/**
 * Created by terance on 21.07.15.
 */
public class WaitNotifyThreadFactory implements ProducerReplacerThreadFactory {
    @Override
    public void spawnDaemonThread(String nameOfProducerThread,
                                  String nameOfReplacerThread,
                                  BlockingQueue<String> source,
                                  BlockingQueue<String> destination) {

        // Check if the input data has been correctly supplied. Otherwise, throw a proper exception.
        if (source == null)
            throw new NullPointerException("The source of the messages cannot be null. " +
                    "Check, if you have initialised it");

        if ((nameOfProducerThread == null) || (nameOfReplacerThread == null))
            throw new IllegalArgumentException("Name of a thread cannot be null.");

        if ((Utilities.NUMBER_OF_PRODUCER_THREADS <= 0) || (Utilities.NUMBER_OF_REPLACER_THREADS < 0))
            throw new IllegalArgumentException("Number of working threads must be greater than zero.");

        if (Utilities.NUMBER_OF_MESSAGES_TO_READ <= 0)
            throw new IllegalArgumentException("Number of messages to read must be greater than zero.");

        for (int i = 1; i < Utilities.NUMBER_OF_PRODUCER_THREADS + 1; i++) {
            String name = nameOfProducerThread + i;
            Producer producer = new Producer(source, name);
            Thread threadProducer = Utilities.getConfiguredThread(producer);
            threadProducer.start();
        }
        for (int i = 1; i < Utilities.NUMBER_OF_REPLACER_THREADS + 1; i++) {
            String name = nameOfReplacerThread + i;
            Replacer replacer = new Replacer(source, destination, name);
            Thread threadReplacer = Utilities.getConfiguredThread(replacer);
            threadReplacer.start();
        }

    }
}
