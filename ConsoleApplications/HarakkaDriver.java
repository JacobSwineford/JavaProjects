package Misc.ConsoleApplications;
import java.util.Scanner;

/**
 * A front end for the Game of Harakka.
 *
 * @author Drue Coles
 */
public class HarakkaDriver {

    public static void main(String[] args) {
        System.out.println("Welcome to the Game of Harakka.");
        System.out.print("Enter the desired number of rows and columns: ");

        Scanner in = new Scanner(System.in);
        int rows = in.nextInt();
        int cols = in.nextInt();
        System.out.println();

        // Create a game that can be solved in 5 moves for easy testing purposes.
        Harakka game = new Harakka(rows, cols, 1);
        System.out.println(game);

        while (!game.isWinning()) {

            System.out.print("Enter move (or 0 to quit): ");
            int k = in.nextInt();
            System.out.println();

            if (k == 0) {
                System.out.println("Goodbye.");
                return;
            }

            // Make a move and output the new grid configuration.
            boolean validMove = game.rotate(k);
            if (validMove) {
                System.out.println(game);
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        System.out.println("You have solved the puzzle!");
    }
}

