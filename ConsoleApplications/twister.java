package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * twists words.
 *
 * @author Jacob Swineford
 */
public class twister {

    static int y = 3;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String str = in.nextLine();
        StringBuilder sb = new StringBuilder(str);

        // make the given string a multiple of twisterHeight
        int twisterHeight = 20;
        for (int i = str.length(); i % twisterHeight != 0; i++) sb.append(" ");
        int wl = sb.length() / twisterHeight;

        // output twister
        for (int i = 0; i < twisterHeight; i++) {
            String s = sb.substring(wl * i, wl * (i + 1));
            StringBuilder stub = new StringBuilder(s);
            if (i % 2 != 0) stub.reverse();
            System.out.println(stub);
        }

        int a = f();
        int b = a + y;

        System.out.println();
        System.out.println("f() -> " + a + " f() + y -> " + b);
     }

     static int f() {
        y = y + 5;
        return 4 * y;
     }
}
