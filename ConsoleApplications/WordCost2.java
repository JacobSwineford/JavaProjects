package Misc.ConsoleApplications;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class WordCost2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String[] words = in.nextLine().split(" ");

        class SortBasedOnAsciiValues implements Comparator<String> {

            @Override
            public int compare(String o1, String o2) {
                return asciiValue(o1) - asciiValue(o2);
            }

            private int asciiValue(String word) {
                int val = 0;
                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    val += arr[i];
                }
                return val;
            }

        }

        Arrays.sort(words, new SortBasedOnAsciiValues());
        for (String s : words) {
            System.out.println(s + " val: ");
        }
    }


}