package Misc.ConsoleApplications;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Coverts either a specified base representation to decimal
 * or decimal to a base representation.
 *
 * @author Jacob Swineford
 */
public class HexToDecimal {

    public static void main(String[] args) throws Exception {
        outputAll(4, 4 );
//        Scanner in = new Scanner(System.in);
//        System.out.print("Would you like to convert a number or base value? (n or b): ");
//        char key = in.next().toCharArray()[0];
//        System.out.print("Batch? (y or n): ");
//        char yn = in.next().toCharArray()[0];
//        if (yn == 'y') { // batch
//            if (key == 'n') {
//                while (true) {
//                    System.out.print("number and base (q q to quit): ");
//                    try {
//                        int val = in.nextInt();
//                        int base = in.nextInt();
//                        System.out.println("Base rep of " + val + " (" + base + ") = "
//                                + baseRepOf(val, base));
//                    } catch (InputMismatchException e) {
//                        break;
//                    }
//                }
//
//            }
//            if (key == 'b') {
//                while (true) {
//                    System.out.print("Base representation and base (q q to quit): ");
//                    try {
//                        String rep = in.next();
//                        int base = in.nextInt();
//                        System.out.println("Number of " + rep + " (" + base + ") = "
//                                + baseValOf(rep, base));
//                    } catch (InputMismatchException e) {
//                        break;
//                    }
//                }
//            }
//        } else {
//            if (key == 'n') {
//                System.out.print("number and base: ");
//                int val = in.nextInt();
//                int base = in.nextInt();
//                System.out.println("Base rep of " + val + " (" + base + ") = "
//                        + baseRepOf(val, base));
//                System.out.println("Base rep of " + val + " (" + base + ") = "
//                        + baseRepOf2(new BigInteger(Integer.toString(val)), base));
//            }
//            if (key == 'b') {
//                System.out.print("Base representation and base: ");
//                String rep = in.next();
//                int base = in.nextInt();
//                System.out.println("Number of " + rep + " (" + base + ") = "
//                        + baseValOf(rep, base));
//
//            }
//        }

    }

    private static String values = "{}[]-!#$0123456789abcdefghijklmnopqrstupwxyz";

    /**
     * Given a String representation of a base-10 number in another base,
     * the String representation in converted to base-10 and returned.
     * @param str given String representation
     * @param base base of String representation
     * @return base-10 value of parameters
     * @throws Exception base is more than 16
     */
    private static int baseValOf(String str, int base) throws Exception {
        if (base > 16) {
            throw new Exception("base is more than 16");
        }
        int r = 0;
        int pow = str.length() - 1;
        for (char c : str.toCharArray()) {
            int i = values.indexOf(c); // for letters
            r += i * Math.pow(base, pow);
            pow--;
        }
        return r;
    }

    /**
     * Converts a base-10 number to a String representing another base.
     * @param val given base-10 value
     * @param base base to convert to
     * @return String representation of given base-10 value in given base
     * @throws Exception base is more than 16
     */
    private static String baseRepOf(int val, int base)  {
        String temp = "";
        int numTemp = val;
        while (numTemp != 0) {
            int i = numTemp % base;
            temp += values.toCharArray()[i];
            numTemp = numTemp / base;
        }
        return reversed(temp);
    }

    private static String baseRepOf2(BigInteger b, int base) {
        String temp = "";
        BigInteger numTemp = b;
        BigInteger ba = new BigInteger(Integer.toString(base));
        BigInteger i;

        while (!numTemp.equals(new BigInteger("0"))) {
            i = numTemp.mod(ba);
            temp += values.toCharArray()[i.intValueExact()];
            numTemp = numTemp.divide(ba);
        }
        return reversed(temp);
    }

    private static String reversed(String str) {
        String r = "";
        char[] arr = str.toCharArray();
        for (int i = str.length() - 1; i > -1; i--) {
            r += arr[i];
        }
        return r;
    }

    private static void outputAll(int length, int base) {
        for (int i = 1; i <= length; i++) {
            outputAllForLength(i, base);
        }
    }

    private static void outputAllForLength(int length, int base)  {
        BigInteger cur = new BigInteger("0");
        BigInteger ba = new BigInteger(Integer.toString(base));
        BigInteger numLoops = ba.pow(length);
        while (!cur.equals(numLoops)) {
            String str = baseRepOf2(cur, base);
            str = extendTo(str, length);
            if (cur.mod(new BigInteger("15")).equals(new BigInteger("0"))) System.out.println();
            System.out.print(str + " ");
            cur = cur.add(new BigInteger("1"));
        }
    }

    private static String extendTo(String str, int length) {
        int loops = length - str.length();
        String valo = values.substring(0,1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < loops; i++) {
            sb.append(valo);
        }
        return sb.toString() + str;
    }
}
