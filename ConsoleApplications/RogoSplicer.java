package Misc.ConsoleApplications;

/**
 * @author Jacob Swineford
 */
public class RogoSplicer {

    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("we got it");
        }
    }
}
