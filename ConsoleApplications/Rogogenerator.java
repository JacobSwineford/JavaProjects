package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class Rogogenerator {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] grid = new int[2][2];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.printf("value for [%d, %d]: ", i, j);
                int val = in.nextInt();
                grid[i][j] = val;
            }
        }

        // print
        System.out.print("\nnew int[][] = \n{");
        for (int i = 0; i < grid.length; i++) {
            System.out.print("{");
            for (int j = 0; j < grid[i].length; j++) {
                if (j != grid[i].length - 1) {
                    System.out.printf("%d, ", grid[i][j]);
                } else {
                    System.out.printf("%d", grid[i][j]);
                }

            }
            if (i != grid.length - 1) {
                System.out.println("},");
            } else {
                System.out.println("}}");
            }
        }

        int[][] a = new int[][]
                {
                {1, 2},
                {3, 4},
                {5, 6}
                };
    }
}
