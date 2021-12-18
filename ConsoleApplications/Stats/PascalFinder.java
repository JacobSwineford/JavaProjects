package Misc.ConsoleApplications.Stats;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Displays the number of pascal's triangle that
 * corresponds with the level and the location that
 * the user specifies.
 *
 * Pascal's triangle is used to calculate certain types
 * of probability, where you target a particular level
 * (where the first level is level 0), and a position
 * on the triangle (ignoring 1).
 *
 * There are special cases for the 0th, 1st, and 2nd
 * level. formats the number with commas if the number exceeds
 * 1000.
 *
 * @author Jacob Swineford
 */
public class PascalFinder {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String endMessage;
        System.out.print("Enter the level: ");
        int lvl = in.nextInt();
        if (lvl == 0) {
            endMessage = "That level only contains 1." +
                    "\nThere are no combinations to be made.";
        } else if (lvl == 1) {
            endMessage = "that level only contains 1_1." +
                    "\nThere are no combinations to be made.";
        } else {
            System.out.print("Possible options for this level: 1 - " +
                    (lvl - 1) + "\nEnter an index that " +
                    "is within that range: ");
            int location = in.nextInt();
            String[] arr = new String[lvl + 1];
            triangleLevels(arr);

            if (location <= 0 || location >= lvl) {
                endMessage = "Out of range. Try again.";
            } else {
                String s = BigFormat(findIndex(arr, lvl, location));
                endMessage = "Here is the number: " + s;
            }
        }
        System.out.println(endMessage);
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
     * Finds the desired index of pascal's triangle and
     * returns it.
     */
    private static String findIndex(String[] arr, int lvl, int location) {
        for (int i = 0; i < arr.length; i++) {
            if (i == lvl) {
                int firstIndex = arr[i].indexOf(" ");
                int lastIndex = arr[i].lastIndexOf(" ");
                int spaces = 0;
                StringBuilder str = new StringBuilder
                        (arr[i].substring(firstIndex + 1, lastIndex));

                if (lvl == 2) {
                    return "2";
                }

                if (location == 1) { // first case
                    return str.substring(0, str.indexOf(" "));
                }

                if (location == lvl - 1) { // last case
                    return str.substring(str.lastIndexOf(" ") + 1);
                }

                // need of an immutable string for searching
                String ori = arr[i].substring(firstIndex + 1, lastIndex);
                for (Character c : ori.toCharArray()) {
                    if (Character.isWhitespace(c)) {
                        spaces++;
                    }

                    if (spaces == location - 1) { // normal case
                        str.delete(0, 1);
                        return str.substring(0, str.indexOf(" "));
                    }
                    str.delete(0, 1);
                }
            }
        }
        return null;
    }
    /**
     * Takes a String and formats commas every three spaces.
     * if string.length() % 3 != 0, then special cases are implemented
     * to imitate US currency comma usage.
     *
     * I suppose I could have saved a lot of hassle by formatting
     * the string using getCurrencyInstance and getting rid of the
     * dollar sign, but whats the fun (and practice) gained in doing that?
     */
    private static String BigFormat(String big) {
        ArrayList<Character> arr = new ArrayList<>();
        int mod = big.length() % 3;
        int div = big.length() / 3;

        for (char cha : big.toCharArray()) {
            arr.add(cha);
        }

        int commas;
        if (mod == 0) {
            commas = div - 1;
        } else {
            commas = div;
        }

        int cur = 0;
        for (int i = 0; i < commas; i++) {

            if (i == 0 && mod != 0) {
                arr.add(mod, ',');
            } else if (i == 0) {
                cur += 3;
                arr.add(cur, ',');
            }

            if (mod == 1 && i == 1) {
                cur += 5;
                arr.add(cur, ',');
            } else if (mod == 2 && i == 1) {
                cur+= 6;
                arr.add(cur, ',');
            } else if (i >= 1) {
                cur += 4;
                arr.add(cur, ',');
            }
        }

        String str = "";
        for (char c : arr) {
            str += c;
        }

        return str;
    }

}
