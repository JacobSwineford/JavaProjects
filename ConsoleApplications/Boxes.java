package Misc.ConsoleApplications;

import Misc.Utilities.ArrayUtil;
import javafx.geometry.Point2D;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class Boxes {

    private Character[][] field;
    private ArrayList<Pair<Point2D, Point2D>> redLines;
    private ArrayList<Pair<Point2D, Point2D>> greenLines;
    private int turn = 1; // 1 is red, -1 is green
    private char turnChar = 'G';

    private Boxes(int width, int height) {
        field = new Character[(height * 2) - 1][(width * 2) - 1];
        redLines = new ArrayList<>();
        greenLines = new ArrayList<>();
        init();
    }

    private void init() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                boolean ie = isEven(i);
                boolean je = isEven(j);
                if (ie && je) field[i][j] = '*'; // stars are y-even and x-even
                else if (!ie && je) field[i][j] = '|'; // vertical lines are y-odd and x-even
                else if (ie) field[i][j] = '-'; // horizontal lines are y-even and x-odd
                else field[i][j] = ' '; // spaces are y-odd and x-odd
            }
        }
    }

    private boolean makeMove(int x, int y, String com) {
        if (!ArrayUtil.withinRange(x, y, field)) return false;
        try {
            if (com.equals("left")) field[y][x-1] = turnChar;
            if (com.equals("right")) field[y][x+1] = turnChar;
            if (com.equals("up")) field[y-1][x] = turnChar;
            if (com.equals("down")) field[y+1][x] = turnChar;
        } catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
        turn = -turn;
        if (turnChar == 'G') turnChar = 'R';
        else turnChar = 'G';
        return true;
    }

    private boolean isEven(int i) {
        return i % 2 == 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(cap());
        int count = 0;
        for (Character[] arr : field) {
            if (isEven(count)) str.append(count);
            else str.append(' ');
            str.append(' ');
            for (char c : arr) {
                str.append(c);
                str.append(' ');
            }
            str.append('\n');
            count++;
        }
        return str.toString();
    }

    private String cap() {
        StringBuilder str = new StringBuilder();
        int count = 0;
        for (int i = 0; i < field[0].length + 2; i++) {
            if (!isEven(count)) str.append(count - 1);
            else str.append(' ');
            str.append(' ');
            count++;
        }
        str.append('\n');
        return str.toString();
    }

    public int getTurn() {
        if (turn == -1) return 2;
        return turn;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the dimensions of the field (x, y): ");
        Boxes b = new Boxes(in.nextInt(), in.nextInt());
        while (true) {
            System.out.println(b);
            System.out.print("Please enter your move player " + b.getTurn() + "! (x, y [right, left, up, down]): ");
            int x = in.nextInt(); int y = in.nextInt();
            String com = in.next();
            if (!b.makeMove(x, y, com))
                System.out.println("Something went wrong! please try again!");
            System.out.println();
        }
    }
}
