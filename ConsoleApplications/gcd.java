package Misc.ConsoleApplications;

/**
 * Based on the Euclidean algorithm for finding the gcd of two numbers.
 *
 * @author Jacob Swineford
 */
public class gcd {

    public static void main(String[] args) {
        int a = 192;
        int b = 270;
        System.out.println("Gcd recursive:");
        System.out.println("The gcd is: " + gcd1(a, b) + "\n");
        System.out.println("Gcd loop:");
        System.out.println("The gcd is: " + gcd2(a, b));
    }

    private static int gcd1(int a, int b) {
        System.out.println("variables: " + a + ", " + b);
        if (b == 0) {
            return a;
        }
        return gcd1(b, a%b);
    }

    private static int gcd2(int a, int b) {
        while (b != 0) {
            int c = b;
            System.out.println("Variables: " + a + ", " + b);
            b = a % b;
            a = c;
        }
        System.out.println("Variables: " + a + ", " + b);
        return a;
    }
}
