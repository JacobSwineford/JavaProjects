package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * Program that accepts multiple inputs and tests to see
 * if they are financial quantities are not.
 *
 * @author Jacob Swineford
 */
public class FQ {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter some (supposed) FQs, separated by a space: ");
        String[] arr = in.nextLine().split(" ");
        // For Dr. Ferland ->
        for (String s : arr) {
            boolean b = scan(s);
            if (b) System.out.println("The String \"" + s + "\" is a financial quantity.");
            else System.out.println("The string \"" + s + "\" is not a financial quantity.");
        }
    }

    /**
     * Scans a string to check if it is a financial quantity. This is done
     * through states of it's DFA, and only returns true on terminating states
     * 2, 5, 6, 7, and 8.
     * @param fq supposed financial quantity string
     */
    private static boolean scan(String fq) {
        int state = 0;
        int index = 0;
        char cur = fq.charAt(index);
        while (true) {
            System.out.println("Char: " + cur + " state: " + state);
            switch (state) {
                case 0:
                    if (cur == '$') {state = 1; break;}
                    return false;
                case 1:
                    if (index == fq.length() - 1) return false;
                    cur = fq.charAt(++index);
                    while (cur == '*') {
                        if (index == fq.length() - 1) {return false;}
                        cur = fq.charAt(++index);
                    }
                    if (cur == '0') {state = 2; break;}
                    if (nonZeroDigit(cur)) {state = 6; break;}
                    return false;
                case 2:
                    if (index == fq.length() - 1) return true;
                    cur = fq.charAt(++index);
                    if (cur == '.') {state = 3; break;}
                    return false;
                case 3:
                    if (index == fq.length() - 1) return false;
                    cur = fq.charAt(++index);
                    if (Character.isDigit(cur)) {state = 4; break;}
                    return false;
                case 4:
                    if (index == fq.length() - 1) return false;
                    cur = fq.charAt(++index);
                    if (Character.isDigit(cur)) {state = 5; break;}
                    return false;
                case 5:
                    return index == fq.length() - 1;
                case 6:
                    if (index == fq.length() - 1) return true;
                    cur = fq.charAt(++index);
                    if (cur == '.') {state = 3; break;}
                    if (cur == ',') {state = 9; break;}
                    if (Character.isDigit(cur)) {state = 7; break;}
                    return false;
                case 7:
                    if (index == fq.length() - 1) return true;
                    cur = fq.charAt(++index);
                    if (cur == '.') {state = 3; break;}
                    if (cur == ',') {state = 9; break;}
                    if (Character.isDigit(cur)) {state = 8; break;}
                    return false;
                case 8:
                    if (index == fq.length() - 1) return true;
                    cur = fq.charAt(++index);
                    if (cur == '.') {state = 3; break;}
                    if (cur == ',') {state = 9; break;}
                    return false;
                case 9:
                    if (index == fq.length() - 1) return false;
                    cur = fq.charAt(++index);
                    if (Character.isDigit(cur)) {state = 10; break;}
                    return false;
                case 10:
                    if (index == fq.length() - 1) return false;
                    cur = fq.charAt(++index);
                    if (Character.isDigit(cur)) {state = 11; break;}
                    return false;
                case 11:
                    if (index == fq.length() - 1) return false;
                    cur = fq.charAt(++index);
                    if (Character.isDigit(cur)) {state = 8; break;}
                    return false;
            }
        }
    }

    private static boolean nonZeroDigit(char c) {
        switch (c) {
            case '1': return true;
            case '2': return true;
            case '3': return true;
            case '4': return true;
            case '5': return true;
            case '6': return true;
            case '7': return true;
            case '8': return true;
            case '9': return true;
        }
        return false;
    }
}
