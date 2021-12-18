package Misc.CustomClasses.PingPong;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class PingPongv2 extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 1000;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        /**
         * A class designed for Ping-Pong v2. This class is essentially
         * a Rectangle with Intervals pertaining to the borders of said rectangle.
         *
         * @author Jacob Swineford
         */
        class Bumper extends Rectangle {

            Bumper(double x, double y, double w, double h)
            {
                super();
                super.setHeight(h);
                super.setWidth(w);
                super.setX(x);
                super.setY(y);
                super.setFill(Color.BLACK);

                class intervalUpdater extends Thread {
                    @Override
                    public void run() {
                        while (true)
                        {
                            // update intervals at a constant rate
                        }
                    }
                }
            }

        }

        root.getChildren().add(new Bumper(50, 50, 10, 10));

        primaryStage.setTitle("Title");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
