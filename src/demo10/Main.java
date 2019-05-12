package demo10;

import demo9.ProducerConsumerLowLevel;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        ReentrantExample processor = new ReentrantExample();
        //firstThread method of class ReentrantExample will run in producer Thread
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //SecondThread method of class ReentrantExample will run in consumer Thread
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.SecondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        processor.finished();
    }
}
