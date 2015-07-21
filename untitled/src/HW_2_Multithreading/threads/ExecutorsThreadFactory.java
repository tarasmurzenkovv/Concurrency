package HW_2_Multithreading.threads;

import HW_2_Multithreading.producer_replacer.Producer;
import HW_2_Multithreading.producer_replacer.Replacer;
import HW_2_Multithreading.Utilities;

import java.util.concurrent.*;

/**
 * Created by terance on 21.07.15.
 */
public class ExecutorsThreadFactory implements ProducerReplacerThreadFactory {

    @Override
    public void spawnDaemonThread(String nameOfProducerThread,
                                  String nameOfReplacerThread,
                                  HW_2_Multithreading.data.BlockingQueue<String> source,
                                  HW_2_Multithreading.data.BlockingQueue<String> destination) {

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

        ExecutorService producers = Executors.newFixedThreadPool(
                Utilities.NUMBER_OF_PRODUCER_THREADS,
                runnable -> Utilities.getConfiguredThread(runnable));

        producers.execute(new Producer(source,nameOfProducerThread));
        producers.shutdown();

        ExecutorService replacers = Executors.newFixedThreadPool(
                Utilities.NUMBER_OF_REPLACER_THREADS,
                runnable -> Utilities.getConfiguredThread(runnable));
        replacers.execute(new Replacer(source,destination,nameOfReplacerThread));
        replacers.shutdown();

    }
}
