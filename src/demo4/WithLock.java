package demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WithLock {

    private  Object lock1 = new Object();
    private  Object lock2 = new Object();
    private Random random = new Random();
    private List<Integer> listOne = new ArrayList<>();
    private List<Integer> listTwo = new ArrayList<>();

    public void stageOne(){

        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listOne.add(random.nextInt(100));
        }
    }

    public void stageTwo(){
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listTwo.add(random.nextInt(100));
        }
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
/*Now we have provided lock to each thread so they will work independent of other
*
* Total Time Taken : 2644
Size of List One : 2000 ; Size of List Two : 2000
*
*/