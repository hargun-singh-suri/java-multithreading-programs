package demo6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String args[]){
        /*Creating Count down latch, which is Thread safe and passed to constructor of processor class
          the instance op CountDownLatch will be utilized by all the thread and it will be decremented by 1
           after calling countdown at end of the process*/
        CountDownLatch latch = new CountDownLatch(3);

        /*Creating Thread pool of 3 Thread*/
        ExecutorService executor = Executors.newFixedThreadPool(3);

        /*Registering process which will be handled by the Threads*/
        for (int i = 0 ; i<3 ; i ++) {
            executor.submit(new Processor(latch));
        }

        //Latch will wait until value of count is reached to 0
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed");
    }
}
