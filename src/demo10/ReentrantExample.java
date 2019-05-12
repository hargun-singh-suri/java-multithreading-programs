package demo10;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    //Shared variable
    private int count = 0;
    //Lock variable
    private Lock lock = new ReentrantLock();
    //Variable that will have await and signal method
    private Condition cond = lock.newCondition();

    //Method which will increment the count but we will not add synchronized keyword
    //Each time it is called it will increment the count value to 1000
    //This will work as expected  == > public synchronized void increment() but we will use lock object

    public  void increment()
    {
        for(int i = 0 ; i<10000;i++){
            count++;
        }
    }

    public void firstThread() throws InterruptedException{
        lock.lock();

        //This will work similar to wait() relinquish it's control to other thread and wait for signal()
        System.out.println(Thread.currentThread().getName() + " Waiting ......");
        cond.await();
        System.out.println(Thread.currentThread().getName() + " Thread is waken up and resume the process");
        try {
            increment();
        }
        finally {
            lock.unlock();
        }
    }

    public void SecondThread()throws InterruptedException{
        //This is done therefore Thread one can kick off initially and go to wait
        Thread.sleep(3000);

        lock.lock();
        System.out.println(Thread.currentThread().getName() + " Started....");
        System.out.println("Enter key to proceed");
        new Scanner(System.in).nextLine();
        System.out.println("Got Returned key");

        //This will signal other thread to take the control which went into await state in our case first thread will be waken up
        System.out.println(Thread.currentThread().getName() + " Signal the previous Thread");
        cond.signal();

        //In case of exception the lock is released in finally block
        try {
            increment();
        }
        //After signal this must be called else lock will never be released
        finally {
            lock.unlock();
        }
    }

    public void finished(){
        System.out.println("Value of count " + count);
    }
}
