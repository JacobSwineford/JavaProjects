package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class UpperCase {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a String: ");
        String s = in.nextLine();
        StringBuilder str = new StringBuilder(s);

        for (int i = 0; i < s.length(); i++) {
            if (str.substring(0, 1).contains(" ")) {
                System.out.print(str.substring(0, 1).toUpperCase());
                str.delete(0, 1);
            } else {
                System.out.print(str.substring(0, 1).toUpperCase() + " ");
                str.delete(0, 1);
            }

        }
    }
}
