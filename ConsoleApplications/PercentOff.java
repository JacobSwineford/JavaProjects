package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * Prompts the user to enter a price
 * and the percent discount that they desire. then,
 * displays the number formatted with a currency sign
 * and decimals up to to places.
 *
 * returns a negative double for percent discounts more
 * than 100%.
 *
 * @author Jacob Swineford
 */
public class PercentOff {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a price: ");
        double price = in.nextDouble();
        System.out.print("Enter a percent discount: ");
        double discountPer = in.nextDouble();
        double disPrice = price - (price * (discountPer / 100));

        String s = "Final Price: $%.2f %n";
        System.out.printf(s, disPrice);
    }
}