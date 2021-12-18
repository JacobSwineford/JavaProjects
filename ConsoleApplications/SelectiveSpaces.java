package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * Asks the user to enter a string, and asks
 * how many words there are to be in the desired
 * string.
 *
 * In addition, asks the user to
 * detail where to start deleting substrings.
 *
 * @author Jacob Swineford
 */

public class SelectiveSpaces {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String statement = in.nextLine();
        System.out.print("From the left? ");
        String tf = in.nextLine();
        System.out.print("Enter the amount of spaces " +
                "the final string should have: ");
        int finalSpaces = in.nextInt();

        boolean rightLeft = false;
        if (tf.contains("yes")) {
            rightLeft = true;
        }

        System.out.println("\n" + fragmentedString(
                statement, finalSpaces, rightLeft));
    }
    /**
     * Given the string, amount of spaces, and a boolean
     * value, returns a string that is concatenated to fit
     * those preferences.
     */
    private static String fragmentedString(String s, int finalSpaces, boolean b) {
        String newStr = "";
        int numSpaces = 0;
        char[] characters = s.toCharArray();
        String[] check = s.split(" ");

        if (check.length - 1 == finalSpaces) {
            return s;
        }

        // right sub.
        if (!b) {
            for (char c : characters) {
                if (Character.isWhitespace(c)) {
                    numSpaces++;
                }
                newStr += c;
                if (numSpaces - 1 == finalSpaces) {
                    StringBuilder bu = new StringBuilder(newStr);
                    bu.delete(newStr.lastIndexOf(" "), newStr.length()); // to subtract space
                    return bu.toString();
                }
            }
        } else { // left sub
            numSpaces = getSpaces(s);
            StringBuilder str = new StringBuilder(s);

            while (numSpaces != finalSpaces) {
                int start = 0;
                int end = str.indexOf(" ") + 1;
                str.delete(start, end);
                numSpaces -= 1;
            }
            return str.toString();
        }

        return "try again.";
    }
    /**
     * returns the number of spaces in the given string.
     */
    private static int getSpaces(String s) {
        char[] characters = s.toCharArray();
        int numSpaces = 0;
        for (char c : characters) {
            if (Character.isWhitespace(c)) {
                numSpaces++;
            }
        }
        return numSpaces;
    }
}
