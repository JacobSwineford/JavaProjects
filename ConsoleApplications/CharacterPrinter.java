package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class CharacterPrinter {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter character and how many to printField: ");
        char p = in.next().toCharArray()[0];
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            System.out.print(p + " ");
        }
    }
}
