package Misc.ConsoleApplications;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class fib {

    private static BigInteger[] fib;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the index: ");
        int length = in.nextInt();
        try {
            fib = new BigInteger[length];
            fib[0] = new BigInteger("0");
            fib[1] = new BigInteger("1");
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        // init array, return value is for recursion
        f(length - 1);
        print();
    }

    // assumes that the first two positions have been filled.
    private static BigInteger f(int i) {
        if (fib[i] == null) {
            fib[i] = f(i-2).add(f(i-1));
        }
        return fib[i];
    }

    private static void print() {
        int counter = 0;
        int longest = fib[fib.length - 1].toString().length();
        char iter = ' ';
        String str;
        for (BigInteger b : fib) {
            if (counter % 4 == 0) System.out.println();
            str = extendWith(b.toString(), iter, longest);
            System.out.print(str + " ");
            counter++;
        }
    }

    private static String extendWith(String str, char c, int longest) {
        int loops  = longest - str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < loops; i++) sb.append(c);
        return sb.append(str).toString();
    }
}
