package Misc.ConsoleApplications;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class bins {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of bins: ");
        Random r = new Random();
        int numBins = in.nextInt();
        double times = 1000000;
        double val = 0;
        for (int i = 0; i < times; i++) {
            // run the helper method and log it's value
            val += test(numBins, r);
        }
        System.out.println("The monte carlo simulation comes out to: " + val / times);
    }

    private static int test(int numBins, Random r) {
        int[] bins = new int[numBins]; // initialized all to 0
        int counter = 0;
        while (!full(bins)) {
            int ran = r.nextInt(numBins);
            bins[ran]++;
            counter++;
        }
        return counter;
    }

    private static boolean full(int[] arr) {
        for (int i : arr) {
            if (i == 0) return false;
        }
        return true;
    }
}
