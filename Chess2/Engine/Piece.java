package Misc.CustomClasses.Chess2.Engine;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.LinkedList;

import static Misc.CustomClasses.Chess2.Engine.Chess.*;

/**
 * Class that represents a piece in the game of Chess.
 *
 * @author Jacob Swineford
 */
public abstract class Piece {
    private Color color;
    private ImageView image;
    private int moves;

    Piece(Color color) {
        this.color = color;
        this.image = imageOf(this);
    }

    public void setColor(Color color) {this.color = color;}
    public Color getColor() {return color;}

    public void setImage(ImageView image) {this.image = image;}
    public ImageView getImage() {return image;}

    public int getMoves() {return moves;}
    void incrementMoves() {moves += 1;}

    public abstract LinkedList<LinkedList<int[]>> getActions();

    private ImageView imageOf(Piece piece) {
        String url = "images/";
        Color c = piece.getColor();
        if (piece instanceof Chess.Pawn) {
            if (c == colors[0]) url += "pawn.png";
            else url += "whitePawn.png";
        } else if (piece instanceof Chess.Knight) {
            if (c == colors[0]) url += "knight.png";
            else url += "whiteKnight.png";
        } else if (piece instanceof Chess.Queen) {
            if (c == colors[0]) url += "queen.png";
            else url += "whiteQueen.png";
        } else if (piece instanceof Chess.King) {
            if (c == colors[0]) url += "king.png";
            else url += "whiteKing.png";
        } else if (piece instanceof Chess.Bishop) {
            if (c == colors[0]) url += "bishop.png";
            else if (c == colors[1]) url += "whiteBishop.png";
        } else if (piece instanceof Chess.Rook) {
            if (c == colors[0]) url += "rook.png";
            else if (c == colors[1]) url += "whiteRook.png";
        }
        return new ImageView(url);
    }
}
