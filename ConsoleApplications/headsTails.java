package Misc.ConsoleApplications;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class headsTails {

    public static void main(String[] args) {
        Random rand = new Random();
        int r = rand.nextInt(2);
        System.out.println("The coin has flipped to...");
        if (r == 1) {
            System.out.println("Heads!");
        } else
            System.out.println("Tails!");
    }
}
