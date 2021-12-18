package Misc.ConsoleApplications;

/**
 * You are trying to get 2 cupcakes to your grandma's house.
 * to get to your grandma's house, you need to pass over
 * seven bridges, each guarded by a troll. Each troll demands
 * you give it half of your cupcakes, but will always give one
 * in return because they are nice. This application finds
 * the number of cupcakes that could be made to get past the trolls
 * and have exactly 2 cupcakes at the end.
 *
 * @author Jacob Swineford
 */
public class Trolls {

    public static void main(String[] args) {
        int testTo = 10000000;
        int testBridges = 25;
        for (int i = 1; i <= testBridges; i++) {
            int works = 0;
            for (int j = 2; j <= testTo; j++) {
                if (cupcakes(j, i)) works++;
                else break;
            }
            System.out.println(works + " different starting amounts of cupcakes work for " + i + " bridges.");
        }
    }

    private static boolean cupcakes(int num, int bridges) {
        for (int i = 0; i < bridges; i++) {
            num /= 2;
            num++;
        }
        return num == 2;
    }
}
