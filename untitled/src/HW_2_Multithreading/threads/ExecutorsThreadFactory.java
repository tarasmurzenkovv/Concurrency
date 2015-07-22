package HW_2_Multithreading.threads;

import HW_2_Multithreading.producer_replacer.Producer;
import HW_2_Multithreading.producer_replacer.Replacer;
import HW_2_Multithreading.Utilities;

import java.util.concurrent.*;

public class ExecutorsThreadFactory extends ProducerReplacerThreadFactory {

    @Override
    public void spawnDaemonThreads(String nameOfProducerThread,
                                   String nameOfReplacerThread,
                                   HW_2_Multithreading.data.BlockingQueue<String> source,
                                   HW_2_Multithreading.data.BlockingQueue<String> destination) {

        try {
            this.checkParameters();

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }

        ExecutorService producers = Executors.newFixedThreadPool(
                Utilities.NUMBER_OF_PRODUCER_THREADS,
                runnable -> this.getConfiguredDaemonThread(runnable));

        producers.execute(new Producer(source, nameOfProducerThread));
        producers.shutdown();

        ExecutorService replacers = Executors.newFixedThreadPool(
                Utilities.NUMBER_OF_REPLACER_THREADS,
                runnable -> this.getConfiguredDaemonThread(runnable));
        replacers.execute(new Replacer(source, destination, nameOfReplacerThread));
        replacers.shutdown();

    }
}
