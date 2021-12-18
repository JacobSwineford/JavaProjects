package Misc.ConsoleApplications;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Finds all the combinations of change when a customer
 * gives more than the initial price.
 *
 * This calculation is not responsible for rounding decimals
 * that would happen to round up a cent (.067 -> .07).
 * Thus, it is recommended to input values in D:CC form.
 *
 * @author Jacob Swineford
 */
public class ChangeGiver {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the dollar amount of the item (D:CC): ");
        BigDecimal initAmount = BigDecimal.valueOf(in.nextDouble());
        System.out.print("Enter what the customer paid (D:CC): ");
        BigDecimal paid = BigDecimal.valueOf(in.nextDouble());
        BigDecimal changeNeeded = paid.subtract(initAmount);

        BigDecimal d = changeNeeded.multiply(BigDecimal.valueOf(100));
        int changeInt = Integer.parseInt(d.toString().substring(
                0, d.toString().indexOf(".")));
        int[] values = new int[5]; // available currency
        values[0] = changeInt / 100; // dollars
        values[1] = changeInt / 25; // quarters
        values[2] = changeInt / 10; // dimes
        values[3] = changeInt / 5; // nickels
        values[4] = changeInt; // pennies

        System.out.println(Arrays.toString(values));

        ArrayList<int[]> list = listOfChange(changeNeeded, values);
        System.out.println("There are " + list.size() + " combinations: ");
        for (int[] arr : list) {
            System.out.println(arr[0] + " Dollars " + arr[1] + " Quarters " + arr[2] + " Dimes "
            + arr[3] + " Nickels " + arr[4] + " Pennies");
        }

    }

    /**
     * returns a list of int[] containing the possible combinations of change
     * needed to get to the specified amount.
     *
     * @param availableCurrency array detailing the number of available currency for
     *                      Dollars, Quarters, Dimes, Nickels, and Pennies.
     */
    private static ArrayList<int[]> listOfChange(BigDecimal amount, int[] availableCurrency) {
        BigDecimal m = amount.multiply(BigDecimal.valueOf(100)); // -> 200.00
        int aInt = Integer.parseInt(m.toString().substring(
                0, m.toString().indexOf("."))); // -> 200

        ArrayList<int[]> arr = new ArrayList<>();
        for (int d = 0; d <= availableCurrency[0]; d++) {
            for (int i = 0; i <= availableCurrency[1]; i++) {
                for (int j = 0; j <= availableCurrency[2]; j++) {
                    for (int q = 0; q <= availableCurrency[3]; q++) {
                        for (int g = 0; g <= availableCurrency[4]; g++) {
                            BigDecimal b = BigDecimal.valueOf(
                                    (d * 100) + (i * 25) + (j * 10) + (q * 5) + g); // -> 200
                            int bInt = Integer.parseInt(b.toString());
                            if (bInt == aInt) {
                                int[] change = new int[5];
                                change[0] = d;
                                change[1] = i;
                                change[2] = j;
                                change[3] = q;
                                change[4] = g;
                                arr.add(change);
                            }
                        }
                    }
                }
            }
        }
        return arr;
    }

}
