package HW_2_Multithreading.threads;

import HW_2_Multithreading.data.BlockingQueue;

/**
 * Created by terance on 21.07.15.
 */
public interface ProducerReplacerThreadFactory {

    void spawnDaemonThread(String nameOfProducerThread,
                           String nameOfReplacerThread,
                           BlockingQueue<String> source,
                           BlockingQueue<String> destination);
}

