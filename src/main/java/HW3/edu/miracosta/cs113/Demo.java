package HW3.edu.miracosta.cs113;

import java.util.LinkedList;
import java.util.Scanner;

public class Demo {

    private static Scanner keyboard = new Scanner(System.in);

    public static void main (String[] args) {
        Polynomial first = new Polynomial();
        Polynomial second = new Polynomial();

        first.addTerm(new Term(3, 2));
        first.addTerm(new Term(5, 1));
        first.addTerm(new Term(6, 0));

        System.out.println(first);

        second.addTerm(new Term(6, 2));
        second.addTerm(new Term(2, 1));
        second.addTerm(new Term(3, 0));

        System.out.println(second);

        first.add(second);
    }

    //private static Scanner keyboard;

    /*public static void main(String[] args) {
        Polynomial firstPoly = null, secondPoly = null;
        int userSelection = 0;
        keyboard = new Scanner(System.in);

        System.out.println("Welcome to the poly program");

        while (userSelection != -1) {
            System.out.println("Please make a selection");
            System.out.println("1: Edit first polynomial");
            System.out.println("2: Edit second polynomial");
            System.out.println("3: Add your polynomials together");
            System.out.println("-1: Exit program");

            userSelection = Integer.parseInt(keyboard.next());

            if (userSelection == 1) {
                editPolynomial(firstPoly);
            } else if (userSelection == 2) {
                editPolynomial(secondPoly);
            } else if (userSelection == 3) {
                System.out.println("Your polynomials added together equal ");
            } else if (userSelection == -1) {
                System.out.println("Goodbye.");
                System.exit(0);
            }
        }
    } */

    public static Polynomial editPolynomial (Polynomial orig) {
        boolean isDone = false;

        while (!isDone) {
            System.out.println("1: Create polynomial");
            System.out.println("2: Add term");
            System.out.println("3: Clear polynomial");
            System.out.println("-1: Exit program");

            switch(keyboard.nextInt()) {
                case 1:
                    orig = new Polynomial();
                    break;
                case 2:
                    System.out.println("Format: [coeff]x^[exp]\n" +
                                        "If no [exp], use 1.\n" +
                                        "If no [exp] and x, use 0.");
                    LinkedList<Integer> nums = convertToPoly(keyboard.next());
                    orig.addTerm(new Term(nums.get(0), nums.get(1)));
                    break;
                case 3:
                    orig.clear();
                    break;
                case -1:
                    isDone = true;
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
