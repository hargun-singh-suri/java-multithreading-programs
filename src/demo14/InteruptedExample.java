package demo14;

import java.util.Random;
import java.util.concurrent.*;

public class InteruptedExample {

    public static void main(String args[]) throws InterruptedException {
        testSingleThread();
        testThreadPool();
    }

    private static void testThreadPool() throws InterruptedException {
        System.out.println("Starting !!");
        //Work with Thread pool

        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> fu =  executor.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Random number = new Random();

                for (int i = 0 ; i<1E8 ; i ++)
                {
                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("Interupted !!!");
                        break;
                    }

                    Math.sin(number.nextDouble());

                }
                return null;
            }
        });



        //2 ways code can be interrupted
        //1 : executor.ShutdownNow()
        //2 : fu.cancel(True)

        executor.shutdown();

        Thread.sleep(500);
        executor.shutdownNow();
        //fu.cancel(true);
        executor.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("Finished");

    }


    public static void testSingleThread() throws InterruptedException {
        //Work with Thread object
        System.out.println("Starting !!");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Random number = new Random();

                for (int i = 0 ; i<1E8 ; i ++)
                {
                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("Interupted !!!");
                        break;
                    }

                    Math.sin(number.nextDouble());

                }
            }
        });

        t.start();

        Thread.sleep(500);

        t.interrupt();

        t.join();
        System.out.println("End !!");

    }
}
