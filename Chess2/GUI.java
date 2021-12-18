package Misc.CustomClasses.Chess2;

import Misc.CustomClasses.Chess2.Engine.Chess;
import Misc.CustomClasses.Chess2.Engine.Piece;
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;

/**
 * @author Jacob Swineford
 */
public class GUI extends Application {

    private Color color1 = Color.FLORALWHITE;
    private Color color2 = Color.SLATEGRAY;
    private Color a = Color.rgb(218, 68, 72); // attack
    private Color m = Color.rgb(159, 209, 168); // movement
    private Color s = Color.rgb(94, 30, 96); // selected

    private Chess chess = new Chess();
    private Tile[][] tiles = new Tile[8][8];
    private Tile selected;
    private LinkedList<Tile> affected = new LinkedList<>();

    private Pane root = new Pane();

    private double tileW;
    private double tileH;

    // a piece is moving, disable all movements to prevent errors
    private boolean moving;

    @Override
    public void start(Stage primaryStage) {

        final double SCENE_WIDTH = 800;
        final double SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        int colorBit = 1;
        tileW = SCENE_WIDTH / 8;
        tileH = SCENE_HEIGHT / 8;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                double x = j * tileW;
                double y = i * tileH;
                Color color;
                if (colorBit == 1) color = color1;
                else color = color2;
                Tile tile = new Tile(tileW, tileH, color);
                tile.setTranslateX(x);
                tile.setTranslateY(y);
                tile.setTileNumbers(j, i);

                tiles[i][j] = tile;
                root.getChildren().add(tile);
                if (j != 7) colorBit *= -1;
            }
        }
        drawBoard();

        primaryStage.setTitle("Chess");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void drawBoard() {
        Piece[][] board = chess.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    tiles[i][j].put(board[i][j]);
                }
            }
        }
    }

    private void drawActions(Tile tile) {
        LinkedList<LinkedList<int[]>> actions = tile.piece.getActions();
        LinkedList<int[]> moves = actions.get(0);
        LinkedList<int[]> attacks = actions.get(1);
        for (int[] arr : moves) {
            tiles[arr[1]][arr[0]].highlightM();
            affected.add(tiles[arr[1]][arr[0]]);
        }
        for (int[] arr : attacks) {
            tiles[arr[1]][arr[0]].highlightA();
            affected.add(tiles[arr[1]][arr[0]]);
        }
        tile.highlightS();
        affected.add(tile);
    }

    private void removeActions() {
        for (Tile t : affected) t.highlightO();
        selected = null;
    }

    public static void main(String[] args) {
        launch(args);
    }

    class Tile extends Pane {

        private Rectangle background;
        private Color original;
        private Piece piece;

        // tile number
        private int x;
        private int y;

        // true x and true y of the position of this tile
        private double trueX;
        private double trueY;

        // the status of this tile when it is highlighted. it
        // is used for executing the correct mouse event.
        private int status;

        private double moveSecs = .5;

        Tile(double width, double height, Color bg) {
            background = new Rectangle();
            background.setWidth(width);
            background.setHeight(height);
            background.setFill(bg);
            background.setStroke(Color.BLACK);
            background.setStrokeWidth(3);
            original = bg;
            this.getChildren().add(background);
            this.setOnMouseClicked(event -> handle());
            piece = null;
        }

        void setTileNumbers(int x, int y) {
            this.x = x;
            this.y = y;
            trueX = x * tileW;
            trueY = y * tileH;
        }

        int getX() {return x;}
        int getY() {return y;}

        void put(Piece piece) {
            this.piece = piece;
            ImageView image = piece.getImage();
            image.setFitWidth(tileW);
            image.setFitHeight(tileH);
            image.setX(trueX);
            image.setY(trueY);
            image.setRotate(0);
            image.setScaleX(1);
            image.setScaleY(1);
            image.setTranslateX(0);
            image.setTranslateY(0);
//            this.getChildren().add(piece.getImage());
            root.getChildren().add(piece.getImage());
            piece.getImage().setOnMouseClicked(event -> handle());
        }

        void clear() {
            if (piece != null) {
//                this.getChildren().remove(piece.getImage());
                root.getChildren().remove(piece.getImage());
                piece = null;
            }
        }

        void handle() {
            if (moving) return; // no selections while moving
            if (piece == null && status == 0) return; // empty space cannot be selected

            if (selected == null) {
                drawActions(this);
            } else if (status == 0) {
                removeActions();
                selected = this;
                drawActions(this);
            } else if (status == 1) {
                selected.moveToEmpty(this);
                removeActions();
            } else if (status == 2) {
                selected.moveToOccupied(this);
                removeActions();
            } else if (status == 3) {
                removeActions();
                affected.clear();
            }
        }

        void moveToEmpty(Tile to) {
//            moving = true;
//            ParallelTransition s = selected.shrinkTransition();
//            s.play();
//            s.setOnFinished(event -> {
//                Piece temp = piece;
//                clear();
//                to.put(temp);
//                chess.move(temp, to.getX(), to.getY());
//                ParallelTransition g = to.growTransition();
//                g.play();
//                g.setOnFinished(event1 -> moving = false);
//            });
//
            moving = true;
            PathTransition move = moveTransition(to);
            move.play();
            move.setOnFinished(event -> {
                Piece temp = piece;
                clear();
                to.put(temp);
                chess.move(temp, to.getX(), to.getY());
                moving = false;
            });
        }

        void moveToOccupied(Tile to) {
            moving = true;
            ParallelTransition s = selected.shrinkTransition();
            s.play();
            s.setOnFinished(event -> {
                Piece temp = piece;
                clear();
                to.clear();
                to.put(temp);
                chess.move(temp, to.getX(), to.getY());
                ParallelTransition g = to.growTransition();
                g.play();
                g.setOnFinished(event1 -> moving = false);
            });
        }

        void highlightO() {
            status = 0;
            background.setFill(original);
        }

        void highlightM() {
            status = 1;
            background.setFill(m);
        }

        void highlightA() {
            status = 2;
            background.setFill(a);
        }

        void highlightS() {
            selected = this;
            status = 3;
            background.setFill(s);
        }

        Line between(Tile to) {
            return new Line(
                    trueX + background.getWidth() / 2,
                    trueY + background.getHeight() / 2,
                    to.trueX + to.background.getWidth() / 2,
                    to.trueY + to.background.getHeight() / 2);
        }

        ParallelTransition shrinkTransition() {
            ParallelTransition p = new ParallelTransition();
            RotateTransition r = new RotateTransition(Duration.seconds(moveSecs / 2.0));
            ScaleTransition s = new ScaleTransition(Duration.seconds(moveSecs / 2.0));
            r.setCycleCount(1);
            r.setFromAngle(0);
            r.setToAngle(360);
            s.setCycleCount(1);
            s.setFromX(1);
            s.setToX(0);
            s.setFromY(1);
            s.setToY(0);
            p.setNode(piece.getImage());
            p.getChildren().addAll(r, s);
            p.setCycleCount(1);
            return p;
        }

        ParallelTransition growTransition() {
            ParallelTransition p = new ParallelTransition();
            RotateTransition r = new RotateTransition(Duration.seconds(moveSecs / 2.0));
            ScaleTransition s = new ScaleTransition(Duration.seconds(moveSecs / 2.0));
            r.setCycleCount(1);
            r.setFromAngle(0);
            r.setToAngle(-360);
            s.setCycleCount(1);
            s.setFromX(0);
            s.setToX(1);
            s.setFromY(0);
            s.setToY(1);
            p.setNode(piece.getImage());
            p.getChildren().addAll(r, s);
            p.setCycleCount(1);
            return p;
        }

        PathTransition moveTransition(Tile to) {
            Line between = this.between(to);
            PathTransition p = new PathTransition(Duration.seconds(moveSecs), between, piece.getImage());
            p.setCycleCount(1);
            return p;
        }
    }
}
