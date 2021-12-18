package Misc.ConsoleApplications.Stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class StatisticalSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("numbers to sort: ");
        String[] arr = in.nextLine().split(" ");
        double[] dArr = new double[arr.length];
        int i = 0;
        for (String s : arr) {
            dArr[i] = Double.parseDouble(s);
            i++;
        }
        Arrays.sort(dArr);
        System.out.println(Arrays.toString(dArr));
        System.out.println("Mean: " + mean(dArr));
        System.out.println("Median: " + median(dArr));
        System.out.println("Mode: " + mode(dArr)); // only works if there is one node
        System.out.println("Variance: " + variance(dArr));
        System.out.println("Standard Deviation: " + standardDeviation(dArr));
    }

    private static double mean(double[] arr) {
        int total = 0;
        for (double i : arr) {
            total += i;
        }
        return 1.0 * total / arr.length;
    }

    private static double median(double[] arr) {
        Arrays.sort(arr);
        if (arr.length % 2 == 0) {
            return ((arr[arr.length / 2] - arr[(arr.length / 2) - 1]) / 2.0)
                    + arr[(arr.length / 2) - 1];
        } else {
            return arr[arr.length / 2];
        }
    }

    private static double mode(double[] arr) {
        int i = 0;
        double[] val = new double[numValues(arr)];
        double[] r = reduce(arr);
        for (double d : reduce(arr)) {
            for (double dou : arr) {
                if (dou == d) {
                    val[i]++;
                }
            }
            i++;
        }

        // index of biggest value for val
        return r[indexOf(biggest(val), val)];
    }

    private static double biggest(double[] arr) {
        double biggest = 0;
        for (double d : arr) {
            if (d > biggest) {
                biggest = d;
            }
        }
        return biggest;
    }

    private static int numValues(double[] arr) {
        int values = 0;
        ArrayList<Double> al = new ArrayList<>();
        for (double d : arr) {
            if (!al.contains(d)) {
                values++;
                al.add(d);
            }
        }
        return values;
    }

    private static double[] reduce(double[] arr) {
        int i = 0;
        double[] dArr = new double[numValues(arr)];
        ArrayList<Double> al = new ArrayList<>();
        for (double d : arr) {
            if (!al.contains(d)) {
                dArr[i] = d;
                al.add(d);
                i++;
            }
        }
        return dArr;
    }

    private static double variance(double[] arr) {
        double val = 0;
        double m = mean(arr);
        for (double d : arr) {
            val += Math.pow(d - m, 2);
        }
        return val / (arr.length - 1);
    }

    private static double standardDeviation(double[] arr) {
        return Math.sqrt(variance(arr));
    }

    private static int indexOf(double d, double[] arr) {
        int i = 0;
        for (double dou : arr) {
            if (dou == d) {
                break;
            }
            i++;
        }
        return i;
    }
}

