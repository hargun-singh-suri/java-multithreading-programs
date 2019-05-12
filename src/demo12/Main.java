package demo12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        //Creating multiple threads
        ExecutorService executorOne = Executors.newCachedThreadPool();

        //Using for loop to create bunch of threads
        for (int i = 0;i<200 ; i++){
            //Submit will create new thread every time
            executorOne.submit(new Runnable() {
                @Override
                public void run() {
                    WithoutSemaphore.getInstance().connect();
                }
            });
        }

        //Terminate the Managerial Thread after all thread execution is finished
        executorOne.shutdown();

        //Wait until they are finished for certain time
        executorOne.awaitTermination(1, TimeUnit.DAYS);

        Thread.sleep(3000);


        /*---------------------------Limiting no of connections on a given time---------------------------*/

        //Creating multiple threads
        ExecutorService executorTwo = Executors.newCachedThreadPool();

        //Using for loop to create bunch of threads
        for (int i = 0;i<200 ; i++){
            //Submit will create new thread every time
            executorTwo.submit(new Runnable() {
                @Override
                public void run() {

                        WithSemaphore.getInstance().connect();

                }
            });
        }

        //Terminate the Managerial Thread after all thread execution is finished
        executorTwo.shutdown();

        //Wait until they are finished for certain time
        executorTwo.awaitTermination(1, TimeUnit.DAYS);





    }
}
