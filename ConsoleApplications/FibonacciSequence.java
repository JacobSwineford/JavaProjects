package Misc.ConsoleApplications;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Displays the numbers in the Fibonacci Sequence.
 * BigInteger is used for arbitrarily large numbers.
 *
 * @author Jacob Swineford
 */
public class FibonacciSequence {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the amount of numbers in the sequence: ");
        int numbers = in.nextInt();
        BigInteger[] num = new BigInteger[numbers];
        int count = 0;

        for (int i = 0; i < numbers; i++) {

            if (count % 10 == 0 && count != 0) {
                System.out.println();
            }

            if (i < 2) {
                BigInteger one = new BigInteger("1");
                num[i] = one;
                System.out.print(num[i] + " ");
            } else {
                num[i] = num[i - 2].add(num[i - 1]);
                System.out.print(num[i] + " ");
            }

            count++;
        }
    }
}
