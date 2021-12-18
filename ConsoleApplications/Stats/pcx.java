package Misc.ConsoleApplications.Stats;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class pcx {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("enter N and n: ");
        int N = in.nextInt();
        int n = in.nextInt();
        System.out.println(pcx(N, n));
    }

    private static double pcx(int n, int x) {
        if (x == 0 || n == x)
            return 1;
        return pcx(n - 1, x - 1) + pcx(n - 1, x);
    }
}
