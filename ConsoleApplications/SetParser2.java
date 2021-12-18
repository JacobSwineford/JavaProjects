package Misc.ConsoleApplications;

import java.util.*;

/**
 * Defines a recursive descent parser for sets.
 * The following CFG is used:
 * set -> { list }
 * list -> head tail | E
 * head -> number | set
 * tail -> , head tail | E
 *
 * This console application solves if a particular string
 * matches this syntax by defining methods for each syntactical word
 * and calling them recursively.
 *
 * @author Jacob Swineford
 */
public class SetParser2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a set with notation {F}, where F contains " +
                "the data in the set, separated by commas.\n" +
                "enter multiple entries by appending spaces between supposed" +
                " set strings: ");
        for (String str : in.nextLine().split(" ")) {
            if (process(str))
                System.out.println("The given string " + str + " is a set.");
            else System.out.println("The given string " + str + " is not a set.");
        }

    }

    /**
     * Checking is done with whitelisted and blacklisted characters.
     * Sets expect the characters { } n , -------- all else is blacklisted.
     * { must be followed by } n {
     * n must be followed by , }
     * } must be followed by } ,
     * , must be followed by n {
     */
    private static boolean process(String s) {
        int inexp = 0;
        for (int i = 0; i < s.length(); i++) {
            System.out.println("Character: " + s.charAt(i) + " inexp = " + inexp);
            if (inexp == 0 && i != 0) return false;
            if (i == s.length() - 1) {
                if (s.charAt(i) == '}') inexp--;
                break;
            }
            if (s.charAt(i) == '{') {
                if (s.charAt(i + 1) != '}' && s.charAt(i + 1) != 'n'
                    && s.charAt(i + 1) != '{')
                    return false;
                inexp++;
            }
            if (s.charAt(i) == 'n') {
                if (s.charAt(i + 1) != ',' && s.charAt(i + 1) != '}')
                    return false;
            }
            if (s.charAt(i) == '}') {
                if (s.charAt(i + 1) != '}' && s.charAt(i + 1) != ',')
                    return false;
                inexp--;
            }
            if (s.charAt(i) == ',') {
                if (s.charAt(i + 1) != 'n' && s.charAt(i + 1) != '{')
                    return false;
            }
        }
        return inexp == 0;
    }
}
