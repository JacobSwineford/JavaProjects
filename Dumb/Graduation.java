package Misc.CustomClasses.Dumb;

import Misc.CustomClasses.Menu.Direction;
import Misc.CustomClasses.Menu.Item;
import Misc.CustomClasses.Menu.Menu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jacob Swineford
 */
public class Graduation extends Application {

    private static TBox[] boxes;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 1500;
        final int SCENE_HEIGHT = 900;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.GRAY);

        String message = "Nice Job on graduating you guys! I hope you both continue to be" +
                " successful, and make sure to keep in touch with us non - graduated people!";
        String[] words = message.split(" ");
        boxes = new TBox[words.length];

        int itemsPerRow = 7;
        double fontSize = 60;
        Menu<TBox> menu = new Menu<>(itemsPerRow, false, false);
        boolean none = true;
        int count = 0;
        for (String word : words) {
            TBox toAdd = new TBox(" " + word + " ", fontSize);
            menu.add(toAdd);
            if (none){
                menu.select(toAdd);
                none = false;
            }
            boxes[count++] = toAdd;
        }

        menu.setTranslateY(100);
        menu.setTranslateX(100);
        root.getChildren().add(menu);

        Rectangle color = new Rectangle(200, 200);
        color.setY(600);
        color.setX(600);
        color.setFill(Color.GREEN);
        color.setStroke(Color.BLACK);
        color.setStrokeWidth(5);
        color.setOnMouseClicked(event -> {
            class Col extends Thread {
                @Override
                public void run() {
                    while (true) {
                        try {
                            for (TBox box : boxes) {
                                box.getTBoxBackground().setFill(randomColor());
                            }
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
            }
            Thread c = new Col();
            c.start();
        });
        root.getChildren().add(color);

        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            switch (key) {
                case D: menu.navigate(Direction.Right); break;
                case A: menu.navigate(Direction.Left); break;
                case SPACE: menu.executeSelected(); break;
            }
        });


        primaryStage.setTitle("Graduation");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private static Color randomColor() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int red = rand.nextInt(256);
        int green = rand.nextInt(256);
        int blue = rand.nextInt(256);
        return Color.rgb(red, green, blue);
    }
}

class TBox extends Pane implements Item {

    private Text mes;
    private Rectangle background;

    TBox(String text, double fontSize) {
        mes = new Text(text);
        mes.setTextAlignment(TextAlignment.CENTER);
        mes.setFont(Font.font("Elephant", FontWeight.BOLD, FontPosture.REGULAR, fontSize));
        mes.setY(fontSize);
        mes.setVisible(false);
        background = new Rectangle(mes.getBoundsInParent().getWidth() + 5, fontSize + 20);
        background.setStroke(Color.RED);
        background.setStrokeWidth(2);
        background.setFill(Color.WHITESMOKE);
        this.getChildren().addAll(background, mes);
    }

    Rectangle getTBoxBackground() {return background;}

    @Override
    public void navigatedOn() {
        background.setStroke(Color.GREEN);
    }

    @Override
    public void navigatedOff() {
        background.setStroke(Color.RED);
    }

    @Override
    public void execute() {
        mes.setVisible(true);
    }
}
