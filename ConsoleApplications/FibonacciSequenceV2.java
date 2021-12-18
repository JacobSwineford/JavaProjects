package Misc.ConsoleApplications;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Displays the numbers in the Fibonacci Sequence.
 * BigInteger is used for arbitrarily large numbers.
 *
 * This is V2 of the program. This version uses an ArrayList
 * to StatisticalSort and output values.
 *
 * @author Jacob Swineford
 */
public class FibonacciSequenceV2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the amount of numbers in the sequence: ");
        int numbers = in.nextInt();
        ArrayList<BigInteger> big = new ArrayList<>();
        BigInteger one = new BigInteger("1");

        int c = 0;
        for (int i = 0; i < numbers; i++) {
             if (i == 0 || i == 1) {
                 big.add(one);
                 System.out.print(big.get(i) + " ");
                 c++;
             } else {
                 big.add(big.get(i - 2).add(big.get(i - 1)));
                 System.out.print(big.get(i) + " ");
                 c++;
             }

             if (c % 10 == 0) {
                 System.out.println();
             }
        }
    }
}
