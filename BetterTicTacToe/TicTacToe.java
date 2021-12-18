package Misc.CustomClasses.BetterTicTacToe;

import java.util.LinkedList;

/**
 * The game of TicTacToe. Players begin by taking turns putting their symbol
 * on some part of the board. whoever has a sequence of 3 symbols wins the game.
 *
 * @author Jacob Swineford
 */
public class TicTacToe {

    private String[][] board;

    TicTacToe(int width) {
        board = new String[width][width];
    }

    /**
     * Places a value at the specified location.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param value value to insert
     */
    void place(int x, int y, String value) {
        board[y][x] = value;
    }

    /**
     * Checks to see if this TicTacToe board has a winning sequence.
     * A winning sequence is a sequence of all the same type that
     * has a length equal to the width of the board.
     *
     * @param checkFor value to check for a winning sequence
     * @return <code>LinkedList</code> containing the individual coordinates
     *         for each index in the winning sequence in the form {x, y}.
     *         if there is no match, then null
     */
    LinkedList<int[]> hasSequence(String checkFor) {
        LinkedList<int[]> points;

        // vertical checks
        for (int i = 0; i < board.length; i++) {
            points = biasCheck(i, 0, new int[] {0, 1}, checkFor);
            if (points.size() == board.length) return points;
        }

        // horizontal checks
        for (int i = 0; i < board.length; i++) {
            points = biasCheck(0, i, new int[] {1, 0}, checkFor);
            if (points.size() == board.length) return points;
        }

        // diagonal checks
        points = biasCheck(0, 0, new int[] {1, 1}, checkFor);
        if (points.size() == board.length) return points;
        points = biasCheck(0, board.length - 1, new int[] {1, -1}, checkFor);
        if (points.size() == board.length) return points;

        return null;
    }

    private LinkedList<int[]> biasCheck(int x, int y, int[] bias, String checkFor) {
        LinkedList<int[]> points = new LinkedList<>();
        int biasX = bias[0];
        int biasY = bias[1];

        // from the point, proceed with the bias x and y until
        // the check fails. At this point, return the list of points.
        while (true) {
            try {
                if (board[y][x] != null && board[y][x].equals(checkFor)) {
                    points.add(new int[] {x, y});
                    x += biasX;
                    y += biasY;
                } else {
                    break; // break if there is no match
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return points;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String[] arr : board) {
            StringBuilder row = new StringBuilder();
            for (String str : arr) {
                String a = str + ' ';
                row.append(a);
            }
            sb.append(row.toString().trim());
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TicTacToe t = new TicTacToe(5);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == j) t.place(j, i, "x");
            }
        }
        System.out.print(t);
        LinkedList<int[]> c = t.hasSequence("x");
        System.out.print("[");
        for (int[] arr : c) {
            int x = arr[0];
            int y = arr[1];
            System.out.print("{" + x + ", " + y + "} ");
        }
        System.out.println("]");
    }
}
