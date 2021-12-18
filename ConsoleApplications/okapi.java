package Misc.ConsoleApplications;

import java.util.Random;

/**
 * @author Jacob Swineford
 */
public class okapi {

    public static void main(String[] args) {
        Random rand = new Random();
        double signum = 1_000_000;
        double pay = 0;
        for (int i = 0; i < signum; i++) {
            pay += payout(rand);
        }
        System.out.printf("The estimated payout is: $%.2f", pay / signum);
    }

    private static int payout(Random r) {
        int d1 = r.nextInt(6) + 1;
        int d2 = r.nextInt(6) + 1;
        int d3 = r.nextInt(6) + 1;
        if (allSame(d1, d2, d3)) return d1 + d2 + d3;
        if (d1 == d2) return d1 + d2;
        if (d2 == d3) return d2 + d3;
        if (d1 == d3) return d1 + d3;
        return 0;
    }

    private static boolean allSame(int q, int w, int e) {
        return q == w && w == e;
    }
}
