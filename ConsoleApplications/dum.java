package Misc.ConsoleApplications;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Jacob Swineford
 */
public class dum extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new StackPane();
        final int SCENE_WIDTH = 1000;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.TRANSPARENT);

        Circle c1 = new Circle(100);
        c1.setTranslateX(-400);
        Circle c2 = new Circle(100);
        c2.setTranslateX(-200);
        Circle c3 = new Circle(100);
        c3.setTranslateX(0);
        Circle c4 = new Circle(100);
        c4.setTranslateX(200);
        Circle c5 = new Circle(100);
        c5.setTranslateX(400);

        // Linear / Radial
        Stop[] stops = new Stop[2];
        stops[0] = new Stop(0, Color.WHITESMOKE);
        stops[1] = new Stop(1, Color.BLACK);

        LinearGradient lG = new LinearGradient(0, 0, 1, 1, true,
                CycleMethod.NO_CYCLE, stops);
        c1.setFill(lG);

        RadialGradient rG = new RadialGradient(0, 0, .5, .5, .45, true,
                CycleMethod.NO_CYCLE, stops);
        c2.setFill(rG);

        // fill
        FillTransition ft = new FillTransition(Duration.millis(3000), c3, Color.RED, Color.BLUE);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();

        // path
        Line l = new Line(200, 0, 200, -200);
        l.setStroke(Color.BLACK);
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(3000));
        pt.setPath(l);
        pt.setNode(c4);
        pt.setInterpolator(Interpolator.LINEAR);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        pt.play();

        // rotate
        Rectangle rect = new Rectangle (100, 40, 100, 100);
        rect.setArcHeight(50);
        rect.setArcWidth(50);
        rect.setFill(Color.VIOLET);

        RotateTransition rt = new RotateTransition(Duration.millis(1000), rect);
        rt.setByAngle(120);
        rt.setCycleCount(1);
        rt.setInterpolator(Interpolator.LINEAR); // allows for single-direction movement
        rt.play();

        // scale
        ScaleTransition st = new ScaleTransition(Duration.millis(1000), c5);
        st.setByX(1); // proportional
        st.setByY(1); // proportional
        st.setToX(2);
        st.setCycleCount(Timeline.INDEFINITE);
        st.setAutoReverse(true);
        st.play();

        root.getChildren().addAll(l, c1, c2, c3, c4, c5, rect);

        // handler
        root.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY:
                    rt.pause();
                    st.pause();
                    pt.pause();
                    break;
                case SECONDARY:
                    rt.play();
                    st.play();
                    pt.play();
                    break;
            }
        });

        primaryStage.setTitle("Title");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
