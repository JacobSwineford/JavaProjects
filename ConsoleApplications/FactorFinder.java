package Misc.ConsoleApplications;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class FactorFinder {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string of numbers: ");
        String[] arr = in.nextLine().split(" ");
        String[] factors = factors(arr);

        IFactors(factors, arr);
    }

    /**
     * Fill arr[]
     */
    private static String[] factors(String[] arr) {
        String[] str = new String[arr.length];
        int a = 0;
        for (String s : arr) {
            StringBuilder temp = new StringBuilder();
            for (int i : getFactors(Integer.parseInt(s))) {
                temp.append(i + " ");
            }
            temp.delete(temp.length() - 1, temp.length());
            str[a] = temp.toString();
            a++;
        }

        return str;
    }

    /**
     * Finds all the factors of the given integer
     * and formats them into a ArrayList.
     */
    private static ArrayList<Integer> getFactors(int n) {
        ArrayList<Integer> end = new ArrayList<>();
       for (int i = 1; i <= n; i ++) {
           if (n % i == 0) {
               end.add(i);
           }
       }
       return end;
    }

    /**
     * Displays the individual factors of the user's
     * entered integers.
     */
    private static void IFactors(String[] arr, String[] f) {
        int c = 0;
        for (String str : arr) {
            System.out.print("Individual factors of " + f[c] + ": " + str);
            System.out.println();
            c++;
        }
    }

    /**
     * Takes a String array and returns the biggest number.
     */
    private static int biggestNum(String[] arr) {
        int b = 0;
        for (String s : arr) {
            if (Integer.parseInt(s) > b) {
                b = Integer.parseInt(s);
            }
        }
        return b;
    }
}
