package demo1.creatingthreadtwo;

import demo1.creatingthreadtwo.ThreadClassTwo;

public class Main {

    public static void main(String args[]){

        Thread obj1 = new Thread(new ThreadClassTwo());
        obj1.start();

        Thread obj2 = new Thread(new ThreadClassTwo());
        obj2.start();
    }
}
