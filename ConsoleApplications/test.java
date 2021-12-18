package Misc.ConsoleApplications;

/**
 * @author Jacob Swineford
 */
public class test {

    public static void main(String[] args) {
        Harakka h = new Harakka(5, 6, 0);
        String[] rows = h.toString().split("\n");
        for (int i = 0; i < 5; i++) {
            String[] vals = rows[i].split(" ");
            for (int j = 0; j < 6; j++) {
                System.out.print(vals[j] + " ");
            }
            System.out.println();
        }
    }









    static int gcd(int a, int b) {
        System.out.println("gcd1: a, b: " + a + ", " + b);
        while (a != b) {
            if (a > b)
            {
                a = a%b;
                System.out.println("a, b: " + a + ", " + b);
            }
            else b = b%a;
        }
        return a;
    }

    static int gcd2(int a, int b) {
        if (b == 0) return a;
        return gcd2(b, a%b);
    }
}
