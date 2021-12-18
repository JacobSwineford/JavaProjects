package Misc.ConsoleApplications.Stats;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Displays the number of pascal's triangle that
 * corresponds with the level and the location that
 * the user specifies.
 *
 * Pascal's triangle is used to calculate certain types
 * of probability, where you target a particular level
 * (where the first level is level 0), and a position
 * on the triangle (ignoring 1).
 *
 * There are special cases for the 0th, 1st, and 2nd
 * level. formats the number with commas if the number exceeds
 * 1000.
 *
 * This version finds the number using a mathematical formula
 * created to find values in pascal's triangle.
 *
 * @author Jacob Swineford
 */
public class PascalFinderV2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String endMessage;
        System.out.print("Enter the level: ");
        int lvl = in.nextInt();
        if (lvl == 0) {
            endMessage = "That level only contains 1." +
                    "\nThere are no combinations to be made.";
        } else if (lvl == 1) {
            endMessage = "that level only contains 1_1." +
                    "\nThere are no combinations to be made.";
        } else {
            System.out.print("Possible options for this level: 1 - " +
                    (lvl - 1) + "\nEnter an index that " +
                    "is within that range: ");
            int location = in.nextInt();

            if (location <= 0 || location >= lvl) {
                endMessage = "Out of range. Try again.";
            } else {
                String s = BigFormat(indexEquation(lvl, location).toString());
                endMessage = "Here is the number: " + s;
            }
        }
        System.out.println(endMessage);
    }

    /**
     * Calculates a factorial for the given number and
     * returns a BigInteger Object.
     */
    private static BigInteger factorial(int num) {
        BigInteger x = new BigInteger(Integer.toString(num));
        BigInteger current = new BigInteger(Integer.toString(num));

        while (num > 1) {
            current = current.multiply(x.subtract(BigInteger.ONE));
            x = x.subtract(BigInteger.ONE);
            num--;
        }
        return current;
    }

    /**
     * Calculates the desired index of pascal's triangle using
     * the mathematical method. the formula is as follows:
     *
     * (n choose k) = n! / k!(n - k)!
     *
     * Returns a BigInteger with the value.
     */
    private static BigInteger indexEquation(int n, int k) {
        return factorial(n).divide
                (factorial(k).multiply(factorial(n - k)));
    }

    /**
     * Takes a String and formats commas every three spaces.
     * if string.length() % 3 != 0, then special cases are implemented
     * to imitate US currency comma usage.
     *
     * I suppose I could have saved a lot of hassle by formatting
     * the string using getCurrencyInstance and getting rid of the
     * dollar sign, but whats the fun (and practice) gained in doing that?
     */
    private static String BigFormat(String big) {
        ArrayList<Character> arr = new ArrayList<>();
        int mod = big.length() % 3;
        int div = big.length() / 3;

        for (char cha : big.toCharArray()) {
            arr.add(cha);
        }

        int commas;
        if (mod == 0) {
            commas = div - 1;
        } else {
            commas = div;
        }

        int cur = 0;
        for (int i = 0; i < commas; i++) {

            if (i == 0 && mod != 0) {
                arr.add(mod, ',');
            } else if (i == 0) {
                cur += 3;
                arr.add(cur, ',');
            }

            if (mod == 1 && i == 1) {
                cur += 5;
                arr.add(cur, ',');
            } else if (mod == 2 && i == 1) {
                cur+= 6;
                arr.add(cur, ',');
            } else if (i >= 1) {
                cur += 4;
                arr.add(cur, ',');
            }
        }

        String str = "";
        for (char c : arr) {
            str += c;
        }

        return str;
    }

}
