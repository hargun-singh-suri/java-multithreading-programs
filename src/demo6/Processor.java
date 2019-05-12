package demo6;

import java.util.concurrent.CountDownLatch;

public class Processor implements Runnable {

    //Create variable of CountDownLatch so every thread should be able to decrease the count
    private CountDownLatch latch ;

    //Passing instance of CountDownLatch in Constructor
    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started ... " +Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Current count : "+ latch.getCount()+" Decrement the Count " + Thread.currentThread().getName());
        //Decrement the count of latch by the Thread
        latch.countDown();
        System.out.println("Current count after decrement: "+ latch.getCount() + " " +Thread.currentThread().getName());
        System.out.println("End ... " +Thread.currentThread().getName());

    }
}
