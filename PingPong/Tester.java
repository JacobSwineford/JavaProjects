package Misc.CustomClasses.PingPong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class Tester extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 0;
        final int SCENE_HEIGHT = 0;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        Pane p = new Pane();
        Rectangle r = new Rectangle(0, 0, 500, 500);
        r.setFill(Color.BLACK);
        p.getChildren().add(r);
        Circle c = new Circle(50);
        c.setFill(Color.BLUE);
        p.getChildren().add(c);

        p.setTranslateX(100);
        root.getChildren().add(p);

        primaryStage.setTitle("Title");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
