package demo13;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WithoutReturn {

    public static void main(String args[]){
        ExecutorService executor = Executors.newCachedThreadPool();

        //This will never return back any value here if we want value to be returned from the run()

        executor.submit(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int duration = random.nextInt(4000);

                System.out.println("Starting.........");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished..........");

            }
        });

        executor.shutdown();



    }
}
