package Misc.CustomClasses.BetterSnake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.PINK);
        int boardX = 40;
        int boardY = 40;
        int snakeLength = 5;
        int[] start = new int[] {5, 5};
        int[] start2 = new int[] {8, 6};
        int tickDuration = 50;
        int fruitAmount = 10;
        Color color = Color.BLUE;
        SnakeGame game = new SnakeGame(boardX, boardY, snakeLength, start, start2, color);
        game.makeGame(SCENE_WIDTH / boardX, SCENE_HEIGHT / boardY, fruitAmount);
        root.getChildren().add(game);

        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            Snake snake = game.getSnake();
            Snake snake2 = game.getSnake2();
            switch (key) {
                case D: {snake.setMoveDirection(Direction.Right) ;break;}
                case W: {snake.setMoveDirection(Direction.Up); break;}
                case A: {snake.setMoveDirection(Direction.Left); break;}
                case S: {snake.setMoveDirection(Direction.Down); break;}

                case L: {snake2.setMoveDirection(Direction.Right); break;}
                case I: {snake2.setMoveDirection(Direction.Up); break;}
                case J: {snake2.setMoveDirection(Direction.Left); break;}
                case K: {snake2.setMoveDirection(Direction.Down); break;}

                case SPACE: {game.startGame(tickDuration);}
            }
        });

        primaryStage.setTitle("Snake");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
