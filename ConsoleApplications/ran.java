package Misc.ConsoleApplications;

import Misc.CustomClasses.Chess.Piece;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 * @author Drue Coles
 */
public class ran {

    private static int x = 2;

    public static void main(String[] args) {
        num n = new num();
        changeY(n, 6);
        System.out.println(n.getY());
    }

    private static void changeY(num n, int i) {
        n.setY(i);
    }

}

class num {
    int y = 3;
    public void setY(int n) {y = n;}
    public int getY() {return y;}
}