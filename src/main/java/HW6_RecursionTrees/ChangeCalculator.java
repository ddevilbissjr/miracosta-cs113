package HW6_RecursionTrees;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 * <p>
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 * <p>
 * An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to verify
 * that all given coin combinations are unique.
 */
public class ChangeCalculator {

    static ArrayList<Coin> combinations = new ArrayList<Coin>();

    public static void main (String[] args) {
        printCombinationsToFile(75);
    }

    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     * <p>
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) {
        return calculateChange(cents, new Coin());
    }

    public static int calculateChange(int cents, Coin coin) {
        if (cents == 0) {
            if (combinations.contains(coin)) {
                return 0;
            } else {
                combinations.add(coin);
                return 1;
            }
        }

        int num = 0;
        Coin newCoin;
        if (cents >= 1) {
            newCoin = new Coin(coin);
            newCoin.addPenny();
            num += calculateChange(cents - 1, newCoin);
        }
        if (cents % 5 == 0 && cents >= 5) {
            newCoin = new Coin(coin);
            newCoin.addNickle();
            num += calculateChange(cents - 5, newCoin);
        }
        if (cents % 10 == 0 && cents >= 10) {
            newCoin = new Coin(coin);
            newCoin.addDime();
            num += calculateChange(cents - 10, newCoin);
        }
        if (cents % 25 == 0 && cents >= 25) {
            newCoin = new Coin(coin);
            newCoin.addQuarter();
            num += calculateChange(cents - 25, newCoin);
        }
        int count = num;

        return count;
    }

    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     * <p>
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination on their own separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        calculateChange(cents);
        String fileName = "src/main/java/HW6_RecursionTrees/CoinCombinations.txt";
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);

            for (Coin coin : combinations) {
                fileWriter.write(coin.toString());
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}