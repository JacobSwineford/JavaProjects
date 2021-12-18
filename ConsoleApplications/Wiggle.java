package Misc.ConsoleApplications;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class Wiggle {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a String: ");
        String s = in.nextLine();
        System.out.print("Enter the length: ");
        int l = in.nextInt();

        int maxSpaces = 0;
        boolean reverse = false;
        for (int i = 0; i < l; i++) {
            String spaces = "";
            for (int k = 0; k < maxSpaces; k++) {
                spaces += " ";
            }
            System.out.print(spaces);
            System.out.println(s);

            if (!reverse) {
                maxSpaces++;
            } else if (reverse) {
                maxSpaces--;
            }

            if (maxSpaces == s.length() / 2) {
                reverse = true;
            } else if (maxSpaces == 0) {
                reverse = false;
            }
        }
    }
}