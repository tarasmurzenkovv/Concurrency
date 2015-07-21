package HW_2_Multithreading;

/**
 * Class that contains number of static utility methods.
 * It is designed to
 * - hold the set of public constants;
 * - transform message;
 * - generate message;
 * - create/configure/start threads;
 */

public class Utilities {

    public static int NUMBER_OF_PRODUCER_THREADS;
    public static int NUMBER_OF_REPLACER_THREADS;
    public static int NUMBER_OF_MESSAGES_TO_READ;
    public static int STORAGE_CAPACITY;

    /**
     * Creates a string in form of "Thread #threadName transferred message *"
     *
     * @param threadName
     * @param messageToTransform
     * @return
     */

    public static String transformMessage(String threadName, String messageToTransform) {
        String[] splitString = messageToTransform.split(" ");
        String newMessage = "Thread #" + threadName + " transferred message " + splitString[splitString.length - 1];
        return newMessage;
    }

    public static String generateMessage(String threadName) {
        return "Thread #" + threadName + " generated message #" + threadName;
    }

    /**
     * Configures and starts threads.
     *
     * @param nameOfProducerThread - the root of the thread-producer name, is used to generate messages.
     * @param nameOfReplacerThread - the root of the thread-replacer name, is used to generate messages.
     * @param source               - source of the messages, BlockingQueue.
     * @param destination          - final place where messages will appear/be transferred.
     */
    public static void spawnDaemonThreads(String nameOfProducerThread,
                                          String nameOfReplacerThread,
                                          HW_2_Multithreading.data.BlockingQueue<String> source,
                                          HW_2_Multithreading.data.BlockingQueue<String> destination) {


    }

    /**
     * Returns a created instance of Thread for a specific Runnable instance.
     *
     * @param runnable   - instance that implements Runnable interface.
     * @param threadName - name of thread.
     * @return Thread    - configured instance of Thread.
     */
    public static Thread getConfiguredThread(Runnable runnable) {
        Thread configuredThread = new Thread(runnable);

        configuredThread.setDaemon(true);
        return configuredThread;
    }

    public static void createExecutors(String nameOfProducerThread,
                                       String nameOfReplacerThread,
                                       HW_2_Multithreading.data.BlockingQueue<String> source,
                                       HW_2_Multithreading.data.BlockingQueue<String> destination) {


    }

}
