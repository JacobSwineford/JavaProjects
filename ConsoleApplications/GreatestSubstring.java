package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class GreatestSubstring {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string and a length: ");
        String str = in.next();
        int max = Integer.parseInt(in.next());
        System.out.println();
        System.out.println("The biggest substring is: " + result(max, str));
    }

    private static int result(int max, String str) {
        int max2 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i + max == str.length() + 1) {
                break;
            }
            String sb = str.substring(i, i + max);
            int j = Integer.parseInt(sb);
            if (j > max2) {
                max2 = j;
            }
        }
        return max2;
    }
}
