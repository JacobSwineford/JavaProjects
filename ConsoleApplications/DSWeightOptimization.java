package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * Weight optimizer for Dark Souls 3. Enter a weight via numerator and a denominator
 * and get an optimized fraction that is closer to .7.
 *
 * @author Jacob Swineford
 */
public class DSWeightOptimization {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter weight numerator and denominator: ");
        double n = in.nextDouble();
        double d = in.nextDouble();
        System.out.println("\nCurrent weight ratio: " + n + " / " + d + " = "
                + (n / d) * 100 + "%\n");

        double dOpt;
        if ((n / d) > .7) {
            dOpt = minDenominator(n , d);
        } else {
            dOpt = maxDenominator(n , d);
        }

        double percent = (n / dOpt) * 100;
        System.out.printf("Optimized weight ratio: %.2f / %.2f = %.2f%%", n, dOpt, percent);
    }

    private static double minDenominator(double n, double d) {
        while ((n / d) > .7) {
            d++;
        }
        return d;
    }

    private static double maxDenominator(double n, double d) {
        while ((n / d) < .7) {
            d--;
        }
        return d + 1; // (n/d) is higher than 70, need (n/d) to be less than 70
    }
}
