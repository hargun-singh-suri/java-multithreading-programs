package demo7;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

    //Create a queue to hold the data say for size of 10
    //Create producer method which will add data to the queue - If space is not available it will wait for space to add data
    //Create consumer method which will remove the data from the queue if the data is there - If data is available it will remove else wait
    //Create thread to execute producer method
    //Create thread to execute consumer method

    //Queue used to store data, which take and put method are synchronized when multiple thread work in action
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String args[]) throws InterruptedException {

        Thread producerThread = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

    }

    public static void producer() throws InterruptedException {
        Random random = new Random();
        while (true){
            //Put will wait to insert value after some value is removed once queue has reached max value
            //random.nextInt(100) -- return Number between 0 to 10
            queue.put(random.nextInt(100));
        }
    }

    public static void consumer() throws InterruptedException {
        Random random = new Random();
        while (true){
            Thread.sleep(2000);
            while (true){
                //Used to simulate real world scenario of chance to come when need to perform take
                //random.nextInt(10) -- return Number between 0 to 10
                if(random.nextInt(10) == 0){
                    //Take will wait for put to add some value if queue is empty
                    Integer value = queue.take();
                    System.out.println("Value Taken "+ value +" ; Current Size of Queue "+queue.size());
                }
            }
        }
    }
}
