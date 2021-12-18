package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * estimates the area under a curve using Riemann's Sum.
 * this is done by drawing equal rectangles under a curve
 * and finding the area of them. In general, the more
 * lower the difference between one point and another,
 * the more accurate the estimation is.
 *
 * Currently only does linear equations. Input must be
 * in the most simplified form with only one constant
 * on the right-hand side.
 *
 * There is no statement prompt for entering the equation,
 * left bound, right bound, and the number of rectangles.
 * but, there is an collected that lets the user confirm
 * their decisions to enter the values given into the
 * equation.
 *
 * @author Jacob Swineford
 */
public class RiemannsSum {

    private static Scanner in = new Scanner(System.in);
    private static String equation = in.nextLine();
    private static int leftBound = in.nextInt();
    private static int rightBound = in.nextInt();
    private static int rectangles = in.nextInt();

    public static void main(String[] args) {

        System.out.print("If the information you entered is" +
                " correct,\npress 1 and enter: ");
        int conCode = in.nextInt(); // 1 if correct, incorrect otherwise

    }
    /**
     * breaks the equation into an integer equation
     * that can have values plugged into easily.
     *
     * does not return the equation, rather gives the
     * option to plug numbers into the equation.
     */
    private static double equationPlugIn(int x) {
        String fullEquation = equation;
        return 2;
    }
    /**
     * Calculates and returns the value for delta, based on the bounds
     * and the number of rectangles intended to be used
     * for estimation.
     */
    private static double delta(int a, int b, int n) {
        return 2;
    }
    /**
     * Using delta and the equationPlugIn, constructs the
     * area of the first instance under the curve.
     */
    // NOTE: DELTA DOES NOT CHANGE, DO NOT CHANGE IT
    private static double estimateArea(double x) {
        return 2;
    }
}
