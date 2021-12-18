package Misc.ConsoleApplications;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Randomizes a sentence that the user inputs.
 *
 * @author Jacob Swineford
 */
public class RandomOrderStr {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("GreatestSubstring: ");
            String[] arr = in.nextLine().split(" ");
            System.out.println(randomOf(arr));
            shuffle(arr);
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * Shuffles  via the Fisher-Yate method.
     */
    private static void shuffle(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Random rand = new Random();
            int r = rand.nextInt(arr.length - i) + i;
            String oriTemp = arr[i];
            arr[i] = arr[r];
            arr[r] = oriTemp;
        }
    }

    private static String randomOf(String[] arr) {
        Random rand = new Random();
        int r = rand.nextInt(arr.length);
        System.out.print(r + ": ");
        return arr[r];
    }
}
