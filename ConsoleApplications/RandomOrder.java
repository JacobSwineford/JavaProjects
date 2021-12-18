package Misc.ConsoleApplications;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Asks the user for the amount of numbers to randomize, and
 * outputs a randomized list of those numbers.
 * @author Jacob Swineford
 */
public class RandomOrder {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("n numbers: ");
            int n = in.nextInt();
            int[] arr = new int[n];
            fillArr(arr);
            shuffle(arr);
            System.out.println(Arrays.toString(arr));
        }
    }

    private static void fillArr(int[] arr) {
        for (int i = 1; i <= arr.length; i++) {
            arr[i - 1] = i;
        }
    }

    /**
     * Shuffles  via the Fisher-Yate method.
     */
    private static void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Random rand = new Random();
            int r = rand.nextInt(arr.length - i) + i;
            int oriTemp = arr[i];
            arr[i] = arr[r];
            arr[r] = oriTemp;
        }
    }
}
