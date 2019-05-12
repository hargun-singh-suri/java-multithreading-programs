package demo2;

import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        UsingVolatileKeyword ob1 = new UsingVolatileKeyword();
        ob1.start();

        System.out.println("Enter to stop !!!");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ob1.shutDown();
    }
}
