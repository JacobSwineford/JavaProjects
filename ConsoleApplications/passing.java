package Misc.ConsoleApplications;

import java.util.Arrays;

/**
 * @author Jacob Swineford
 */
public class passing {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int[][] arr2 = {{1}};

        change(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void change(int[] arr) {
        arr[0] = 22;
    }
}
