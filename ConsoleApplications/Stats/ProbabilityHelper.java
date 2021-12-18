package Misc.ConsoleApplications.Stats;

import java.util.Scanner;

/**
 * Outputs my idea of something I like to refer to as "multi-dimensional"
 * problems in probability.
 *
 * This program asks the user to enter an amount of numbers within a
 * given range. With that, the program prints all the results of the
 * probability problem and gives the user percentages based on what trends
 * the program could find.
 *
 * DISCLAIMER: only does 2-10D problems (problem with implementing loops).
 * The last number that is entered acts as the "x" value of the graph,
 * meaning outputs after the last number is exhausted will be printed on
 * a different line.
 *
 * 1 is redundant as an argument.
 *
 * @author Jacob Swineford
 */
public class ProbabilityHelper {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("enter possibilities (2-10): ");
        String[] num = in.nextLine().split(" ");
        System.out.print("Ending value grid? (y/n): ");
        String g = in.next();
        System.out.print("Show probabilities with 0%? (y/n): ");
        String z = in.next();
        int d = num.length;
        int[] nV = nV(num, d);
        int p = problemLength(nV);

        String[] eV = new String[p];
        for (int t = 0; t < eV.length; t++) {
            eV[t] = "";
        }

        if (d == 2) {
            fill2DArray(eV, nV, g);
        } else if (d == 3) {
            fill3DArray(eV, nV, g);
        } else if (d == 4) {
            fill4DArray(eV, nV, g);
        } else if (d == 5) {
            fill5DArray(eV, nV, g);
        } else if (d == 6) {
            fill6DArray(eV, nV, g);
        } else if (d == 7) {
            fill7DArray(eV, nV, g);
        } else if (d == 8) {
            fill8DArray(eV, nV, g);
        } else if (d == 9) {
            fill9DArray(eV, nV, g);
        } else if (d == 10) {
            fill10DArray(eV, nV, g);
        }

