package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class AsteriskSquare {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter x, y: ");
        int x = in.nextInt(); int y = in.nextInt();
        System.out.print("What dimension?: ");
        int dim = in.nextInt();

        StringBuilder stars = new StringBuilder();
        while (dim != 0) {
            stars.append("*");
            dim--;
        }

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(stars + " ");
            }
            System.out.println();
        }
    }
}
