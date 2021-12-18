package Misc.ConsoleApplications;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class DistinctDigits {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a year: ");
        String year = in.nextLine();
        System.out.println(nextDistinctYear(year));
    }

    // only works for 4 digit years
    private static boolean isDistinct(String year) {
        char dig1 = year.charAt(0);
        char dig2 = year.charAt(1);
        char dig3 = year.charAt(2);
        char dig4 = year.charAt(3);
        if (dig1 == dig2) return false;
        if (dig1 == dig3) return false;
        if (dig1 == dig4) return false;
        if (dig2 == dig3) return false;
        if (dig2 == dig4) return false;
        if (dig3 == dig4) return false;
        return true;
    }

    private static boolean isDistinct2(String year) {
        char[] arr = year.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (char c : arr) {
            for (char cha : list) {
                if (c == cha) return false;
            }
            list.add(c);
        }
        return true;
    }

    private static boolean isDistinct3(String year) {
        char[] arr = year.toCharArray();
        char[] test = new char[year.length()];
        int index = 0;
        for (char c : arr) {
            for (char cha : test) {
                if (c == cha) return false;
            }
            test[index++] = c;
        }
        return true;
    }

    private static String nextDistinctYear(String year) {
        int nextYear = Integer.parseInt(year) + 1;
        String tester = String.valueOf(nextYear);
        while(!isDistinct3(tester)) {
            tester = String.valueOf(Integer.parseInt(tester) + 1);
        }
        return tester;
    }
}
