package Misc.ConsoleApplications;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Jacob Swineford
 */
public class CoinCollector {

    private int[] coins;
    private int turn = 1;

    private int collected1 = 0;
    private int collected2 = 0;

    public CoinCollector(int numCoins) {
        coins = new int[numCoins];
        randomizeCoins();
    }

    // randomize the coins that are in the array of coins
    private void randomizeCoins() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < coins.length; i++) {
            int val;
            int r = rand.nextInt(3);
            if (r == 0) {
                val = 5;
            } else if (r == 1) {
                val = 10;
            } else {
                val = 25;
            }
            coins[i] = val;
        }
    }

    public int whoseTurn() {
        return turn;
    }

    public void removeLeftCoin() {
        if (turn == 1) collected1 += coins[0];
        else collected2 += coins[0];

        int[] dup = new int[coins.length - 1];
        for (int i = 0; i < dup.length; i++) {
            dup[i] = coins[i + 1];
        }
        coins = dup;

        if (turn == 1) turn = 2;
        else turn = 1;
    }

    public void removeRightCoin() {
        if (turn == 1) collected1 += coins[coins.length - 1];
        else collected2 += coins[coins.length - 1];

        int[] dup = new int[coins.length - 1];
        for (int i = 0; i < dup.length; i++) {
            dup[i] = coins[i];
        }
        coins = dup;

        if (turn == 1) turn = 2;
        else turn = 1;
    }

    public int collectedValue1() {
        return collected1;
    }

    public int collectedValue2() {
        return collected2;
    }

    public boolean over() {
        return coins.length == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i : coins) {
            sb.append(i);
            sb.append(" ");
        }
        return sb.toString();
    }
}
