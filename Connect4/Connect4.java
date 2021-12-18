package Misc.CustomClasses.Connect4;

import java.util.LinkedList;

/**
 * Engine for the game of Connect 4. The Goal of Connect 4 is to get 4 chips in
 * a line before the other player. This is done by taking turns dropping chips into
 * a 2D grid. Lines can be made vertically, horizontally, and diagonally
 * in all directions.
 *
 * @author Jacob Swineford
 */
class Connect4 {

    private int[][] board;

    Connect4(int width, int height) {
        board = new int[height][width];
    }

    /**
     * Simulates dropping a chip in a column in a Connect 4 board.
     *
     * @param column index of the column to drop a chip
     * @param value value to insert
     * @return the index of the row that the chip was placed in. If the value
     *         could not be placed on the board, -1 is returned.
     */
    int drop(int column, int value) {
        int row = board.length - 1;
        while (board[row][column] != 0) {
            row--;
            if (row == -1) return -1;
        }
        board[row][column] = value;
        return row;
    }

    /**
     * Resets the board to it's default value.
     */
    void reset() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * Checks for a line of four of the given value intersecting the specified
     * position.
     *
     * @param x origin x
     * @param y origin y
     * @param value given value to check for
     *
     * @return A <code>LinkedList</code> with the positions of the match,
     *         null otherwise
     */
    LinkedList<int[]> match(int x, int y, int value) {
        LinkedList<int[]> coor = new LinkedList<>();
        LinkedList<int[]> ul = crawl(x, y, value, new int[] {-1, -1});
        LinkedList<int[]> ur = crawl(x, y, value, new int[] {1, -1});
        LinkedList<int[]> dl = crawl(x, y, value, new int[] {-1, 1});
        LinkedList<int[]> dr = crawl(x, y, value, new int[] {1, 1});
        LinkedList<int[]> r = crawl(x, y, value, new int[] {1, 0});
        LinkedList<int[]> l = crawl(x, y, value, new int[] {-1, 0});
        LinkedList<int[]> u = crawl(x, y, value, new int[] {0, -1});
        LinkedList<int[]> d = crawl(x, y, value, new int[] {0, 1});
        int[] o = {x, y};

        if (ul.size() + dr.size() >= 3) {
            coor.addAll(ul);
            coor.addAll(dr);
        }

        if (ur.size() + dl.size() >= 3) {
            coor.addAll(ur);
            coor.addAll(dl);
        }

        if (r.size() + l.size() >= 3) {
            coor.addAll(r);
            coor.addAll(l);
        }

        if (u.size() + d.size() >= 3) {
            coor.addAll(u);
            coor.addAll(d);
        }

        if (coor.size() > 0) {
            coor.add(o);
            return coor;
        } else return null;
    }

    /**
     * Does a crawl of this Connect 4 board based the the starting position
     * and a given bias value. This crawl specifically does not include the
     * origin position in it's returned list, and stops once the target value is
     * not found.
     *
     * @param x origin x
     * @param y origin y
     * @param value given value to check for
     * @param bias the given bias to use in the crawl
     *
     * @return a <code>LinkedList</code> with the positions matching the specifications
     *         of this crawl
     */
    private LinkedList<int[]> crawl(int x, int y, int value, int[] bias) {
        LinkedList<int[]> pos = new LinkedList<>();
        int xb = bias[0];
        int yb = bias[1];
        x += xb;
        y += yb;
        try {board[y][x] = board[y][x];}
        catch (IndexOutOfBoundsException e) {return pos;}
        while (board[y][x] == value) {
            pos.add(new int[] {x, y});
            x += xb;
            y += yb;

            try {board[y][x] = board[y][x];}
            catch (IndexOutOfBoundsException e) {break;}
        }
        return pos;
    }
}
