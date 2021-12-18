package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class UpperCaseV2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a String: ");
        String[] str = in.nextLine().split(" ");

        for (String s : str) {
            String e = s.toUpperCase();
            for (char c : e.toCharArray()) {
                System.out.print(c + " ");
            }
            System.out.print(" ");
        }
    }
}
