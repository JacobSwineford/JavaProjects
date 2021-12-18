package Misc.ConsoleApplications;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class RandomTester {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a thing: ");
        int thing = in.nextInt();
        int current = thing;
        while (thing > 1) {
            current *= (thing - 1);
            System.out.println(current);
            thing--;
        }
    }


}
