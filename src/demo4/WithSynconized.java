package demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WithSynconized {


    private Random random = new Random();
    private List<Integer> listOne = new ArrayList<>();
    private List<Integer> listTwo = new ArrayList<>();

    public synchronized void stageOne(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listOne.add(random.nextInt(100));
    }

    public synchronized void stageTwo(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listTwo.add(random.nextInt(100));
    }

    public void startProcess(){
        for (int i = 0 ; i <1000 ; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void launchProgram(){
        long start = System.currentTimeMillis();
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                startProcess();
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                startProcess();
            }
        });

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long end = System.currentTimeMillis();
        System.out.println("Total Time Taken : " + (long)(end - start));
        System.out.println("Size of List One : "+listOne.size()+" ; Size of List Two : " + listTwo.size());
    }
}

/*Total Time Taken : 5294
Size of List One : 2000 ; Size of List Two : 2000

This happened because we have 2 Thread which are adding values to the list so 2 process 1 process adding 1000 values
and other thread will start and it will also add 1000 values but the method are syncronised and 1 class can have 1 intrinsic lock
therefore wait time will Increase

*/