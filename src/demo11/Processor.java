package demo11;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {
    private Account acc1 = new Account();
    private Account acc2 = new Account();

    //Create 2 ReentrantLock Lock --  Way - 2 To maintain synchronization
    private Lock lockOne = new ReentrantLock();
    private Lock lockTwo = new ReentrantLock();

    public void acquireLock(Lock lock1, Lock lock2) throws InterruptedException{

        while (true){
            //Locks  acquired
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                //This part will actually acquire the lock and if lock acquired if acquired and false if not
                gotFirstLock = lock1.tryLock();
                gotSecondLock = lock2.tryLock();
            }finally {

                if (gotFirstLock && gotSecondLock){
                    return;
                }

                //As we know we need 2 lock to work if lock acquired on 1 that will be not use give other thread a chance
                // So simply unlock what is acquired return only in case when you have both the locks
                if (gotFirstLock){
                    lock1.unlock();
                }

                if (gotSecondLock){
                    lock2.unlock();
                }
            }

            //Locks not acquired
            Thread.sleep(1000);

        }

    }


/*    Way - 1 To maintain synchronization
    public synchronized void transfer(Account source,Account destination, int amount){
        Account.transfer(source,destination,amount);
    }*/


    public void threadOneProcessor() throws InterruptedException {
        //Perform Random transfers
        Random amount = new Random();


        for(int i = 0 ; i < 100 ; i ++){
            //Way - 2 To maintain synchronization - ReentrantLock
//            lockOne.lock();
//            lockTwo.lock();

            acquireLock(lockOne,lockTwo);
            try {
                Account.transfer(acc1,acc2,amount.nextInt(100));
            }
            finally {
                lockOne.unlock();
                lockTwo.unlock();
            }

        }

/*        Way - 1 To maintain synchronization
        transfer(acc1,acc2,amount.nextInt(100));*/
    }

    public void threadTwoProcessor() throws InterruptedException {
        //Perform Random transfers
        Random amount = new Random();


        for(int i = 0 ; i < 100 ; i ++){
            // Way - 2 To maintain synchronization - ReentrantLock
           /* lockOne.lock();
            lockTwo.lock();*/

            acquireLock(lockTwo,lockOne);
            try {
                Account.transfer(acc2,acc1,amount.nextInt(100));
            }
            finally {
                lockOne.unlock();
                lockTwo.unlock();
            }
        }

/*        Way - 1 To maintain synchronization
        transfer(acc2,acc1,amount.nextInt(100));*/
    }

    public void finished(){
        System.out.println("Account 1 available balance "+ acc1.getBalance());
        System.out.println("Account 2 available balance "+ acc2.getBalance());
        System.out.println("Total available balance "+ (acc1.getBalance()+ acc2.getBalance()));
    }
}


/*
* Case 1 :
*
        for(int i = 0 ; i < 100 ; i ++){
            //Way - 2 To maintain synchronization - ReentrantLock
            lockOne.lock();
            lockTwo.lock();
            try {
                Account.transfer(acc1,acc2,amount.nextInt(100));
            }
            finally {
                lockOne.unlock();
                lockTwo.unlock();
            }


for(int i = 0 ; i < 100 ; i ++){
            // Way - 2 To maintain synchronization - ReentrantLock
            lockOne.lock();
            lockTwo.lock();
            try {
                Account.transfer(acc2,acc1,amount.nextInt(100));
            }
            finally {
                lockOne.unlock();
                lockTwo.unlock();
            }
        }
*
*Case 2 : There exist a case when it is given to you 2 account and you need to transfer money from 1 to other
* in above case we know which method is performing transfer when
*
*So you will try to associate lock with 1 account and other lock with second account
*
*
* If you transfer from account 2 to ACCOUNT 1 your code will change like below
*
*
* for(int i = 0 ; i < 100 ; i ++){
            // Way - 2 To maintain synchronization - ReentrantLock
           ** lockTwo.lock();
            lockOne.lock();

            try {
                Account.transfer(acc2,acc1,amount.nextInt(100));
            }
            finally {
                lockOne.unlock();
                lockTwo.unlock();
            }
        }

        Lock is performed in Different order will result in Dead Lock

        First Thread will acquire lock on LockOne
        Second Thread will acquire lock on LockTwo

        and Both of them will wait for each other to get release eventually move to Dead lock.

  case 3 : Handling Dead Lock

  We will have trlock of reentrant class which will return true if It has lock

  In try block
    we will try to get the status
    if we have both lock available we will return from while loop
    if we have any 1 lock available then we will unlock it

    If we have only 1 lock available we will loop again to check for other lock to get free up and get exit from
    while loop and perform lock on to it
*
* */