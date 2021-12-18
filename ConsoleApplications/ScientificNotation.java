package Misc.ConsoleApplications;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class ScientificNotation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("number: ");
        String num = in.next();
        System.out.println(scientificNotation(num));
    }

    private static String scientificNotation(String num) {
        String r = "";
        int pwr;

        if (num.indexOf('.') != -1) { // there exists a decimal point
            pwr = num.indexOf('.') - 1;
        } else {
            pwr = num.length() - 1;
        }

        BigDecimal coEfficient = new BigDecimal(num);
        coEfficient = coEfficient.divide(
                new BigDecimal(Math.pow(10, pwr)), 5);

        r += coEfficient + " * ";
        r += "10^" + pwr;
        return r;
    }
}
