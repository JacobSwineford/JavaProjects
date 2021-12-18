package Misc.ConsoleApplications;

import java.security.MessageDigest;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class digest {

    public static void main(String[] args) throws Exception {
        String alg = "SHA-1";
        Scanner in = new Scanner(System.in);
        String ent = in.nextLine();
        byte[] arr = MessageDigest.getInstance(alg).digest(ent.getBytes());
        for (byte b : arr) {
            System.out.print(b + " ");
        }
    }


}
