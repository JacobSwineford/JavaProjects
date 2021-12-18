package Misc.ConsoleApplications;

import java.util.Random;

/**
 * Plays the game of SimpleMan Monopoly.
 *
 * SimpleMan Monopoly is a type of monopoly that
 * is played purely by chance, thus it can be played
 * by those who don't possess human intelligence.
 * This game has these special rules:
 *
 * 1. Players must buy a property if it is available.
 *
 * 2. There is no bidding for auctions. To compensate, the player
 * with the most amount of money at that time (that can afford it)
 * will obtain the property for the MAX price that can be afforded.
 *
 * 3. There is no trading, for properties or money. Rather, there
 * is the inclusion of a new SimpleMan monopoly feature, blackmail.
 *
 * 4. Players must stay in jail for three turns if they are caught.
 *
 * 5. Players can only sell properties if they cannot afford a fee
 * for the space that they land on (blackmail is NOT related).
 *
 * 5b. Players must sell their most valuable property first, second
 * most valuable property second, and so on until they can pay the fee.
 *
 * 6. Players must buy houses if they have all the properties in a set and
 * they have money for at least one.
 *
 *
 * The game goes like this. the first player rolls
 * and moves the amount of spaces that they rolled on
 * the dice, then proceeds with whatever event they land on.
 * Once the first player is done, the second goes, and so
 * on until someone wins (AKA everyone else is eliminated).
 *
 *
 *
 * @author Jacob Swineford
 */
public class SimpleManMonopoly {
    public static void main(String[] args) {
    }

    /**
     * Contains the information for space 1,
     * Mediterranean Avenue.
     *
     * if all the purple properties are owned
     * and unimproved, then the rent is doubled.
     *
     * returns what the player owes based on their
     * assets.
     */
    private static int medAv(int money, int[] owned, int houses,
                             int curPlayer, int hotel) {

        final int HOUSE_PRICE = 50;
        final int HOTEL_PRICE = 50;
        final int BASE_PRICE = 60;
        int rent = 2;

        if (owned[1] == curPlayer && owned[3] == curPlayer && houses == 0) {
            rent = rent * 2;
        }

        if (money > BASE_PRICE && owned[1] == 0) {
            owned[1] += curPlayer;
        }

        if (houses == 1) {
            rent = 10;
        } else if (houses == 2) {
            rent = 30;
        } else if (houses == 3) {
            rent = 90;
        } else if (houses == 4) {
            rent = 160;
        } else if (hotel == 1) {
            rent = 250;
        }

        if (owned[1] != curPlayer) {
            if (money < rent) {
                while (money < rent) {
                    // mortgage properties
                }
            }
            money -= rent;
            return money;
        }

        return money;
    }
    /**
     * Rolls two dice and returns the sum.
     */
    private static int rollDice() {
        Random rand = new Random();
        int sum = 0;
        for (int i = 0; i < 2; i++) {
            int roll = rand.nextInt(6) + 1;
            sum += roll;
        }
        return sum;
    }
}

