package Misc.ConsoleApplications;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Algorithm for finding the best possible path in the game of
 * Rogo, given the current moves and the board.
 *
 * @author Jacob Swineford
 */
public class BestPathAlgorithm {

    private static int[][] grid = new int[][] {
            {5, 0, 0, 5, 0, 0, 4},
            {0, 8, -1, 0, 0, -1, 0},
            {0, 0, 2, 0, 0, 8, 0},
            {0, -1, 0, 4, 0, 0, 0},
            {4, 0, 2, 0, 2, 0, 5},
            {0, 0, 0, 5, 0, -1, 0},
            {0, 8, 0, 0, 8, 0, 0},
            {0, -1, 0, 0, -1, 4, 0},
            {5, 0, 0, 4, 0, 0, 2}};

    private static HashMap<Integer, LinkedList<int[]>> scoreMap = new HashMap<>();

    private static int anchorX = 0;
    private static int anchorY = 0;

    public static void main(String[] args) {
        int numMoves = grid.length * grid[0].length;
        printCycles(anchorX, anchorY, numMoves, grid[anchorY][anchorX]);
        System.out.println("The max score is " + maxScore);

    }

    private static LinkedList<int[]> bestPath(
            LinkedList<int[]> path, int numMoves, int score, int x, int y) {
        return null;
    }

    private static int maxScore = 0;
    private static int counter;
    private static LinkedList<int[]> printingPath = new LinkedList<>();
    private static void printCycles(int x, int y, int numMoves, int score) {
        if (numMoves == -1) return;
        if (adjacentToAnchor(x, y) && printingPath.size() > 1) {
            if (score > maxScore) maxScore = score;
            System.out.println(++counter + ": " + pathString(printingPath, score));
            return;
        }

        LinkedList<int[]> validMoves = validMoves(x, y);
        for (int[] arr : validMoves) {
            int moveX = arr[0];
            int moveY = arr[1];
            int[] coor = new int[] {moveX, moveY};
            printingPath.add(coor);
            printCycles(moveX, moveY, numMoves - 1, score + grid[moveY][moveX]);
            printingPath.remove(coor);
        }
    }

    /**
     * Valid moves of a point on the grid above. All the moves
     * are defined to be it's adjacent tiles.
     *
     * @param x x point
     * @param y y point
     *
     * @return <code>LinkedList</code> of valid points
     */
    private static LinkedList<int[]> validMoves(int x, int y) {
        LinkedList<int[]> moves = new LinkedList<>();
        if (inRange(x + 1, y)) moves.add(new int[] {x + 1, y});
        if (inRange(x - 1, y)) moves.add(new int[] {x - 1, y});
        if (inRange(x, y + 1)) moves.add(new int[] {x, y + 1});
        if (inRange(x, y - 1)) moves.add(new int[] {x, y - 1});
        return moves;
    }

    /**
     * Returns whether a point is within range or not for the given rogo grid.
     * Note that points in the current path are not within range, neither is the
     * anchor.
     *
     * @param x x coordinate
     * @param y y coordinate
     *
     * @return true if the tile is within range, false otherwise.
     */
    private static boolean inRange(int x, int y) {
        for (int[] arr : printingPath) { // points already in the path are not within range
            if (arr[0] == x && arr[1] == y) return false;
        }
        if (x == anchorX && y == anchorY) return false; // the anchor point is not within range
        if (x > -1 && x < grid[0].length && y > -1 && y < grid.length) {
            return grid[y][x] > -1;
        }
        return false;
    }

    private static boolean adjacentToAnchor(int x, int y) {
        // left
        if (x == anchorX - 1 && y == anchorY) return true;
        // right
        if (x == anchorX + 1 && y == anchorY) return true;
        // top
        if (x == anchorX && y == anchorY - 1) return true;

        // bottom
        return x == anchorX && y == anchorY + 1;
    }

    private static String pathString(LinkedList<int[]> path, int score) {
        StringBuilder sb = new StringBuilder("{");
        int counter = 0;
        for (int[] arr : path) {
            counter++;
            int x = arr[0];
            int y = arr[1];
            String toAppend;
            if (counter == path.size()) {
                toAppend = String.format("[%d, %d]", x, y);
            } else {
                toAppend = String.format("[%d, %d], ", x, y);
            }
            sb.append(toAppend);
        }
        sb.append("}");
        String s = " Score: " + score;
        sb.append(s);
        sb.append("\n");
        // print the board and the current path
        for (int i = 0; i < grid.length; i++) {
            sb.append("\n");
            for (int j = 0; j < grid[i].length; j++) {
                boolean inPath = false;
                for (int[] coor : printingPath) {
                    if (coor[0] == j && coor[1] == i) {inPath = true; break;}
                }
                if (inPath) sb.append("&");
                else sb.append("*");
                if (j != grid[i].length - 1) sb.append(" ");
            }
        }
        sb.append("\n");

        return sb.toString();
    }
}
