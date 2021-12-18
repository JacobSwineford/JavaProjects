package Misc.ConsoleApplications.Stats;

import java.util.Scanner;

/**
 * Formula for stats:
 * (n C x)(ps)^x(1-ps)^n - x
 *
 * @author Jacob Swineford
 */
public class BinomialDistribution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter n, x, and the probability of success: ");
        int n = in.nextInt();
        int x = in.nextInt();
        double ps = in.nextDouble();
        System.out.print("Binomial Distribution: " + bDOf(n, x, ps));
    }

    private static double pcx(int n, int x) {
        if (x == 0 || n == x)
            return 1;
        return pcx(n - 1, x - 1) + pcx(n - 1, x);
    }

    private static double bDOf(int n, int x, double ps) {
        return pcx(n, x) * Math.pow(ps, x) * Math.pow(1 - ps, n - x);
    }
}
