package demo13;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class HandlingReturn {


    public static void main(String args[]) {

        System.out.println("Returning value example Started...");

        ExecutorService executorOne = Executors.newCachedThreadPool();

        //Getting value back from running Thread

        //Callable is similar to Runnable but it is able to return the value it has method call similar to run
        //Future will handle the response return by Callable
        //Parameter of Callable and Future must be the same


        Future<Integer> futureOne = executorOne.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                Random random = new Random();
                int duration = random.nextInt(4000);

                System.out.println("Starting.........");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished..........");
                return duration;
            }
        });

        executorOne.shutdown();


        //Value that is returned from callable can be obtained from future reference using get()
        //Get will wait for thread on future is completed
        try {
            System.out.println("Total Time of Execution lapsed : " + futureOne.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Returning value example completed successfully");
        System.out.println("***********************************************");
        System.out.println("Exception throwing example started...");
        ExecutorService executorTwo = Executors.newCachedThreadPool();

        Future<Integer> futureTwo = executorTwo.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                Random random = new Random();
                int duration = random.nextInt(4000);

                if (duration < 2000)
                {
                    throw  new IOException("Sleeping for Too Long ... .. .");
                }

                System.out.println("Starting.........");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished..........");
                return duration;
            }
        });

        try {
            System.out.println("Total Time of Execution lapsed : " + futureTwo.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e);
        }


        System.out.println("Exception throwing example Completed...");


        /*Using methods of future where result is not returned*/


/* ? --> Wild Card
   Integer will be replaced by Void
   Return will be null

        Future<?> futureTwo = executorTwo.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {

                Random random = new Random();
                int duration = random.nextInt(4000);

                if (duration < 2000)
                {
                    throw  new IOException("Sleeping for Too Long ... .. .");
                }

                System.out.println("Starting.........");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished..........");
                return null;
            }
        });
*/

    }
}
