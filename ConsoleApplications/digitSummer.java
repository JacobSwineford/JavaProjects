package Misc.ConsoleApplications;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jacob Swineford
 */
public class digitSummer {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the amount of numbers to ne sorted: ");
        int amount = in.nextInt();

        Integer[] one = new Integer[amount];
        Integer[] second = new Integer[one.length];
        for (int i = 0; i < one.length; i++) {
            int r = ThreadLocalRandom.current().nextInt(1000);
            one[i] = r;
            second[i] = r;
        }
        Arrays.sort(second, new SumOfDigits());
        System.out.println("Original Sorted_By_Sum");
        for (int i = 0; i < one.length; i++) {
            System.out.println(one[i] + "        " + second[i]);
        }
    }
}

class SumOfDigits implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        int dig1 = digitSum(o1);
        int dig2 = digitSum(o2);
        return dig1 - dig2;
    }

    public static int digitSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
