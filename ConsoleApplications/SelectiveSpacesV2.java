package Misc.ConsoleApplications;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Asks the user to enter a string, and asks
 * how many words there are to be in the desired
 * string.
 *
 * In addition, asks the user to
 * detail where to start deleting substrings.
 *
 * This version makes more use of ArrayLists and
 * the syntax of the english language.
 *
 * @author Jacob Swineford
 */

public class SelectiveSpacesV2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String statement = in.nextLine();
        System.out.print("From the left? ");
        String tf = in.nextLine();
        System.out.print("Enter the amount of spaces " +
                "the final string should have: ");
        int finalSpaces = in.nextInt();

        String[] x = statement.split(" ");
        int numSpaces = x.length - 1;
        ArrayList<String> words = new ArrayList<>();

        boolean left = false;
        if (tf.contains("yes")) {
            left = true;
        }

        // add each of the words to the ArrayList.
        // The IDE says there's a better way, but
        // I don't understand addAll's arguments
        for (String str : x) {
            words.add(str);
        }

        if (numSpaces < finalSpaces) {
            System.out.println("Try again.");
        } else if (numSpaces == finalSpaces) {
            System.out.println(statement);
        } else if (left) {
            for (int i = 0; i < numSpaces - finalSpaces; i++) {
                words.remove(0);
                if (i == numSpaces - finalSpaces - 1) {
                    for (String str : words) {
                        if (finalSpaces == 0) {
                            System.out.print(str);
                            break;
                        }
                        System.out.print(str + " ");
                    }
                }
            }
        } else {
            for (int k = 0; k < numSpaces - finalSpaces; k++) {
                words.remove(words.size() - 1);
                if (k == numSpaces - finalSpaces - 1) {
                    for (String str : words) {
                        if (finalSpaces == 0) {
                            System.out.println(str);
                            break;
                        }
                        System.out.print(str + " ");
                    }
                }
            }
        }
    }
}
