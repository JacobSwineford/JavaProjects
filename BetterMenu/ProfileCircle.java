package Misc.CustomClasses.BetterMenu;

import Misc.CustomClasses.BetterMenu.Grid.Item;
import Misc.CustomClasses.BetterMenu.LinkedImplement.LinkedMenuI;
import Misc.CustomClasses.BetterMenu.LinkedImplement.Association;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Test program for LinkedMenuI.
 *
 * @author Jacob Swineford
 */
public class ProfileCircle extends Application {

    private Text selected = new Text("Selected: N/A");
    private Text entering = new Text("Entering from: N/A");
    private Text leaving = new Text("Leaving from: N/A");

    private LinkedMenuI menu;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        double x = SCENE_WIDTH / 2.0;
        double y = SCENE_HEIGHT / 2.0;
        Color in = Color.BLUE;
        Color out = Color.RED;
        PC pc = new PC(in, out, x, y, 50, 70, root);
        PC pcRight = new PC(in, out, x + 150, y, 50, 70, root);
        PC pcLeft = new PC(in, out, x - 150, y, 50, 70, root);
        PC pcTop = new PC(in, out, x, y - 150, 50, 70, root);
        PC pcBottom = new PC(in, out, x, y + 150, 50, 70, root);
        menu = new LinkedMenuI();
        menu.setNeighbor(pc, pcRight, Direction.Right);
        menu.setNeighbor(pc, pcLeft, Direction.Left);
        menu.setNeighbor(pcRight, pc, Direction.Left);
        menu.setNeighbor(pcLeft, pc, Direction.Right);
        menu.setNeighbor(pc, pcTop, Direction.Up);
        menu.setNeighbor(pcTop, pc, Direction.Down);
        menu.setNeighbor(pc, pcBottom, Direction.Down);
        menu.setNeighbor(pcBottom, pc, Direction.Up);

        VBox v = new VBox();
        v.getChildren().addAll(selected, entering, leaving);
        v.setTranslateX(x- 100);
        v.setTranslateY(20);
        root.getChildren().add(v);

        scene.setOnMouseClicked(event -> {
            menu.select(pc);
        });

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch (code) {
                case A: menu.navigate(Direction.Left); break;
                case D: menu.navigate(Direction.Right); break;
                case W: menu.navigate(Direction.Up); break;
                case S: menu.navigate(Direction.Down); break;
            }
        });

        primaryStage.setTitle("Title");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private class PC implements Item {
        Circle inner;
        Hollow outer;
        Color oIn;
        Color oOut;

        PC(Color in, Color out, double x, double y, double radiusIn, double radiusOut, Pane root) {
            inner = new Circle(x, y, radiusIn);
            outer = new Hollow(radiusOut, radiusOut - radiusIn, out);
            outer.setTranslateX(x - radiusOut);
            outer.setTranslateY(y - radiusOut);
            inner.setFill(in);
            root.getChildren().addAll(outer, inner);
            oIn = in;
            oOut = out;
        }

        @Override
        public void navigatedOn(Direction direction) {
            inner.setFill(Color.GREEN);
            entering.setText("Entering from: " +  direction);
            selected.setText("Selected: " + this);
        }

        @Override
        public void navigatedOff(Direction direction) {
            Association a = menu.getAssociationFor(this);
            leaving.setText("Leaving from: " + direction);
            if (direction == Direction.Left && a.left == null) {
                bounce(direction);
                return;
            } else if (direction == Direction.Right && a.right == null) {
                bounce(direction);
                return;
            } else if (direction == Direction.Up && a.top == null) {
                bounce(direction);
                return;
            } else if (direction == Direction.Down && a.bottom == null) {
                bounce(direction);
                return;
            }
            inner.setFill(oIn);
        }

        @Override
        public void execute() {

        }

        private void bounce(Direction d) {
            TranslateTransition first = new TranslateTransition(Duration.millis(100));
            first.setAutoReverse(true);
            first.setCycleCount(2);

            TranslateTransition second = new TranslateTransition(Duration.millis(100));
            second.setAutoReverse(true);
            second.setCycleCount(2);

            if (d == Direction.Left) {
                first.setFromX(outer.getTranslateX());
                first.setToX(outer.getTranslateX() - 50);
                second.setFromX(outer.getTranslateX());
                second.setToX(outer.getTranslateX() + 20);
            } else if (d == Direction.Right) {
                first.setFromX(outer.getTranslateX());
                first.setToX(outer.getTranslateX() + 50);
                second.setFromX(outer.getTranslateX());
                second.setToX(outer.getTranslateX() - 20);
            } else if (d == Direction.Up) {
                first.setFromY(outer.getTranslateY());
                first.setToY(outer.getTranslateY() - 50);
                second.setFromY(outer.getTranslateY());
                second.setToY(outer.getTranslateY() + 20);
            } else { // down
                first.setFromY(outer.getTranslateY());
                first.setToY(outer.getTranslateY() + 50);
                second.setFromY(outer.getTranslateY());
                second.setToY(outer.getTranslateY() - 20);
            }

            SequentialTransition s = new SequentialTransition();
            s.setNode(outer);
            s.setCycleCount(1);
            s.getChildren().addAll(first, second);
            s.play();
        }

        private void flash(long millis, Color color) {
            class f extends Thread {
                @Override
                public void run() {
                    try {
                        inner.setFill(color);
                        Thread.sleep(millis);
                        inner.setFill(Color.GREEN);
                    } catch (Exception e) {
                        //
                    }
                }
            }
            Thread t = new f();
            t.start();
        }
    }

    private class Hollow extends Pane {

        Hollow(double radius, double thickness, Color color) {
            Circle outer = new Circle(radius, radius, radius);
            Circle inner = new Circle(radius, radius, radius - thickness);
            Rectangle b = new Rectangle(0, 0);
            b.setWidth(radius * 2);
            b.setHeight(radius * 2);
            b.setFill(Color.WHITE);
            outer.setFill(color);
            inner.setFill(Color.WHITE);
            this.getChildren().addAll(b, outer, inner);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
