package Misc.ConsoleApplications;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Jacob Swineford
 */
public class DataBaseParser {

    public static void main(String[] args) {
        C c = new C();
        c.f();
    }

    private static int[][] parseDataString(String data) {
        String[] packs = data.split(";");
        String xLen = packs[0].substring(0, packs[0].indexOf(','));
        String yLen = packs[0].substring(packs[0].indexOf(',') + 1);
        int x = Integer.parseInt(xLen);
        int y = Integer.parseInt(yLen);

        // put the resulting values into a two dimensional array
        int[][] grid = new int[y][x];
        int packIndex = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = Integer.parseInt(packs[packIndex++]);
            }
        }
        return grid;
    }

    private static String makeDataString(int[][] grid) {
        StringBuilder insert = new StringBuilder(grid[0].length + "," + grid.length + ";");
        for (int[] aGrid : grid) {
            for (int anAGrid : aGrid) {
                insert.append(anAGrid);
                insert.append(";");
            }
        }
        return insert.toString();
    }
}

interface I1 {
    void f();
    // other abstract methods
}

interface I2 {
    void f();
    // other abstract methods
}

class C implements I1, I2 {
    @Override
    public void f() {
        System.out.println("I WAS CALLED");
    }
}

