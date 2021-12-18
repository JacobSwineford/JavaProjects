package Misc.ConsoleApplications;

import java.util.Scanner;

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
public class SetParser {

    // string to be tested and it's respective index
    private static String s;
    private static int index = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a set with notation {F}, where F contains " +
                "the data in the set, separated by commas.\n" +
                "enter multiple entries by appending spaces between supposed" +
                " set strings: ");
        for (String str : in.nextLine().split(" ")) {
            s = str;
            index = 0;
            if (set()) System.out.println("The given string " + s + " is a set.");
            else System.out.println("The given string " + s + " is not a set.");
        }

    }

    // -> { list }
    private static boolean  set() {
        if (s.charAt(index) != '{') return false;
        index++;
        if (s.charAt(index) == '}') {index++; return true;} // empty set
        if (!list()) return false;
        try {
            return s.charAt(index++) == '}';
        } catch (IndexOutOfBoundsException e) {return false;}
         // grammar fails here -> {n,n}} is not a valid set
    }

    // -> head tail | E
    private static boolean list() {
        if (!head()) return false;
        return tail();
    }

    // -> number | set
    private static boolean head() {
        if (s.charAt(index) == 'n') {index++; return true;}
        return set();
    }

    // -> , head tail | E
    private static boolean tail() {
        if (s.charAt(index) == ',') {
            index++;
            if (!head()) return false;
            return tail();
        }
        return true;
    }



}
