package Misc.ConsoleApplications.Discrete;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class TruthTables {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Number of variables: ");
        int variables = in.nextInt();
        printTruthTables(variables);
        System.out.println("\nThere are " + (int) Math.pow(2, variables)
                + " Combinations.");
        String s = "";
        s += "hi";
        System.out.println(s);
    }

    private static void printTruthTables(int numVariables) {
        char[][] truthArray = truthArray(numVariables);
        char[][] invertedArray = inverseTruthArray(truthArray, numVariables);
        printHeading(numVariables);

        for (int i = 0; i < Math.pow(2, numVariables); i++) {
            // normal
            for (int j = 0; j < numVariables; j++) {
                if (j == 0) {
                    System.out.print("| ");
                } else {
                    System.out.print(" ");
                }
                System.out.print(truthArray[j][i] + " |");
            }
            // inverted
            for (int j = 0; j < numVariables; j++) {

                System.out.print("  ");
                System.out.print(invertedArray[j][i] + " |");
            }
            System.out.println();
        }
    }

    private static char[] booleanValuesFor(int numVariables, int index) {
        // 2 ^ numVariables = number of boolean values
        double numValues = Math.pow(2, numVariables);
        char[] cha = new char[(int) numValues];

        // 2 ^ numVariables - 1 - index = number of times a value is repeated
        double limit = Math.pow(2, numVariables - 1 - index);
        char a = 'F';
        for (int i = 0; i < numValues; i++) {
            if (i % limit == 0 && i != 0) {
                if (a == 'T') {
                    a = 'F';
                } else {
                    a = 'T';
                }
            }
            cha[i] = a;
        }
        return cha;
    }

    private static char[][] truthArray(int numVariables) {
        char[][] cha = new char[numVariables][(int) Math.pow(2, numVariables)];
        for (int i = 0; i < cha.length; i++) {
            cha[i] = booleanValuesFor(numVariables, i);
        }
        return cha;
    }

    private static char[][] inverseTruthArray(char[][] truthArray, int numVariables) {
        char[][] invertedArray = new char[numVariables][(int) Math.pow(2, numVariables)];
        int in = 0;
        for (char[] cha : truthArray) {
            invertedArray[in] = invertedOf(cha);
            in++;
        }
        return invertedArray;
    }

    private static void printHeading(int numVariables) {
        String labels = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // normal
        for (int i = 0; i < numVariables; i++) {
            System.out.print("| " + labels.charAt(i) + " ");
        }
        // inverted
        for (int i = 0; i < numVariables; i++) {
            System.out.print("| " + (char) 172  + labels.charAt(i) + " ");
        }
        System.out.print("|\n");
        //normal
        for (int i = 0; i < numVariables; i++) {
            System.out.print("|---");
        }
        // inverted
        for (int i = 0; i < numVariables; i++) {
            System.out.print("|----");
        }
        System.out.print("|\n");
    }

    private static char[] invertedOf(char[] truthArray) {
        char[] n = new char[truthArray.length];
        int i= 0;
        for (char c : truthArray) {
            if (c == 'T') {
                n[i] = 'F';
            }
            if (c == 'F') {
                n[i] = 'T';
            }
             i++;
        }
        return n;
    }
}
