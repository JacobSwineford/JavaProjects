package Misc.CustomClasses.Connect4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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

    private Connect4 c4;
    private Column[] columns;
    private Circle[][] chips;
    private int turn = 2; // 2 for first, -2 for second

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 900;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.PURPLE);
        int width = 7;
        int height = 6;
        double cw = 100;
        double ch = 500;
        double cr = 30;
        c4 = new Connect4(width, height);
        columns = new Column[width];
        chips = new Circle[height][width];
        Pane game = new Pane();
        for (int i = 0; i < width; i++) {
            Column col = new Column(i, cw, ch, cr, height);
            columns[i] = col;
            col.setTranslateX(i * cw);
            game.getChildren().add(col);
        }
        game.setTranslateY((SCENE_HEIGHT - ch) / 2.0);
        game.setTranslateX((SCENE_WIDTH - width  * cw) / 2.0 - 10);
        root.getChildren().add(game);

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch (code) {
                case R:
                    c4.reset();
                    for (Column c : columns)
                        c.reset();
                    turn = 2;
            }
        });

        primaryStage.setTitle("Connect 4");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    class Column extends Pane {

        private Circle[] chipsCol;

        Column(int xPos, double width, double height, double chipRadius, int numChips) {
            Rectangle bg = new Rectangle(width, height, Color.GRAY);
            bg.setStroke(Color.BLACK);
            bg.setStrokeWidth(5);
            this.getChildren().add(bg);

            chipsCol = new Circle[numChips];
            double chipX = width / 2;
            double chipY = (height / numChips) / 2;
            for (int i = 0; i < numChips; i++) {
                Circle chip = new Circle(chipRadius);
                chipsCol[i] = chip;
                chip.setFill(Color.WHITESMOKE.darker());
                chip.setStroke(Color.BLACK);
                chip.setStrokeWidth(5);
                chip.setCenterX(chipX);
                chip.setCenterY(chipY);
                chipY += (height / numChips);
                chips[i][xPos] = chip;
                this.getChildren().add(chip);
            }

            // i = y
            this.setOnMouseClicked(event -> {
                int y = c4.drop(xPos, turn);
                if (y != -1) {
                    if (turn == 2) {
                        chipsCol[y].setFill(Color.BLUE);
                    } else {
                        chipsCol[y].setFill(Color.GREEN);
                    }
                    LinkedList<int[]> m = c4.match(xPos, y, turn);
                    if (m != null) {
                        for (int[] pos : m) {
                            chips[pos[1]][pos[0]].setStroke(Color.YELLOW);
                        }
                    }
                    turn *= - 1;
                }
            });
            this.setOnMouseEntered(event -> {
                for (Circle chip : chipsCol) {
                    if (chip.getStroke() != Color.YELLOW)
                        chip.setStroke(Color.DARKGREEN);
                }

            });
            this.setOnMouseExited(event -> {
                for (Circle chip : chipsCol)
                    if (chip.getStroke() != Color.YELLOW)
                        chip.setStroke(Color.BLACK);
            });
        }

        void reset() {
            for (Circle chip : chipsCol) {
                chip.setFill(Color.WHITESMOKE.darker());
                chip.setStroke(Color.BLACK);
            }

        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
