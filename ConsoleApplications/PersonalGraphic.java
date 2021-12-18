package Misc.ConsoleApplications;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class PersonalGraphic extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 500;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.DARKRED);

        // make the crazy eyes of death
        Circle circle = new Circle(200, 150, 50); //center x, center y, radius
        circle.setFill(Color.BLUEVIOLET);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(5);

        Circle circle2 = new Circle(225, 150, 50);
        circle2.setFill(Color.BLUE);
        circle2.setStroke(Color.BROWN);
        circle2.setStrokeWidth(5);


        //make the mouth of demise
        Arc arc = new Arc();
        arc.setCenterX(250.0); //origin x
        arc.setCenterY(150.0); //origin y
        arc.setRadiusX(50.0); //radius of the arc along the x axis
        arc.setRadiusY(50.0); //radius of the arc along the y axis
        arc.setStartAngle(0.0); //sets the beginning position of the x axis for the .setlength method
        arc.setLength(350.0); //sets the angle of which the circle is filled in
        arc.setType(ArcType.ROUND);

        root.getChildren().addAll(arc, circle2, circle); //node goes one layer on top of the one before
        primaryStage.setTitle("Personal Graphic");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
