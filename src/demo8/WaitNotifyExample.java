package demo8;

import java.util.Scanner;

public class WaitNotifyExample {

    public void waitExample() throws InterruptedException {

        //Acquire lock on current Object
        synchronized (this){
            System.out.println("Lock acquired by "+Thread.currentThread().getName());
            System.out.println("Relinquish the lock on "+ this.getClass().getSimpleName()+" go to wait");
            //Wait will relinquish the lock on the object and the Next lines will be not executed until lock is gained by notify from other block
            wait();
            //Not execute after wait() is recahed wait till lock is acquired
            System.out.println(Thread.currentThread().getName() + " Got resumed now");
            System.out.println(Thread.currentThread().getName() + " Completed");
        }
    }

    public void resumeExample() throws InterruptedException {
        //start only when lock is available after the wait() is called
        Thread.sleep(3000);

        synchronized (this){
            System.out.println("Lock acquired by "+Thread.currentThread().getName());
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press Enter Key to resume your program from here !!!");
            scanner.nextLine();
            System.out.println("Enter Key pressed");
            //This will notify the block which relinquished the lock that lock is available you can access now
            notify();
            //Executed after notify(), once this block is completed only after this notified block will work
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " Completed");
        }
    }
}
