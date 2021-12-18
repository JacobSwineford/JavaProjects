package Misc.ConsoleApplications;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jacob Swineford
 */
public class PancakeSorting {

    private String pancakeStack;
    private static String key = "abcdefghijklmnopqrstupwxyz";

    public PancakeSorting(int length) {
        pancakeStack = key.substring(0, length);
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < 5; i++) {
            flip(pancakeStack.charAt(rand.nextInt(pancakeStack.length())));
        }
    }

    public boolean isSorted() {
        return pancakeStack.equals(key.substring(0, pancakeStack.length()));
    }

    public void flip(char insertionPoint) {
        String flipped = new StringBuilder(pancakeStack.substring(
                pancakeStack.indexOf(insertionPoint))).reverse().toString();
        String begin = pancakeStack.substring(0, pancakeStack.indexOf(insertionPoint));
        System.out.println(flipped);
        System.out.println(begin);
        pancakeStack = begin + flipped;
    }

    @Override
    public String toString() {
        return pancakeStack;
    }

    public static void main(String[] args) {
        PancakeSorting p = new PancakeSorting(10);
        System.out.println(p);
    }
}
