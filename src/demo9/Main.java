package demo9;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        ProducerConsumerLowLevel processor = new ProducerConsumerLowLevel();
        //Producer method of class ProducerConsumerLowLevel will run in producer Thread
        Thread producer = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //Consumer method of class ProducerConsumerLowLevel will run in consumer Thread
        Thread consumer = new Thread(() -> {
            try {
                processor.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
