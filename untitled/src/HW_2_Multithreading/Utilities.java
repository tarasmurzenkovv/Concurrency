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
}
