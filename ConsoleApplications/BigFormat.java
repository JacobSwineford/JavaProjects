package Misc.ConsoleApplications;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Takes a String entered by the user and formats commas
 * for numbers larger than 3 digits.
 *
 * @author Jacob Swineford
 */
public class BigFormat {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a number: ");
        StringBuilder number = new StringBuilder(in.next());
        ArrayList<String> str = new ArrayList<>();

        fillArr(number, str);
        StringBuilder nS = appendCommas(str);
        System.out.println(nS);
    }

    /**
     * given the StringBuilder and ArrayList arguments, fills
     * the ArrayList with varied Strings based on the position
     * of commas for big integers.
     */
    private static void fillArr(StringBuilder number, ArrayList str) {
        while (true) {
            if (number.length() == 0) {
                break;
            }
            if (number.length() % 3 != 0) { // first character special case
                String s = number.substring(0, number.length() % 3);
                str.add(s);
                number.delete(0, number.length() % 3);
            } else {
                String s = number.substring(0, 3);
                str.add(s);
                number.delete(0, 3);
            }
        }
    }

    /**
     * takes the filled ArrayList and formats the commas into
     * an appended StringBuilder object.
     *
     * @return StringBuilder object containing formatted big integer.
     */
    private static StringBuilder appendCommas(ArrayList str) {
        StringBuilder nS = new StringBuilder();
        for (Object s : str) {
            nS.append(s);
            nS.append(",");
        }
        nS.delete(nS.length() - 1, nS.length());
        return nS;
    }
}
