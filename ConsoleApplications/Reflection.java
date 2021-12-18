package Misc.ConsoleApplications;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jacob Swineford
 */
public class Reflection {

    private ArrayList<String> history;
    private String gameString;

    private static String key = "abcdefghijklmnopqrstupwxyz";

    public Reflection(int length) {
        gameString = key.substring(0, length);
        history = new ArrayList<>();
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < 1; i++) {
            char first = gameString.charAt(rand.nextInt(gameString.length()));
            char second = gameString.charAt(rand.nextInt(gameString.length()));
            reflect(first, second);
        }
        history.clear();
    }

    public void reflect(char first, char second) {
        int firstIndex = gameString.indexOf(first);
        int secondIndex = gameString.indexOf(second);

        // we need to switch them if
        if (firstIndex > secondIndex) {
            int temp = firstIndex;
            firstIndex = secondIndex;
            secondIndex = temp;
        }

        String begin = gameString.substring(0, firstIndex);
        StringBuilder reversed = new StringBuilder(gameString.substring(firstIndex,
                secondIndex + 1)).reverse();
        String end = gameString.substring(secondIndex + 1);
        history.add(gameString);
        gameString = begin + reversed.toString() + end;
    }

    public boolean over() {
        return gameString.equals(key.substring(0, gameString.length()));
    }

    public ArrayList<String> history() {
        return history;
    }

    @Override
    public String toString() {
        return gameString;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Reflection r = new Reflection(10);
        System.out.println(r);
        System.out.println();
        System.out.print("Make a move! ");
        char c1 = scan.next().charAt(0);
        char c2 = scan.next().charAt(0);
        r.reflect(c1, c2);
        System.out.println(r);
    }
}
