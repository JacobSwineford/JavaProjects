package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * Checks if the given string has an x followed by a y.
 *
 * @author Jacob Swineford
 */
public class XYString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = in.next();

        if (xY(s)[0]) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
    }

    /**
     *
     */
    private static boolean[] xY(String s) {
        boolean[] boo = new boolean[3];
        String g;
        for (char c : s.toCharArray()) {
            if (c == 'x' || c == 'X') {
                boo[1] = true;
                g = Character.toString(c);
                String sub = s.substring(s.indexOf(g));
                if (sub.contains("y") || sub.contains("Y")) {
                    boo[0] = true;
                    boo[2] = true;
                }
            }
        }
        return boo;
    }
}
