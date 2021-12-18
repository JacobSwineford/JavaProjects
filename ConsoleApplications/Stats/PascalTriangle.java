package Misc.ConsoleApplications.Stats;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Displays the numbers in pascal's triangle.
 * Uses BigInteger for arbitrarily large values.
 *
 * Pascal's triangle is used to calculate certain types
 * of probability, where you target a particular level
 * (where the first level is level 0), and a position
 * on the triangle (ignoring 1).
 *
 * When run, there will be two triangles that pop up.
 * Two different methods are used to calculate the spaces
 * for each, and I couldn't decide which one was better,
 * all things considered (Though the second triangle does
 * have a little glitch, detailed below in the corresponding
 * doc comment). Also, this program doesn't take into account
 * that the top level is considered level 0, and considers the
 * top level 1 here.
 *
 * hyphens are used for even characterized spaces (reason below).
 *
 * @author Jacob Swineford
 */
public class PascalTriangle {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the amount of levels desired: ");
        int levels = in.nextInt();
        String[] arr = new String[levels];
        triangleLevels(arr);
        int b = biggestLine(arr).length();

        // first triangle
        String[] spaces = getSpaces(arr, b);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(spaces[i]);
            System.out.println(arr[i]);
        }
    }

    /**
     * Fills the given string array with the string levels
     * of pascal's triangle. Takes advantage of ArrayLists.
     */
    private static void triangleLevels(String[] arr) {
        ArrayList<BigInteger> previous = new ArrayList<>();
        ArrayList<BigInteger> current = new ArrayList<>();
        int levels = arr.length;
        String s = " ";
        BigInteger one = new BigInteger("1");
        previous.add(one);
        previous.add(one);

        for (int i = 0; i < levels; i++) {
            if (i == 0) {
                arr[i] = "1";
            } else if (i == 1) {
                arr[i] =  "1" + s + "1";
            } else {
                arr[i] = "1" + s;
                current.add(one);
                int x = (i + 1) - 2;

                // get the two numbers and add them to get the current
                for (int k = 0; k < x; k++) {
                    BigInteger y = previous.get(k);
                    BigInteger z = previous.get(k + 1);
                    BigInteger adds = y.add(z);
                    String str = adds.toString() + s;
                    arr[i] += str;
                    current.add(adds);
                }

                arr[i] += "1";
                current.add(one);

                // transfer lines
                previous.clear();
                for (BigInteger e : current) {
                    previous.add(e);
                }

                current.clear();
            }
        }
    }

    /**
     * For the given String array full of pascal levels,
     * returns the bottom, most lengthy level.
     */
    private static String biggestLine(String[] arr) {
        String seg = "";

        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                seg += arr[i];
            }
        }

        return seg;
    }

    /**
     * Takes the levels of pascal's triangle and
     * outputs a String[] of Spaces.
     *
     * This version of implementing spaces is based off of
     * the first observation that I had about triangles
     * with a non-arithmetic amount of characters on each line.
     *
     * This method splits the triangle in half and implements
     * the amount of spaces based on the half value of the bottom
     * line and the current line. hyphens are implemented to ensure
     * that the triangle is completely symmetrical. (there is always
     * a character to act as the middle).
     */
    private static String[] getSpaces(String[] arr, int b) {
        String[] spaces = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int l = arr[i].length();
            int h = l / 2;
            spaces[i] = "";

            // if even, then make the string odd
            if (l % 2 == 0) {
                StringBuilder str = new StringBuilder(arr[i]);
                String app = str.substring(h, l);
                str.delete(h , l);
                str.append("-");
                str.append(app);
                arr[i] = str.toString();
            }

            while (h < b / 2) {
                spaces[i] += " ";
                h++;
            }
        }
        return spaces;
        // b = 19, c = 16, hc = 8, hb = 9, spaces = 1
    }
    /**
     * Takes the levels of pascal's triangle and
     * outputs a String[] of Spaces.
     *
     * This version of implementing spaces is based off of
     * the second observation that I had about triangles
     * with a non-arithmetic amount of characters on each line.
     *
     * This method has the amount of spaces calculated by a single
     * integer, subtracting the length of the current line from the bottom
     * line and dividing that by two. hyphens are implemented to ensure
     * that the triangle is completely symmetrical.(there is always
     * a character to act as the middle).
     */
    private static String[] getSpaces2(String[] arr, int b) {
        String[] spaces2 = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int numSpaces = (b - arr[i].length()) / 2;
            int l = arr[i].length();
            int h = l / 2;
            spaces2[i] = "";
            while (numSpaces != 0) {
                spaces2[i] += " ";
                numSpaces--;
            }

            if (l % 2 == 0) {
                StringBuilder str = new StringBuilder(arr[i]);
                String app = str.substring(h, l);
                str.delete(h , l);
                str.append("-");
                str.append(app);
                arr[i] = str.toString();
            }
        }
        return spaces2;
    }
}