        if (g.contains("y")) {
            System.out.println();
        }
        printResults(eV, nV, d, z);
    }

    /**
     * Takes an int[] and finds the largest number and returns it.
     */
    private static int biggest(int[] arr) {
        int b = 0;
        for (int i : arr) {
            if (i > b) {
                b = i;
            }
        }
        return b;
    }

    /**
     * finds and returns how many ending values
     * that the current possibilities
     * are supposed to output.
     *
     * This is a general method for multiplying
     * the values of any size int[].
     *
     * @return int with amount of results
     */
    private static int problemLength(int[] arr) {
        int total = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (i == 0) {
                total += arr[i] * arr[i + 1];
            } else {
                total = total * arr[i + 1];
            }
        }
        return total;
    }

    /**
     * Prints the results of the following probability problem.
     * This methods searches terms[] separate times for how many
     * of any given number is in a result, and ends with the method
     * printing relevant numbers found in the process.
     *
     * Prints:
     * Number of corresponding matches.
     * the matches that were currently being searched.
     * Probability of numbers showing up in probability game.
     */
    private static void printResults(String[] terms, int[] nValues,
                                     int d, String z) {

        System.out.println("There are " + terms.length + " ending values.");
        for (int i = 1; i <= nValues.length; i++) {
            int[] matches = new int[biggest(nValues)];
            for (String str : terms) {
                int[] wall = new int[biggest(nValues)];
                StringBuilder sB = new StringBuilder(str);
                for (int k = 0; k < d; k++) {
                    if (k != d - 1) {
                        String s = sB.substring(0, sB.indexOf("-"));
                        sB.delete(0, sB.indexOf("-") + 1);
                        int e = Integer.parseInt(s);
                        wall[e] += 1;

                        if (wall[e] == i) {
                            matches[e] += 1;
                        }
                    } else { // last case
                        String s = sB.substring(0);
                        int e = Integer.parseInt(s);
                        wall[e] += 1;

                        if (wall[e] == i) {
                            matches[e] += 1;
                        }
                    }
                }
            }
            System.out.println();
            for (int a = 0; a < biggest(nValues); a++) {
                double prob = ((double) matches[a] / terms.length) * 100;
                if (z.contains("y")) {
                        String s = "Probability: %.2f%% %n";
                        System.out.println("There are " + matches[a] + " ending values " +
                                "that contain " + i + " instance(s) of " + a +
                                " (" + (a + 1) + ")" );
                        System.out.printf(s, prob);
                } else {
                    if (prob != 0) {
                        String s = "Probability: %.2f%% %n";
                        System.out.println("There are " + matches[a] + " ending values " +
                                "that contain " + i + " instance(s) of " + a +
                                " (" + (a + 1) + ")" );
                        System.out.printf(s, prob);
                    }
                }
            }
        }
    }

    /**
     * Takes a String[] of numbers and casts the numerical values
     * of the String[] into an int[].
     *
     * @return int[] with casted values
     */
    private static int[] nV(String[] arr, int d) {
        int[] intArr = new int[d];
        for (int i = 0; i < d; i++) {
            intArr[i] = Integer.parseInt(arr[i]);
        }
        return intArr;
    }

    /**
     * takes a String[] and formats it to have the
     * needed values for a 2D probability problem.
     */
    private static void fill2DArray(String[] terms, int[] nValues, String p) {
        int cou = 0;
        System.out.println();
        for (int i = 0; i < nValues[0]; i++) {
            for (int k = 0; k < nValues[1]; k++) {
                terms[cou] = i + "-" + k;
                if (p.contains("y")) {
                    System.out.print(terms[cou] + " ");
                    if (k ==  nValues[1] - 1) {
                        System.out.println();
                    }
                }

                cou++;
            }
        }
    }

    /**
     * takes a String[] and formats it to have the
     * needed values for a 3D probability problem.
     */
    private static void fill3DArray(String[] terms, int[] nValues, String p) {
        int cou = 0;
        System.out.println();
        for (int i = 0; i < nValues[0]; i++) {
            for (int k = 0; k < nValues[1]; k++) {
                for (int j = 0; j < nValues[2]; j++) {
                    terms[cou] = i + "-" + k + "-" + j;
                    if (p.contains("y")) {
                        System.out.print(terms[cou] + " ");
                        if (j == nValues[2] - 1) {
                            System.out.println();
                        }
                    }

                    cou++;
                }
            }
        }
    }

    /**
     * takes a String[] and formats it to have the
     * needed values for a 4D probability problem.
     */
    private static void fill4DArray(String[] terms, int[] nValues, String p) {
        int cou = 0;
        System.out.println();
        for (int i = 0; i < nValues[0]; i++) {
            for (int k = 0; k < nValues[1]; k++) {
                for (int j = 0; j < nValues[2]; j++) {
                    for (int g = 0; g < nValues[3]; g++) {
                        terms[cou] = i + "-" + k + "-" +
                                j + "-" + g;
                        if (p.contains("y")) {
                            System.out.print(terms[cou] + " ");
                            if (g ==  nValues[3] - 1) {
                                System.out.println();
                            }
                        }
                        cou++;
                    }
                }
            }
        }
    }

    /**
     * takes a String[] and formats it to have the
     * needed values for a 5D probability problem.
     */
    private static void fill5DArray(String[] terms, int[] nValues, String p) {
        int cou = 0;
        System.out.println();
        for (int i = 0; i < nValues[0]; i++) {
            for (int k = 0; k < nValues[1]; k++) {
                for (int j = 0; j < nValues[2]; j++) {
                    for (int g = 0; g < nValues[3]; g++) {
                        for (int h = 0; h < nValues[4]; h++) {
                            terms[cou] = i + "-" + k + "-" +
                                    j + "-" + g + "-" + h;
                            if (p.contains("y")) {
                                System.out.print(terms[cou] + " ");
                                if (h ==  nValues[4] - 1) {
                                    System.out.println();
                                }
                            }
                            cou++;
                        }
                    }
                }
            }
        }
    }

    /**
     * takes a String[] and formats it to have the
     * needed values for a 6D probability problem.
     */
    private static void fill6DArray(String[] terms, int[] nValues, String p) {
        int cou = 0;
        System.out.println();
        for (int i = 0; i < nValues[0]; i++) {
            for (int k = 0; k < nValues[1]; k++) {
                for (int j = 0; j < nValues[2]; j++) {
                    for (int g = 0; g < nValues[3]; g++) {
                        for (int h = 0; h < nValues[4]; h++) {
                            for (int t = 0; t < nValues[5]; t++) {
                                terms[cou] = i + "-" + k + "-" +
                                        j + "-" + g + "-" + h +
                                        "-" + t;
                                if (p.contains("y")) {
                                    System.out.print(terms[cou] + " ");
                                    if (t ==  nValues[5] - 1) {
                                        System.out.println();
                                    }
                                }
                                cou++;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * takes a String[] and formats it to have the
     * needed values for a 7D probability problem.
     */
    private static void fill7DArray(String[] terms, int[] nValues, String p) {
        int cou = 0;
        System.out.println();
        for (int i = 0; i < nValues[0]; i++) {
            for (int k = 0; k < nValues[1]; k++) {
                for (int j = 0; j < nValues[2]; j++) {
                    for (int g = 0; g < nValues[3]; g++) {
                        for (int h = 0; h < nValues[4]; h++) {
                            for (int t = 0; t < nValues[5]; t++) {
                                for (int l = 0; l < nValues[6]; l++) {
                                    terms[cou] = i + "-" + k + "-" +
                                            j + "-" + g + "-" + h +
                                            "-" + t + "-" + l;
                                    if (p.contains("y")) {
                                        System.out.print(terms[cou] + " ");
                                        if (l ==  nValues[6] - 1) {
                                            System.out.println();
                                        }
                                    }
                                    cou++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * takes a String[] and formats it to have the
     * needed values for a 8D probability problem.
     */
    private static void fill8DArray(String[] terms, int[] nValues, String p) {
        int cou = 0;
        System.out.println();
        for (int i = 0; i < nValues[0]; i++) {
            for (int k = 0; k < nValues[1]; k++) {
                for (int j = 0; j < nValues[2]; j++) {
                    for (int g = 0; g < nValues[3]; g++) {
                        for (int h = 0; h < nValues[4]; h++) {
                            for (int t = 0; t < nValues[5]; t++) {
                                for (int l = 0; l < nValues[6]; l++) {
                                    for (int q = 0; q < nValues[7]; q++) {
                                        terms[cou] = i + "-" + k + "-" +
                                                j + "-" + g + "-" + h +
                                                "-" + t + "-" + l + "-" + q;
                                        if (p.contains("y")) {
                                            System.out.print(terms[cou] + " ");
                                            if (q ==  nValues[7] - 1) {
                                                System.out.println();
                                            }
                                        }
                                        cou++;
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * takes a String[] and formats it to have the
     * needed values for 9D probability problem.
     */
    private static void fill9DArray(String[] terms, int[] nValues, String p) {
        int cou = 0;
        System.out.println();
        for (int i = 0; i < nValues[0]; i++) {
            for (int k = 0; k < nValues[1]; k++) {
                for (int j = 0; j < nValues[2]; j++) {
                    for (int g = 0; g < nValues[3]; g++) {
                        for (int h = 0; h < nValues[4]; h++) {
                            for (int t = 0; t < nValues[5]; t++) {
                                for (int l = 0; l < nValues[6]; l++) {
                                    for (int q = 0; q < nValues[7]; q++) {
                                        for (int n = 0; n < nValues[8]; n++) {
                                            terms[cou] = i + "-" + k + "-" +
                                                    j + "-" + g + "-" + h +
                                                    "-" + t + "-" + l + "-" + q +
                                                    "-" + n;
                                            if (p.contains("y")) {
                                                System.out.print(terms[cou] + " ");
                                                if (n ==  nValues[8] - 1) {
                                                    System.out.println();
                                                }
                                            }
                                            cou++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * takes a String[] and formats it to have the
     * needed values for a 10D probability problem.
     */
    private static void fill10DArray(String[] terms, int[] nValues, String p) {
        int cou = 0;
        System.out.println();
        for (int i = 0; i < nValues[0]; i++) {
            for (int k = 0; k < nValues[1]; k++) {
                for (int j = 0; j < nValues[2]; j++) {
                    for (int g = 0; g < nValues[3]; g++) {
                        for (int h = 0; h < nValues[4]; h++) {
                            for (int t = 0; t < nValues[5]; t++) {
                                for (int l = 0; l < nValues[6]; l++) {
                                    for (int q = 0; q < nValues[7]; q++) {
                                        for (int n = 0; n < nValues[8]; n++) {
                                            for (int x = 0; x < nValues[9]; x++) {
                                                terms[cou] = i + "-" + k + "-" +
                                                        j + "-" + g + "-" + h +
                                                        "-" + t + "-" + l + "-" + q +
                                                        "-" + n + "-" + x;
                                                if (p.contains("y")) {
                                                    System.out.print(terms[cou] + " ");
                                                    if (x ==  nValues[9] - 1) {
                                                        System.out.println();
                                                    }
                                                }
                                                cou++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
