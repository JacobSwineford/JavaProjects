package Misc.CustomClasses.BetterCypher;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class CipherDisk extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        double radius = SCENE_WIDTH / 3.0;
        double radius2 =  SCENE_WIDTH / 5.0;

//        Pane p = new Pane();
//        p.setMinWidth(radius * 2);
//        p.setMinHeight(radius * 2);
//        p.setTranslateX((SCENE_WIDTH - p.getMinWidth()) / 2);
//        p.setTranslateY((SCENE_HEIGHT - p.getMinHeight()) / 2);
//        p.setStyle("-fx-frame-color: rgb(100, 100, 0, 1);");
//        Circle outer = new Circle();
//        outer.setRadius(radius);
//        outer.setCenterX(radius);
//        outer.setCenterY(radius);
//        outer.setStroke(Color.BLACK);
//        outer.setFill(Color.ORANGE);
//        outer.setOnMouseClicked(event -> System.out.println("outer clicked"));
//        p.getChildren().add(outer);
//
//        Pane p2 = new Pane();
//        p2.setMinWidth(radius2 * 2);
//        p2.setMinHeight(radius2 * 2);
//        System.out.println(p2.getMinWidth());
//        System.out.println(p2.getMinHeight());
//        p2.setTranslateX((SCENE_WIDTH - p2.getMinWidth()) / 2);
//        p2.setTranslateY((SCENE_HEIGHT - p2.getMinHeight()) / 2);
//        p2.setStyle("-fx-frame-color: rgb(100, 100, 100, 1);");
//        p2.setMouseTransparent(true);
//        Circle inner = new Circle();
//        inner.setRadius(radius2);
//        inner.setCenterX(radius2);
//        inner.setCenterY(radius2);
//        inner.setStroke(Color.BLACK);
//        inner.setFill(Color.VIOLET);
//        inner.setOnMouseClicked(event -> System.out.println("inner clicked"));
//        p2.getChildren().add(inner);

        root.setStyle("-fx-frame-color: rgb(0, 100, 100, 1);");
//        root.getChildren().addAll(p, p2);

        TransparentPane p3 = new TransparentPane(radius * 2, radius * 2);
        p3.setTranslateX((SCENE_WIDTH - radius * 2) / 2);
        p3.setTranslateY((SCENE_HEIGHT - radius * 2) / 2);
        Circle c = new Circle(radius);
        c.setFill(Color.DARKGRAY);
        c.setCenterX(radius);
        c.setCenterY(radius);
        c.setOnMouseClicked(event -> System.out.println("big clicked"));
        p3.getChildren().add(c);

        TransparentPane p4 = new TransparentPane(radius2 * 2, radius2 * 2);
        p4.setTranslateX((SCENE_WIDTH - radius2 * 2) / 2);
        p4.setTranslateY((SCENE_HEIGHT - radius2 * 2) / 2);
        p4.setMouseTransparent(true);
        Circle c2 = new Circle(radius2);
        c2.setFill(Color.BLACK);
        c2.setCenterX(radius2);
        c2.setCenterY(radius2);
        c2.setOnMouseClicked(event -> System.out.println("small clicked"));
        p4.getChildren().add(c2);

        root.getChildren().add(p3);
        root.getChildren().add(p4);

        primaryStage.setTitle("Disk");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    class TransparentPane extends Pane {
        Rectangle frame;

        TransparentPane(double width, double height) {
//            frame = new Rectangle(width, height);
//            frame.setFill(Color.TRANSPARENT);
//            frame.setMouseTransparent(true);
//            frame.setStroke(Color.BLACK);
//            this.getChildren().add(frame);
//            this.setMinHeight(height); this.setMaxHeight(height);
//            this.setMinWidth(width); this.setMinWidth(width);
            this.setStyle("-fx-background-color: rgba(100, 0, 100, 0);");
        }
    }

    class SpiderCircle {

    }

    public static void main(String[] args) {
        launch(args);
    }


}
