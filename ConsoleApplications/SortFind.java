package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * This program uses a sorted array (of integers for now) to efficiently
 * find a value that may or may not be in the array. This is done by first
 * initializing two variables, min and max. as the program runs, binSearch
 * will increment min and max to get closer to the given variable, and will
 * find the variable if it is in the given sorted array. Otherwise, binSearch
 * will return false.
 *
 * @author Jacob Swineford
 */
public class SortFind {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // notice: natural ordered array
        int[] arr = {1, 2, 2, 3, 26, 57, 64, 73, 82, 91, 1013};
        System.out.print("Please enter a value to search for: ");
        int i = in.nextInt();
        boolean b = binSearch(i, arr);
        System.out.print("The given value " + i + " is ");
        if (b) System.out.print("in the array.");
        else System.out.print("not in the array.");
        System.out.println();

    }

    private static boolean binSearch(int val, int[] arr) {
        int min = 0; int max = arr.length - 1;
        if (val == arr[max]) return true;
        while (true) {
            int index = ((max - min) / 2) + min;
            int i = arr[index];
            if (max - min == 1) return false;
            if (i == val) return true;
            if (i > val) {max = index; continue;}
            min = index;
        }
    }
}
