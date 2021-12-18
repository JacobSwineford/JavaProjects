package Misc.CustomClasses.BetterCheckers;

import java.util.LinkedList;

/**
 * @author Jacob Swineford
 */
class Checkers {

    private Checker[][] board;

    /**
     * Constructs a new Checkers object using the given size to initialize the
     * board.
     *
     * @param size width and height of the board
     */
    Checkers(int size) {
        board = new Checker[size][size];
    }

    /**
     * Gets the checker at the specified {x, y}, or null if there is no piece there.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return piece at {x, y}, null otherwise
     */
    Checker get(int x, int y) {
        return board[y][x];
    }

    /**
     * Puts a piece of the specified color on the space marked
     * with {x, y}. Note that if there is a piece already in the position
     * marked with {x, y}, then it will be overwritten.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param checker checker to put
     *
     * @return whether the piece was successfully put into place or not
     */
    boolean put(int x, int y, Checker checker) {
        try {
            board[y][x] = checker;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    /**
     * Removes a piece from the board and replaces it with an empty space.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    void remove(int x, int y) {
        put(x, y, null);
    }

    /**
     * Moves a piece from one space on the board to another. If the move cannot be
     * made because of improper coordinates, then this method does nothing.
     *
     * @param x x coordinate of starting piece
     * @param y y coordinate of starting piece
     * @param x2 x coordinate of destination
     * @param y2 y coordinate of destination
     */
    void move(int x, int y, int x2, int y2) {
        try {
            if (put(x2, y2, board[y][x])) board[y][x] = null;
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }

    /**
     * Gets a list of moves that may be made for the given chess piece.
     * a chess piece may be moved diagonal-top-left and diagonal-top-right
     * if the piece is black, and diagonal-bottom-left and diagonal-bottom-right
     * if the piece is white.
     *
     * @param x x coordinate of piece
     * @param y y coordinate of piece
     * @return a <code>LinkedList</code> of integer arrays that contain the coordinates
     *         of the given moves in the format [x, y]. If there is no piece at {x, y} or
     *         {x, y} describes a point outside of the board, then null
     */
    LinkedList<int[]> movesFor(int x, int y) {
        // ensures that there is some piece at {x, y}
        try {
            if (board[y][x] == null) return null;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        Checker c = board[y][x];
        LinkedList<int[]> moveBias = c.moveBias();
        LinkedList<int[]> moves = new LinkedList<>();
        for (int[] bias : moveBias) {
            int xm = bias[0] + x;
            int ym = bias[1] + y;
            try {
                if (board[ym][xm] == null) moves.add(new int[] {xm, ym});
            } catch (IndexOutOfBoundsException e) {
                //
            }
        }
        return moves;
    }

    /**
     * Gets a list of attacks that may be made for the given chess piece.
     * Attacks for a given piece are determined by having a piece of
     * the opposing color on one of it's diagonals, and having the second
     * diagonal of that direction be free:
     *
     *  e e e
     *  e 1 e
     *  e e 2
     *
     * Where 1 is the piece marked by {x, y} and 2 is the opposing piece. It then
     * follows that the attack is the second diagonal given that it is not occupied
     * by some other piece:
     *
     *  e e e
     *  e 1 e
     *  e e 2
     *  - - - a
     *
     * @param x x coordinate of piece
     * @param y y coordinate of piece
     * @return a <code>LinkedList</code> of integer arrays that contain the coordinates
     *         of the given attacks in the format [x, y]. If there is no piece at {x, y} or
     *         {x, y} describes a point outside of the board, then null
     */
    LinkedList<int[]> attacksFor(int x, int y) {
        // ensures that there is some piece at {x, y}
        try {
            if (board[y][x] == null) return null;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        Checker c = board[y][x];
        LinkedList<int[]> attackBias = c.attackBias();
        LinkedList<int[]> attacks = new LinkedList<>();
        for (int[] bias : attackBias) {
            int xa = bias[0] + x;
            int ya = bias[1] + y;

            int xt;
            int yt;
            if (bias[0] > 0) xt = -1;
            else xt = 1;
            if (bias[1] > 0) yt = -1;
            else yt = 1;

            try {
                if (board[ya][xa] == null && board[ya + yt][xa + xt].getType() != c.getType()) {
                    attacks.add(new int[] {xa, ya});
                }
            } catch (IndexOutOfBoundsException e) {
                //
            }
        }
        return attacks;
    }
}
