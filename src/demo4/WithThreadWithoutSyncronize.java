package demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WithThreadWithoutSyncronize {

    private Random random = new Random();
    private List<Integer> listOne = new ArrayList<>();
    private List<Integer> listTwo = new ArrayList<>();

    public void stageOne(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listOne.add(random.nextInt(100));
    }

    public void stageTwo(){
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
        Thread threadOne = new Thread(() -> startProcess());

        Thread threadTwo = new Thread(() -> startProcess());

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

/*Total Time Taken : 2679
Size of List One : 1998 ; Size of List Two : 1999

Multiple Thread working on same method which will cause method to execute multiple times or in a wrong manner
The 2 thread are not in sync they keep on adding the data to the list which will cause data limit of array list goes beyond 1000
it should be 2000 as 2 thread will run and should run in good time
*/