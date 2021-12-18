package Misc.CustomClasses.Mancala;

import java.util.Scanner;

/**
 * Mancala is strategy game about moving pebbles in a
 * counter-clockwise motion to get pebbles in your
 * pocket. The board consists of 12 round divots
 * surrounding the middle accompanied by the player's
 * rectangular divots on either side (stores).
 *
 * Four pieces are placed in each of the 12 pockets
 * to start the game.
 *
 * The game begins with one player picking up all the pebbles
 * in any one of the pockets on their side. Moving counter-clockwise,
 * the player deposits ones of the stones on each pocket until the
 * stones run out. The opponent then takes their turn. There are multiple outcomes:
 * 1. If the last pebble is dropped in the player's store, that player
 *    may take another turn.
 * 2. If the last pebble is dropped in an empty divot on their side,
 *    the player captures the last piece dropped and any pebbles in the
 *    divot directly opposite on the opponent's side.
 * 3. The game ends when all siz divots on either player's side is empty.
 * 4. If the game ends and a player has pebbles in their divots, they capture
 *    all the pebbles in their divots.
 *
 * The winner is determined by who has the most pebbles in their stores
 * when the game ends.
 *
 * -------------
 *
 * This class is used to represent a board game of Mancala, the current
 * status of the pebbles, and checking for the end of the game.
 *
 * Player A will be represented as the bottom of the board, and player B
 * will be represented as the top. Therefore, store A will always be on the
 * right and store B will always be on the left.
 *
 * @author Jacob Swineford
 */
public class Mancala {

    // A1 - A2 - A3 - A4 - A5 - A6 - AStore
    // - B1 - B2 - B3 - B4 - B5 - B6 - BStore
    private int[] divots;
    private char turn;

    public Mancala() {
        divots = new int[14];
        for (int i = 0; i < 14; i++) {
            if (i == 6 || i == 13) continue;
            divots[i]=4;
        }
        turn='A';
    }
    public int[] getDivots() {return divots;}
    public int getA() {return divots[6];}
    public int getB() {return divots[13];}
    public char getTurn() {return turn;}

    public void distribute(int index) {
        if (index == 6 || index == 13) return;
        try {
            int p = divots[index];
            int end = index + p;
            divots[index]=0;
            for (int i = index + 1; i <= end; i++)
            {
                divots[i%divots.length]+=1;
            }
            end=end%divots.length;
            if (end==6 && turn=='A') {
                return;
            }
            if (end==13 && turn=='B')
            {
                return;
            }
            if (divots[end]==1)
            {
                if (end < 6 && turn=='A') {
                    divots[6]+=divots[adjacent(end)]+divots[end];
                    divots[adjacent(end)]=0;
                    divots[end]=0;
                }
                if (end > 6 && turn=='B') {
                    divots[13]+=divots[adjacent(end)]+divots[end];
                    divots[adjacent(end)]=0;
                    divots[end]=0;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        if (turn=='A')
            turn='B';
        else turn='A';
    }
    public void reset() {
        turn='A';
        for (int i = 0; i < 14; i++)
        {
            if (i==6 || i==13) {
                divots[i]=0;
            } else {
                divots[i]=4;
            }
        }
    }
    private int adjacent(int index) {
        int[] key = {12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        return key[index];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Mancala m = new Mancala();
        int[] divots = m.getDivots();
        while (true)
        {
            System.out.println("\nIt is " + m.getTurn() + "'s turn!\n");
            System.out.print("B: ");
            for (int i = divots.length - 1; i >= 7; i--)
            {
                System.out.print(divots[i] + " ");
            }
            System.out.print("\nA:   ");
            for (int i = 0; i < 7; i++)
            {
                System.out.print(divots[i] + " ");
            }
            if (m.getTurn() == 'A') System.out.print("\nMove (0-5): ");
            if (m.getTurn() == 'B') System.out.print("\nMove (7-12): ");
            int index = in.nextInt();
            m.distribute(index);
        }
    }
}
