package Misc.CustomClasses.BetterMonopoly;

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
public class test extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        double width = 400;
        double height = 400;
        Pane r = new Pane();
        r.setStyle("-fx-background-color: rgb(100, 100, 100, 1);" +
                " -fx-border-color: rgb(100, 0, 100, 1);" +
                " -fx-border-width: 5px;");
        r.setMinWidth(width);
        r.setMinHeight(height);

        // rows have two dots, columns are determined by the number of players
        LinkedList<double[]> points = playerPoints(width, height);
        for (double[] arr : points) {
            double x = arr[0];
            double y = arr[1];
            PlayerSquare p = new PlayerSquare(50, 50, Color.BLUE);
            p.setCenter(x, y);
            r.getChildren().add(p);
        }

        r.setTranslateX((SCENE_WIDTH) / 2.0);

        root.getChildren().add(r);
        primaryStage.setTitle("Title");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    // returns a list of player points based on the width and height restrictions
    // given for those points. These points could then be used to insert players.
    private LinkedList<double[]> playerPoints(double width, double height) {
        LinkedList<double[]> points = new LinkedList<>();
        int players = 10; ////////////
        int columns = 5;
        int rows = 0;

        int count = players;
        while (count > 0) {
            count -= columns;
            rows++;
        }
        double dx = width / (columns + 1);
        double dy = height / (rows + 1);
        for (int i = 1; i <= rows; i++) {
            double py = dy * i;
            for (int j = 1; j <= columns; j++) {
                if (players == 0) break;
                double px = dx * j;
                players--;
                points.add(new double[] {px, py});
            }
        }
        return points;
    }

    class PlayerSquare extends Pane {

        PlayerSquare(double width, double height, Color color) {
            Rectangle main = new Rectangle(width, height, color);
            main.setStroke(Color.BLACK);
            Circle center = new Circle(3, Color.RED);
            center.setCenterX(width / 2);
            center.setCenterY(height / 2);
            this.getChildren().addAll(main, center);
        }

        void setCenter(double x, double y) {
            this.setTranslateX(x - this.getBoundsInLocal().getWidth() / 2);
            this.setTranslateY(y - this.getBoundsInLocal().getHeight() / 2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
