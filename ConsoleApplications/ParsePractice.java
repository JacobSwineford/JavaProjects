package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * The point of this program is to test out different types
 * of parsing user input and putting it in a format that
 * is easily used for whatever is appropriate.
 *
 * @author Jacob Swineford
 */
public class ParsePractice {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // take a line entered ny the user and make each one
        // a separate string in an array
        System.out.print("Enter a string: ");
        String word = in.nextLine();
        String[] w = new String[word.length()];
        int e = 1;

        for (int i = 0; i < word.length(); i++) {
            w[i] = word.substring(i, e);
            System.out.print(w[i] + " ");
            e++;
        }

        // take a String entered by the user and make each
        // character a separate string in an array using characters.
        System.out.println();
        char[] w2 = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            char x = w2[i];
            System.out.print(x + " ");
        }

        // Take an input from the user in ints and determine how many ints
        // there are in the sequence. note that you don't need to parse this
        // value, as there is no need to.

        System.out.print("\nEnter a line of ints: ");
        String[] y = in.nextLine().split(" ");
        int len = y.length;
        System.out.println("Here is the amount of ints in the array: " + len);

        // parse the String[] to an int[]
        int[] numbers = new int[len];
        for (int i = 0; i < len; i++) {
            int num = Integer.parseInt(y[i]);
            numbers[i] = num;
        }

        // find the biggest value in the array
        int biggest = 0;

        for (int k : numbers) {
            if (k > biggest) {
                biggest = k;
            }
        }

        System.out.println("Here is the biggest number: " + biggest);

        // find the smallest value in the array\
        int smallest = 0;

        for(int d : numbers) {
            if (smallest == 0) {
                smallest = d;
            }
            if (d < smallest) {
                smallest = d;
            }
        }

        System.out.println("Here is the smallest number: " + smallest);

        // find the median of the numbers in the line of ints
        int sum = 0;
        for (int f : numbers) {
            sum += f;
        }

        double median = (double) sum / numbers.length; // make sure this is after the loop
        System.out.println("Here is the median of the numbers: " + median);
    }
}
