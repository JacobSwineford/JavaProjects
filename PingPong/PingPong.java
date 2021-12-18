package Misc.CustomClasses.PingPong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

/**
 * @author Jacob Swineford
 */
public class PingPong extends Application {

    @Override
    public void start(Stage primaryStage) {

        Random r = new Random();
        Pane root = new Pane();
        final int SCENE_WIDTH = 1000;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.BEIGE);

        class Bumper extends Rectangle implements Runnable {

            private Point2D[] hitNodes;
            private Circle[] circleNodes;
            private boolean hitFlash = false;

            private Bumper(double x, double y, double width, double height, int dw, int dh)
            {
                super(x, y, width, height);
                super.setFill(Color.BLACK);
                hitNodes = new Point2D[(dw) * (dh)];
                circleNodes = new Circle[(dw) * (dh)];
                updateHitNodes(dw, dh);
                updateCircleNodes();
                super.setOnMouseDragged(event ->
                {
                    super.setX(event.getX());
                    super.setY(event.getY());
                    updateHitNodes(dw, dh);
                    updateCircleNodes();
                });
            }

            private void updateHitNodes(int dw, int dh)
            {
                double dx = 0;
                double dy = 0;
                int index = 0;
                for (double i = 0; i < dh ; i++)
                {
                    for (double j = 0; j < dw; j++)
                    {
                        hitNodes[index] = new Point2D(super.getX() + dx, super.getY() + dy);
                        dx += super.getWidth() / (dw - 1);
                        index++;
                    }
                    dx = 0;
                    dy += super.getHeight() / (dh - 1);
                }
            }

            private void updateCircleNodes() {
                for (int i = 0; i < hitNodes.length; i++)
                {
                    circleNodes[i] = new Circle(3);
                    circleNodes[i].setCenterX(hitNodes[i].getX());
                    circleNodes[i].setCenterY(hitNodes[i].getY());
                    circleNodes[i].setFill(Color.TRANSPARENT);
                }
            }

            private void showCircleNodes() {
                for (Circle c : circleNodes) c.setFill(Color.DARKGREEN);
            }

            private void hideCircleNodes() {
                for (Circle c : circleNodes) c.setFill(Color.TRANSPARENT);
            }

            private void addCircleNodes(){for (Circle c : circleNodes) root.getChildren().add(c);}

            private Point2D[] getHitNodes() {return hitNodes;}

            @Override
            public void run() {
                while (true)
                {
                    if (hitFlash)
                    {
                        super.setFill(Color.DARKRED);
                        showCircleNodes();
                        try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
                        super.setFill(Color.BLACK);
                        hideCircleNodes();
                        hitFlash=false;
                    }
                }
            }

            private void setHitFlash(boolean b) {hitFlash = b;}
        }

        class Ball extends Circle {

            private double xd;
            private double yd;
            private Scene scene;

            private Ball(double x, double y, double r, Scene s, Bumper... bs)
            {
                super(x, y, r); xd = 5; yd = -5;
                scene = s;
                KeyFrame kf = new KeyFrame(Duration.millis(10), event ->
                {
                    bouncing(bs);
                    super.setCenterX(super.getCenterX() + xd);
                    super.setCenterY(super.getCenterY() + yd);
                });
                Timeline t = new Timeline(kf);
                t.setCycleCount(Timeline.INDEFINITE);
                t.play();
            }

            private void bouncing(Bumper[] bs)
            {
                if (super.getCenterY() + super.getRadius() >= scene.getHeight())
                {
                    yd = -yd;
                    return;
                }

                if (super.getCenterY() - super.getRadius() <= 0)
                {
                    yd = -yd;
                    return;
                }

                if (super.getCenterX() + super.getRadius() >= scene.getWidth())
                {
                    xd = -xd;
                    return;
                }

                if (super.getCenterX() - super.getRadius() <= 0)
                {
                    xd = -xd;
                    return;
                }

                for (Bumper b : bs)
                {
                    for (Point2D p : b.getHitNodes())
                    {
                        if (super.contains(p))
                        {
                            b.setHitFlash(true);
                            xd = -xd;
                            return;
                        }
                    }
                }
            }
        }

        int dw = 2;
        int dh = 100;
        Bumper b1 = new Bumper(20, 0, 50, 1000, dw, dh);
        Bumper b2 = new Bumper(SCENE_WIDTH - 70, 0, 50, 1000, dw, dh);
        root.getChildren().addAll(b1, b2);
        b1.addCircleNodes();
        b2.addCircleNodes();
        for (int i = 0; i < 150; i++)
        {
            double x = r.nextInt(SCENE_WIDTH);
            double y = r.nextInt(SCENE_HEIGHT);
            Ball ball = new Ball(x, y, 20, scene, b1, b2);
            root.getChildren().add(ball);
        }
        Thread t1 = new Thread(b1);
        Thread t2 = new Thread(b2);
        t1.start();
        t2.start();

        scene.setOnKeyPressed(event ->
        {
            KeyCode code = event.getCode();
            if (code == KeyCode.W)
            {
                b1.setY(b1.getY() - 30);
                b2.setY(b2.getY() - 30);
            }

            if (code == KeyCode.S)
            {
                b1.setY(b1.getY() + 30);
                b2.setY(b2.getY() + 30);
            }
        });

        primaryStage.setTitle("Ping-Pong");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}




