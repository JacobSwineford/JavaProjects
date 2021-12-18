package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class factorial {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("enter a number: ");
        int i = in.nextInt();
        System.out.println(f(i));
    }

    private static  int f (int i) {
        if (i == 0)
            return 1;
        return i * f(i - 1);
    }
}
