package Misc.CustomClasses.BetterTicTacToe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class GUI extends Application {

    private int turn = 1;
    private TicTacToe ttt;
    private Tile[][] tiles;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final double SCENE_WIDTH = 800;
        final double SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        int height = 6;
        int width = 7;
        ttt = new TicTacToe(width);
        double tileWidth = SCENE_WIDTH / width;
        double tileHeight = SCENE_HEIGHT / height;
        tiles = new Tile[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile t = new Tile(i, j);
                t.setWidth(tileWidth);
                t.setHeight(tileHeight);
                t.setX(j * tileWidth);
                t.setY(i * tileHeight);
                tiles[i][j] = t;
                root.getChildren().add(t);
            }
        }

        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            switch (key) {
                case R: {
                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {
                            tiles[i][j].setFill(Color.GRAY);
                        }
                    }
                    turn = 1;
                }
            }
        });

        primaryStage.setTitle("TicTacToe");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class Tile extends Rectangle {

        Tile(int row, int column) {
            this.setStroke(Color.BLACK);
            this.setStrokeWidth(3);
            this.setFill(Color.GRAY);

            this.setOnMouseClicked(event -> {
                if (this.getFill() == Color.GRAY) {
                    if (turn == 1) {
                        this.setFill(Color.GREEN);
                        ttt.place(column, row, "green");
                    } else if (turn == -1) {
                        this.setFill(Color.RED);
                        ttt.place(column, row, "red");
                    }
                    turn *= -1;
                } else {
                    this.setFill(Color.GRAY);
                    ttt.place(column, row, "");
                    turn *= -1;
                }
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
