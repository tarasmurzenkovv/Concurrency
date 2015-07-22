package HW_2_Multithreading.threads;

import HW_2_Multithreading.producer_replacer.Producer;
import HW_2_Multithreading.producer_replacer.Replacer;
import HW_2_Multithreading.Utilities;
import HW_2_Multithreading.data.BlockingQueue;

public class WaitNotifyThreadFactory extends ProducerReplacerThreadFactory {
    @Override
    public void spawnDaemonThreads(String nameOfProducerThread,
                                   String nameOfReplacerThread,
                                   BlockingQueue<String> source,
                                   BlockingQueue<String> destination) {

        try{
            this.checkParameters();
        }catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }

        for (int i = 1; i < Utilities.NUMBER_OF_PRODUCER_THREADS + 1; i++) {
            String name = nameOfProducerThread + i;
            Producer producer = new Producer(source, name);
            Thread threadProducer = this.getConfiguredDaemonThread(producer);
            threadProducer.start();
        }
        for (int i = 1; i < Utilities.NUMBER_OF_REPLACER_THREADS + 1; i++) {
            String name = nameOfReplacerThread + i;
            Replacer replacer = new Replacer(source, destination, name);
            Thread threadReplacer = this.getConfiguredDaemonThread(replacer);
            threadReplacer.start();
        }

    }
}
