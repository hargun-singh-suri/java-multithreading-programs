package demo1.creatingthreadone;

import demo1.creatingthreadone.ThreadClassOne;
import demo1.creatingthreadone.WithoutExtendingThread;

public class Main {

    public static void main(String args[]){

        ThreadClassOne obj1 = new ThreadClassOne();
        obj1.start();

        ThreadClassOne obj2 = new ThreadClassOne();
        obj2.start();


        //Wait for thread processing to complete
        try {
            obj1.join();
            obj2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread execution completed");


        WithoutExtendingThread obj3 = new WithoutExtendingThread();
        obj3.run();

        WithoutExtendingThread obj4 = new WithoutExtendingThread();
        obj4.run();
    }
}
