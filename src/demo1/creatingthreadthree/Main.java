package demo1.creatingthreadthree;

import demo1.creatingthreadtwo.ThreadClassTwo;

public class Main {

    public static void main(String args[]){

        //Peace of code which will be run by Thread
        Runnable runnable = () -> {
            for(int i = 0 ; i < 10 ; i++){
                System.out.println("Thread Name  - " + Thread.currentThread().getName() + " Count " + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

/*        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 10 ; i++){
                    System.out.println("Thread Name  - " + Thread.currentThread().getName() + " Count " + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };*/

        Thread obj1 = new Thread(runnable);
        obj1.start();

        Thread obj2 = new Thread(runnable);
        obj2.start();

    }
}
