package Misc.CustomClasses.BetterMinesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Jacob Swineford
 */
public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final double SCENE_WIDTH = 800;
        final double SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        Minesweeper m = new Minesweeper(16, 16, 40);
        double boardWidth = 600;
        double boardHeight = 600;
        Pane board = m.getBoard(boardWidth, boardHeight);
        board.setTranslateX((SCENE_WIDTH - boardWidth) / 2);
        board.setTranslateY((SCENE_HEIGHT - boardHeight) / 2);
        root.getChildren().add(board);

        primaryStage.setTitle("Minesweeper");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
