package Lab2;

import java.util.Scanner;

public class SquareOfTwoShortened {
    /*
    Write a method that finds out if a number is a power of two.
    Create a tester to demonstrate it works.
    Please refer to the slides on how to work with JUnit.
    */
    public static void main (String[] args) {
        System.out.println("Enter the number you'd like to test is of the power of two.");
        Scanner input = new Scanner(System.in);
        System.out.println("This returned " + (Math.sqrt(Double.parseDouble(input.next())) % 2 == 0 ? true : false) + ".");
        input.close();
    }
}