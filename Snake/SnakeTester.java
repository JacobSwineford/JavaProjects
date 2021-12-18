package Misc.CustomClasses.Snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Random;

/**
 * SNEK
 * @author Jacob Swineford
 */
public class SnakeTester extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final double SCENE_WIDTH = 1500;
        final double SCENE_HEIGHT = 900;
        int xr = 200;
        int yr = 200;
        int snakeAmount = 200;
        int snakeLength = 10;
        double rWidth = SCENE_WIDTH / xr;
        double rHeight = SCENE_HEIGHT / yr;

        class Playground {

            private Pane root;
            private Rectangle[][] ground;
            private Random rand = new Random();

            class Snake {

                private int direction = 1; // 0 -> up, 1 -> right, 2 -> down, 3 -> left
                private LinkedList<P2D> location;
                private Color snakeColor;

                /**
                 * This class should not be called by itself, an instance of it is initialized
                 * with the Playground class. If this is not so, then this constructor will throw
                 * a NullPointerException.
                 * 1. length does not exceed ground area (X * Y)
                 * 2. Points are adjacent to each other
                 * 3. list is initialized from tail to head
                 */
                Snake(LinkedList<P2D> location, Color snakeColor) throws NullPointerException {
                    this.location = location;
                    this.snakeColor = snakeColor;
                    if (ground[0][0] == null) {
                        throw new NullPointerException("Ground must be initialized" +
                                "in the Playground class");
                    }
                    setSnake();
                    Thread t = new DirectionThread();
                    t.start();
                }

                LinkedList<P2D> getLocation() {return location;}
                private void setSnake() {
                    for (P2D p : location)
                        ground[(int) p.getY()][(int) p.getX()].setFill(snakeColor);
                    KeyFrame kf = new KeyFrame(Duration.millis(10), event -> {
                        updateLocation();
                        for (P2D p : location)
                            ground[(int) p.getY()][(int) p.getX()].setFill(snakeColor);
                    });
                    Timeline t = new Timeline(kf);
                    t.setCycleCount(Timeline.INDEFINITE);
                    t.play();
                }
                private void updateLocation() {
                    // bouncing off boundaries, last is head, first is tail
                    ground[(int) location.getFirst().getY()][(int) location.getFirst().getX()].setFill(Color.TRANSPARENT);
                    if (location.getLast().getY() == 0 && direction == 0) {direction = 2;}
                    if (location.getLast().getY() == ground.length - 1 && direction == 2) {direction = 0;}
                    if (location.getLast().getX() == 0 && direction == 3) {direction = 1;}
                    if (location.getLast().getX() == ground[0].length - 1 && direction == 1) {direction = 3;}
                    if (direction == 0) { // up
                        for (int i = 0; i < location.size(); i++) {
                            if (i == location.size() - 1) {
                                location.getLast().setLocation(location.getLast().getX(),
                                        location.getLast().getY() - 1);
                                return;
                            }
                            double x = location.get(i + 1).getX();
                            double y = location.get(i + 1).getY();
                            location.get(i).setLocation(x, y);
                        }
                    }
                    if (direction == 1) { // right
                        for (int i = 0; i < location.size(); i++) {
                            if (i == location.size() - 1) {
                                location.getLast().setLocation(location.getLast().getX() + 1,
                                        location.getLast().getY());
                                return;
                            }
                            double x = location.get(i + 1).getX();
                            double y = location.get(i + 1).getY();
                            location.get(i).setLocation(x, y);
                        }
                    }
                    if (direction == 2) { // down
                        for (int i = 0; i < location.size(); i++) {
                            if (i == location.size() - 1) {
                                location.getLast().setLocation(location.getLast().getX(),
                                        location.getLast().getY() + 1);
                                return;
                            }
                            double x = location.get(i + 1).getX();
                            double y = location.get(i + 1).getY();
                            location.get(i).setLocation(x, y);
                        }
                    }
                    if (direction == 3) { // left
                        for (int i = 0; i < location.size(); i++) {
                            if (i == location.size() - 1) {
                                location.getLast().setLocation(location.getLast().getX() - 1,
                                        location.getLast().getY());
                                return;
                            }
                            double x = location.get(i + 1).getX();
                            double y = location.get(i + 1).getY();
                            location.get(i).setLocation(x, y);
                        }
                    }
                }

                class DirectionThread extends Thread {

                    DirectionThread(){}

                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {break;}
                            direction = rand.nextInt(4);
                        }
                    }
                }
            }
            private LinkedList<Snake> snakes;

            /**
             * Constructor for this game of Snake. This constructor will initialize
             * the snake, it's ground and a root pane that that may serve as
             * an easy-to-use GunGame. The same contracts
             * that apply to the snake constructor apply to this constructor:
             * 1. length does not exceed ground area (X * Y)
             * 2. Points are adjacent to each other
             */
            private Playground(LinkedList<LinkedList<P2D>> locations, int x, int y, double rWidth, double rHeight) {
                ground = new Rectangle[y][x];
                root = new Pane();
                setGround(rWidth, rHeight, x);
                snakes = new LinkedList<>();
                for (LinkedList<P2D> location : locations) {
                    snakes.add(new Snake(location, Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))));
                }
            }

            Pane getRoot() {return root;}
            private void setGround(double rWidth, double rHeight, int x) {
                int xd = 0;
                int yd = 0;
                for (int i = 0; i < ground.length; i++) {
                    for (int j = 0; j < ground[0].length; j++) {
                        ground[i][j] = new Rectangle();
                        ground[i][j].setHeight(rHeight);
                        ground[i][j].setWidth(rWidth);
                        ground[i][j].setX(xd * rWidth);
                        ground[i][j].setY(yd * rHeight);
                        ground[i][j].setFill(Color.TRANSPARENT);
                        ground[i][j].setStroke(Color.BLACK);
                        ground[i][j].setStrokeWidth(0);
                        root.getChildren().add(ground[i][j]);
                        if (xd == x - 1) {xd = 0; yd++;} else {xd++;}
                    }
                }
                System.out.println("y length: " + ground.length);
                System.out.println("x length: " + ground[4].length);
            }
            private void setDirection(int i) {
                for (Snake s : snakes) {
                    s.direction = i;
                }
            }
        }

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        LinkedList<LinkedList<P2D>> locations = snakes(snakeAmount, snakeLength);

        Playground sg = new Playground(locations, xr, yr, rWidth, rHeight);
        scene.setRoot(sg.getRoot());

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP: sg.setDirection(0); break;
                case RIGHT: sg.setDirection(1); break;
                case DOWN: sg.setDirection(2); break;
                case LEFT: sg.setDirection(3); break;
            }
        });

        primaryStage.setTitle("Snek");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static LinkedList<LinkedList<P2D>> snakes(int amount, int length) {
        LinkedList<LinkedList<P2D>> l = new LinkedList<>();
        for (int i = 0; i < amount; i++) {
            LinkedList<P2D> lo = new LinkedList<>();
            for (int j = 0; j < length; j++) {
                lo.add(new P2D(30, 30));
            }
            l.add(lo);
        }
        return l;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
