package HW_2_Multithreading.threads;

import HW_2_Multithreading.Utilities;
import HW_2_Multithreading.data.BlockingQueue;

public abstract class ProducerReplacerThreadFactory {
    String nameOfProducerThread;
    String nameOfReplacerThread;
    BlockingQueue<String> source;
    BlockingQueue<String> destination;

    public abstract void spawnDaemonThreads(String nameOfProducerThread,
                                            String nameOfReplacerThread,
                                            BlockingQueue<String> source,
                                            BlockingQueue<String> destination);

    protected Thread getConfiguredDaemonThread(Runnable runnable) {
        Thread configuredThread = new Thread(runnable);

        configuredThread.setDaemon(true);
        return configuredThread;
    }

    protected void checkParameters(){
        if (source == null)
            throw new NullPointerException("The source of the messages cannot be null. " +
                    "Check, if you have initialised it");

        if (destination == null)
            throw new NullPointerException("The destination of the messages cannot be null. " +
                    "Check, if you have initialised it");

        if ((nameOfProducerThread == null) || (nameOfReplacerThread == null))
            throw new IllegalArgumentException("Name of a thread cannot be null.");

        if ((Utilities.NUMBER_OF_PRODUCER_THREADS <= 0) || (Utilities.NUMBER_OF_REPLACER_THREADS < 0))
            throw new IllegalArgumentException("Number of working threads must be greater than zero.");

        if (Utilities.NUMBER_OF_MESSAGES_TO_READ <= 0)
            throw new IllegalArgumentException("Number of messages to read must be greater than zero.");
    }
}

