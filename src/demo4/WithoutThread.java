package demo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WithoutThread {

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
        startProcess();
        long end = System.currentTimeMillis();
        System.out.println("Total Time Taken : " + (long)(end - start));
        System.out.println("Size of List One : "+listOne.size()+" ; Size of List Two : " + listTwo.size());
    }
}

/*Total Time Taken : 2656
Size of List One : 1000 ; Size of List Two : 1000*/