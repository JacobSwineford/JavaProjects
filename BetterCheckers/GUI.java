package Misc.CustomClasses.BetterCheckers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.LinkedList;

/**
 * @author Jacob Swineford
 */
public class GUI extends Application {

    private Checkers c;
    private static int black = 1;
    private static int white = 2;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        int size = 8;
        double width = (double) SCENE_WIDTH / size;
        double height = (double) SCENE_HEIGHT / size;
        c = new Checkers(size);
        c.put(0, 0, new White());
        c.put(3, 3, new Black());

        Color one = Color.BLUEVIOLET;
        Color two = Color.ORANGERED;

        Color current = one;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double x = j * width;
                double y = i * height;
                Tile t = new Tile(current, width, height, j, i);
                t.setTranslateX(x);
                t.setTranslateY(y);
                root.getChildren().add(t);

                if (current == one) current = two;
                else current = one;
            }

            if (current == one) current = two;
            else current = one;
        }

        primaryStage.setTitle("Checkers");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    class Tile extends Pane {
        Rectangle tile;
        Circle piece;
        Checker checker;

        Tile(Color color, double width, double height, int x, int y) {
            tile = new Rectangle();
            tile.setHeight(height);
            tile.setWidth(width);
            tile.setFill(color);
            tile.setStroke(Color.LIGHTSALMON);
            tile.setStrokeWidth(3);
            this.getChildren().add(tile);
            if (c.get(x, y) != null) {
                checker = c.get(x, y);
                piece = new Circle();
                piece.setRadius(width / 2 - 10);
                piece.setCenterX(width / 2);
                piece.setCenterY(height / 2);
                piece.setStroke(Color.YELLOWGREEN);
                if (c.get(x, y).getType() == black) {
                    piece.setFill(Color.DARKGRAY.darker().darker());
                } else if (c.get(x, y).getType() == white) {
                    piece.setFill(Color.WHITESMOKE);
                }
                this.getChildren().add(piece);
            }
        }
    }

    // black on bottom
    class Black extends Checker {

        Black() {
            super(black);
        }

        @Override
        LinkedList<int[]> moveBias() {
            LinkedList<int[]> bias = new LinkedList<>();
            bias.add(new int[] {-1, -1});
            bias.add(new int[] {1, -1});
            return bias;
        }
    }

    // white on top
    class White extends Checker {

        White() {
            super(white);
        }

        @Override
        LinkedList<int[]> moveBias() {
            LinkedList<int[]> bias = new LinkedList<>();
            bias.add(new int[] {-1, 1});
            bias.add(new int[] {1, 1});
            return bias;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
