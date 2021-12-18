package Misc.ConsoleApplications;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Brute force password cracker, based characters
 *
 * @author Jacob Swineford
 */
public class BruteForce {

    private static String values = "abcdefghijklmnopqrstupwxyz";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Length and Base: ");
        int length = in.nextInt();
        int base = in.nextInt();
        if (base >= values.length())
            System.out.println("base not supported: " + base);
        else
        outputAll(length, base);
    }

    private static String baseRepOf2(BigInteger b, int base) {
        StringBuilder sb = new StringBuilder();
        BigInteger numTemp = b;
        BigInteger ba = new BigInteger(Integer.toString(base));
        BigInteger i;

        while (!numTemp.equals(new BigInteger("0"))) {
            i = numTemp.mod(ba);
            sb.append(values.toCharArray()[i.intValueExact()]);
            numTemp = numTemp.divide(ba);
        }
        return reversed(sb.toString());
    }

    private static String reversed(String str) {
        StringBuilder sb = new StringBuilder();
        char[] arr = str.toCharArray();
        for (int i = str.length() - 1; i > -1; i--) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    private static void outputAll(int length, int base) {
        for (int i = 1; i <= length; i++) {
            outputAllForLength(i, base);
        }
    }

    private static void outputAllForLength(int length, int base)  {
        BigInteger cur = new BigInteger("0");
        BigInteger ba = new BigInteger(Integer.toString(base));
        BigInteger numLoops = ba.pow(length);
        while (!cur.equals(numLoops)) {
            String str = baseRepOf2(cur, base);
            str = extendTo(str, length);
            if (cur.mod(new BigInteger("15")).equals(new BigInteger("0"))) System.out.println();
            System.out.print(str + " ");
            cur = cur.add(new BigInteger("1"));
        }
    }

    private static String extendTo(String str, int length) {
        int loops = length - str.length();
        String valo = values.substring(0,1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < loops; i++) {
            sb.append(valo);
        }
        return sb.toString() + str;
    }
}
