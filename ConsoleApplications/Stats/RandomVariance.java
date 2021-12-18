package Misc.ConsoleApplications.Stats;

import java.util.Scanner;

/**
 * BS formula for stats:
 * R^2 = S[(x - u)^2 * p(x)]
 *
 * @author Jacob Swineford
 */
public class RandomVariance {

    private static double u;
    private static double p2;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter list of x: ");
        double[] xArr = convertFrom(in.nextLine().split(" "));
        System.out.print("Enter corresponding list of p(x): ");
        double[] pxArr = convertFrom(in.nextLine().split(" "));
        if (xArr.length != pxArr.length) {
            System.out.println("incorrect correspondence");
        } else {
            u = uEqu(xArr, pxArr);
            p2 = rEquP2(xArr, pxArr);
            System.out.println("P = " + u);
            System.out.println("R^2 = " + p2);
            System.out.println("R = " + Math.sqrt(p2));
        }
    }

    private static double[] convertFrom(String[] arr) {
        double[] r = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            r[i] = Double.parseDouble(arr[i]);
        }
        return r;
    }

    private static double uEqu(double[] x, double[] p) {
        double r = 0;
        for (int i = 0; i < x.length; i++) {
            r += x[i] * p[i];
        }
        return r;
    }

    /**
     * Assumes that u has a calculated numeric value
     * R^2 = S[(x - u)^2 * p(x)]
     */
    private static double rEquP2(double[] x, double[] p) {
        double r = 0;
        for (int i = 0; i < x.length; i++) {
            r += (Math.pow((x[i] - u), 2) * p[i]);
        }
        return r;
    }


}
