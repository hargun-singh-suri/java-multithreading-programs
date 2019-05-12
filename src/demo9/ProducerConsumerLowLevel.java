package demo9;

import java.util.LinkedList;
import java.util.Random;

/*Using wait and notify, not using Thread safe class like array Blocking Queue*/
public class ProducerConsumerLowLevel {

    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void producer() throws InterruptedException {
        int value = 0;
        //Keep on adding value
        while (true){
            //Acquire Lock on object
            synchronized (lock) {
                //Check the size of list if full then make lock wait so that data can be available for addition
                while (LIMIT == list.size()) {
                    lock.wait();
                }
                //Line will only execute when there is no wait and place available in DS for addition
                list.add(value++);
                //Notify the Consumer so that if it is waiting it will be ready to remove the data
                lock.notify();
            }
        }
    }
    public void consumer() throws InterruptedException {

        //Keep removing the value
        while (true){
            synchronized (lock){
                //Check if the DS is empty put this on wait and let producer produce the value
                while (list.size() == 0){
                    lock.wait();
                }
                System.out.print("Size of DS " + list.size());
                //Remove the value when data is present there if no data put on wait
                Integer value = list.removeFirst();
                //Notify the producer that there is place to add value
                System.out.println(" ; Value removed " + value);
                lock.notify();
            }

            Thread.sleep(new Random().nextInt(1000));

        }
    }

}
