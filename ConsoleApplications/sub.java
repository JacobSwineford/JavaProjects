package Misc.ConsoleApplications;

import Misc.Utilities.ArrayUtil;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Jacob Swineford
 */
public class sub {

    public static void main(String[] args) {
        String str = "0123456789";

        // inclusive, exclusive
        System.out.println(str.substring(1, 4));

        // inclusive
        System.out.println(str.substring(1));

        System.out.println(str.substring(str.lastIndexOf("4")));

        str = "hi\\there\\my\\name\\is\\joe";
        System.out.println(str.substring(str.lastIndexOf("\\")));
        System.out.println(str.substring(str.lastIndexOf("\\") + 1));



        // testing ArrayUtil collision methods
        Integer[][] arr = new Integer[5][5];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == 2) arr[i][j] = i + j;
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
