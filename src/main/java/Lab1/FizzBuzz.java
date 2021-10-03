package Lab1;

public class FizzBuzz {
    /*
    Write a program that prints the numbers from 1 to 100,
    but for multiples of 3 print "Fizz" instead of the number and for multiples of 5 print "Buzz".
    For numbers that are multiples of both three and five print "Lab1.FizzBuzz".
    */
    public static void main (String[] args) {
        int currentNum = 1;
        do {
            if(currentNum % 3 == 0) {
                System.out.print("Fizz");
            }

            if(currentNum % 5 == 0) {
                System.out.print("Buzz");
            }

            if(currentNum % 3 != 0 && currentNum % 5 != 0) {
                System.out.print(currentNum);
            }

            System.out.print("\n");
            currentNum++;
        } while (currentNum <= 100);
    }
}
