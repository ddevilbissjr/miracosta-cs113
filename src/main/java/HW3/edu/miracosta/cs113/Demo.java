package HW3.edu.miracosta.cs113;

import java.util.LinkedList;
import java.util.Scanner;

public class Demo {

    private static Scanner keyboard;

    /*public static void main (String[] args) {
        Polynomial first = new Polynomial();
        Polynomial second = new Polynomial();

        first.addTerm(new Term(4, 10));
        first.addTerm(new Term(3, 2));
        first.addTerm(new Term(5, 1));
        first.addTerm(new Term(6, 0));
        first.addTerm(new Term(13, 26));

        System.out.println(first);

        second.addTerm(new Term(6, 2));
        second.addTerm(new Term(2, 1));
        second.addTerm(new Term(3, 0));

        System.out.println(second);

        first.add(second);
    }*/

    public static void main(String[] args) {
        Polynomial firstPoly = new Polynomial(), secondPoly = new Polynomial();
        boolean finished = false;
        keyboard = new Scanner(System.in);

        System.out.println("Welcome to the poly program");

        while (!finished) {
            System.out.println("Please make a selection");
            System.out.println("1: Edit first polynomial");
            System.out.println("2: Edit second polynomial");
            System.out.println("3: Add your polynomials together");
            System.out.println("0: Exit program");

            switch (Integer.parseInt(keyboard.next())) {
                case 1:
                    editPolynomial(firstPoly);
                    break;
                case 2:
                    editPolynomial(secondPoly);
                    break;
                case 3:
                    System.out.print("Your polynomials added together equal ");
                    firstPoly.add(secondPoly);
                    finished = true;
                    break;
                case 0:
                    System.out.println("Goodbye.");
                    finished = true;
                    break;
                default:
                    System.out.println("Invalid command. Try again.");
            }
            break;
        }
        System.exit(0);
    }

    public static Polynomial editPolynomial (Polynomial orig) {
        boolean isDone = false;

        while (!isDone) {
            System.out.println("1: Add term");
            System.out.println("2: Clear polynomial");
            System.out.println("0: Main menu");

            switch(Integer.parseInt(keyboard.next())) {
                case 1:
                    System.out.println("Format: [coeff]x^[exp]\n" +
                                        "If no [exp], use 1.\n" +
                                        "If no [exp] and x, use 0.");
                    LinkedList<Integer> nums = convertToPoly(keyboard.next());
                    orig.addTerm(new Term(nums.get(0), nums.get(1)));
                    break;
                case 2:
                    orig.clear();
                    break;
                case 0:
                    isDone = true;
                    break;
                default:
                    System.out.println("Unknown command. Try again.");
                    break;
            }

            if(isDone) {
                break;
            }
        }
        return orig;
    }

    public static LinkedList<Integer> convertToPoly (String str) {
        LinkedList<Integer> variables = new LinkedList<>();
        variables.add(Integer.parseInt(str.substring(0, str.indexOf("x"))));
        variables.add(Integer.parseInt(str.substring(str.indexOf("^")+1)));
        return variables;
    }
}
