package Misc.ConsoleApplications;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class LongestRun {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Enter the length of the run: ");
        int length = in.nextInt();
        double testCases = 1000000;
        double max = 0;
        for (int i = 0; i < testCases; i++) {
            max += longestRun(randomCoinFlips(length, rand));
        }
        System.out.println("Longest Run (Probable): " + (int) (max / testCases));
    }

    // returns an array of random coin flips, of the specified
    // length
    private static boolean[] randomCoinFlips(int length, Random r) {
        boolean[] arr = new boolean[length];
        for (int i = 0; i < length; i++) {
            int t = r.nextInt(2);
            arr[i] = (t==1);
        }
        return arr;
    }

    // returns the longest run of true / false. This method does not discriminate
    // between the two.
    private static int longestRun(boolean[] cf) {
        int max = 1;
        int len = 1;
        boolean testerVal = cf[0];
        for (int i = 1; i < cf.length; i++) {
            if (cf[i] == testerVal) {len++;}
            else {len = 1; testerVal = cf[i];}
            if (len > max) max = len;
        }
        return max;
    }
}
