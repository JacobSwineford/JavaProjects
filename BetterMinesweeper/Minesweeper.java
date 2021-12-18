package Misc.CustomClasses.BetterMinesweeper;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Engine for the game of Minesweeper.
 * @author Jacob Swineford
 */
class Minesweeper {

    private Tile[][] field;

    Minesweeper(int height, int width, int mines) {
        field = new Tile[height][width];

        // init
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                field[i][j] = new Tile(j, i);

        // set mines
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        while (mines != 0) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            if (field[y][x].getValue() != -1) {
                field[y][x].setValue(-1);
                mines--;
            }
        }

        // set neighbors, values
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j].getValue() == -1) continue;

                int val = 0;
                field[i][j].setNeighbors(neighbors(j, i));
                for (Tile t : field[i][j].getNeighbors())
                    if (t.getValue() == -1) val++;
                field[i][j].setValue(val);
            }
        }

    }

    Pane getBoard(double boardWidth, double boardHeight) {
        Pane board = new Pane();
        double tileWidth = boardWidth / field[0].length;
        double tileHeight = boardHeight / field.length;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j].build(tileWidth, tileHeight);
                field[i][j].setTranslateX(tileWidth * j);
                field[i][j].setTranslateY(tileHeight * i);
                board.getChildren().add(field[i][j]);
            }
        }
        return board;
    }

    class Tile extends Pane {

        private int value;
        private int x;
        private int y;
        private LinkedList<Tile> neighbors;

        private Pane cap;
        private Rectangle flag;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void build(double tileWidth, double tileHeight) {
            Rectangle bg = new Rectangle(tileWidth, tileHeight, Color.GRAY);
            bg.setStroke(Color.BLACK);
            double strokeWidth = 2;
            bg.setStrokeWidth(strokeWidth);

            BorderPane s = new BorderPane();
            s.setMinWidth(tileWidth);
            s.setMinHeight(tileHeight);
            Label val = new Label(Integer.toString(value));
            val.setFont(Font.font(Font.getFamilies().get(3)));
            val.setStyle("-fx-stroke-width: 5;");
            val.setMouseTransparent(true);
            s.setCenter(val);

            cap = new Pane();
            Polygon left = new Polygon();
            left.getPoints().addAll(
                    strokeWidth, strokeWidth,
                    tileWidth - 2, strokeWidth,
                    strokeWidth, tileHeight - 2
            );
            left.setFill(Color.WHITE.darker());

            Polygon right = new Polygon();
            right.getPoints().addAll(
                    tileWidth - 2, tileHeight - 2,
                    strokeWidth, tileHeight - 2,
                    tileWidth - 2, strokeWidth
            );
            right.setFill(Color.DARKGRAY.darker().darker());
            double topWidth = tileWidth / 1.3;
            double topHeight = tileHeight / 1.3;
            Rectangle top = new Rectangle(topWidth, topHeight);
            top.setFill(Color.GRAY);
            top.setX((tileWidth - topWidth) / 2);
            top.setY((tileHeight - topHeight) / 2);
            cap.getChildren().addAll(left, right, top);

            double flagWidth = topWidth / 3;
            double flagHeight = topHeight / 3;
            flag = new Rectangle(flagWidth, flagHeight);
            flag.setFill(Color.RED);
            flag.setX((tileWidth - flagWidth) / 2);
            flag.setY((tileHeight - flagHeight) / 2);
            flag.setVisible(false);

            this.setOnMouseEntered(event -> top.setFill(Color.LIGHTGREEN));
            this.setOnMouseExited(event -> top.setFill(Color.GRAY));
            this.setOnMouseClicked(event -> {
                if (!cap.isVisible()) return;
                MouseButton button = event.getButton();
                switch (button) {
                    case PRIMARY: reveal(this); break;
                    case SECONDARY: flag();
                }
            });

            this.getChildren().addAll(bg, s, cap, flag);
        }

        private void reveal(Tile tile) {
            if (!tile.cap.isVisible()) return;
            tile.cap.setVisible(false);
            if (tile.getValue() != 0) return;

            for (Tile t : tile.getNeighbors()) reveal(t);
        }
        private void flag() {
            if (flag.isVisible()) flag.setVisible(false);
            else flag.setVisible(true);
        }

        void setValue(int value) {this.value = value;}
        int getValue() {return value;}
        int getX() {return x;}
        int getY() {return y;}
        void setNeighbors(LinkedList<Tile> n) {neighbors = n;}
        LinkedList<Tile> getNeighbors() {return neighbors;}
    }

    private LinkedList<Tile> neighbors(int x, int y) {
        LinkedList<Tile> l = new LinkedList<>();
        add(x, y + 1, l);
        add(x, y - 1, l);
        add(x + 1, y, l);
        add(x + 1, y + 1, l);
        add(x + 1, y - 1, l);
        add(x - 1, y, l);
        add(x - 1, y + 1, l);
        add(x - 1, y - 1, l);
        return l;
    }
    private void add(int x, int y, LinkedList<Tile> list) {
        if (x > -1 && x < field[0].length && y > -1 && y < field.length)
            list.add(field[y][x]);
    }

}
