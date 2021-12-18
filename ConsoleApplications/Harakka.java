package Misc.ConsoleApplications;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Back end for the game of Harakka. The game is played on a rectangular grid
 * of numbered tiles. The goal is to rearrange the tiles into numerical order
 * through a series of clockwise rotations of 2x2 sub-grids.
 *
 * @author Jacob Swineford
 */
public class Harakka {

    private int[][] board;

    /**
     * Creates a new game with the default (high) difficulty.
     *
     * @param r number of rows
     * @param c number of columns
     */
    public Harakka(int r, int c) {
        this(r, c, r * c);
    }

    /**
     * Creates a new game.
     *
     * @param r number of rows
     * @param c number of columns
     * @param n level of difficulty (number of rotations needed to win)
     */
    public Harakka(int r, int c, int n) {
        board = new int[r][c];
        incrementBoard();
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        while (n > 0) {
            if (r < 2 || c < 2) break;
            int row = rand.nextInt(r);
            int col = rand.nextInt(c);
            if (rotate(board[row][col])) {
                rotate(board[row][col]);
                rotate(board[row][col]);
                n--;
            }
        }
    }

    /**
     * Returns true if the game is in the winning configuration.
     */
    public boolean isWinning() {
        int in = 0;
        for (int[] arr : board) {
            for (int i : arr) {
                if (i != ++in) return false;
            }
        }
        return true;
    }

    /**
     * Performs a clockwise rotation of a 2x2 sub-grid specified by it's
     * top-left tile number.
     */
    public boolean rotate(int tileNumber) {
        int[] lo = location(tileNumber);
        if (lo[2] == 0) return false;

        int x = lo[0];
        int y = lo[1];
        if (y == board.length - 1 || x == board[0].length - 1) return false;

        int topLeft = board[y][x];
        int topRight = board[y][x + 1];
        int bottomRight = board[y + 1][x + 1];
        int bottomLeft = board[y + 1][x];

        board[y][x] = bottomLeft;
        board[y][x + 1] = topLeft;
        board[y + 1][x + 1] = topRight;
        board[y + 1][x] = bottomRight;

        return true;
    }

    /**
     * Returns the current game state.
     */
    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        int maxDigits = digits(board.length * board[0].length); // board is always rectangular
        int row = 0;
        int col = 0;
        for (int[] arr : board) {
            for (int i : arr) {
                r.append(spaces(maxDigits - digits(i)));
                r.append(i);
                if (++col != board[0].length) r.append(" ");
            }
            col = 0;
            if (++row != board.length) r.append("\n");
        }
        return r.toString();
    }

    private void incrementBoard() {
        int in = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ++in;
            }
        }
    }

    private int digits(int num) {
        int r = 0;
        while (num > 0) {
            num /= 10;
            r++;
        }
        return r;
    }

    private String spaces(int length) {
        StringBuilder r = new StringBuilder();
        while (length > 0) {
            r.append("0");
            length--;
        }
        return r.toString();
    }

    private int[] location(int tileNumber) {
        int[] lo = new int[3]; // x, y, check bit
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (tileNumber == board[i][j]) {
                    lo[0] = j; // x
                    lo[1] = i; // y
                    lo[2] = 1; // check bit
                }
            }
        }
        return lo;
    }
}
