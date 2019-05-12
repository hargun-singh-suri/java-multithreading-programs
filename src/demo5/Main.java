package demo5;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String args[]){
        //Create Thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //Register Tasks - Class on executor
        for (int i = 1 ; i <= 5; i++){
            executor.submit(new ThreadPoolExample(i));
        }

        //Inform executor to shutdown when Tasks are Completed and Do not accept other Tasks (Command to close executor)
        executor.shutdown();

        System.out.println("All Tasks Submitted");

        //Wait for all the task to get completed
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All Tasks completed !!!");
    }
}
