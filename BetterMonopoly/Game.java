package Misc.CustomClasses.BetterMonopoly;

import Misc.CustomClasses.BetterMonopoly.Spaces.Property;
import Misc.CustomClasses.BetterMonopoly.Spaces.Railroad;
import Misc.CustomClasses.BetterMonopoly.Spaces.Space;
import Misc.CustomClasses.BetterMonopoly.Spaces.Utility;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * GUI for the game of Monopoly (Better version).
 * @author Jacob Swineford
 */
public class Game extends Application {

    private final Player[] players = new Player[] {
            new Player("Jacob", 2000),
            new Player("Smaycob", 2000),
            new Player("Bakob", 2000),
            new Player("Makob", 2000),
            new Player("Craycob", 2000),
    };

    // colors for playerSquares. must match the number of players
    private final Color[] colors = new Color[] {
            Color.BLUE,
            Color.PURPLE,
            Color.GREEN,
            Color.YELLOW,
            Color.BROWN
    };

    private HashMap<Player, PlayerSquare> squareMap = new HashMap<>();
    private Pane board;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 1200;
        final int SCENE_HEIGHT = 980;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        Monopoly m = new Monopoly(players);
        this.board = m.constructBoard(SCENE_WIDTH, SCENE_HEIGHT / 2.2, 10);
        root.getChildren().add(board);

        int index = 0;
        for (Player p : players) {
            squareMap.put(p, new PlayerSquare(20, 20, colors[index++]));
            setPlayerTo(p, m.getSpaces().get(0));
        }

        scene.setOnMouseClicked(event -> {
            moveSquare(players[1], players[1].getSpace().next(m.getSpaces()));
            players[1].setSpace(players[1].getSpace().next(m.getSpaces()));
        });

        primaryStage.setTitle("Monopoly");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Sets a player to a given space. Note that this method does not interact
     * with the Monopoly engine, thus does not "move" the player within the
     * game. Moving should be done with the <code>move()</code> method inside
     * the <code>Monopoly</code> class, when an event can be gotten and resolved.
     */
    private void setPlayerTo(Player player, Space space) {
        Pane spaceGraphic = obtainGraphic(space);
        PlayerSquare playerSquare = squareMap.get(player);

        double width = spaceGraphic.getBoundsInLocal().getWidth();
        double height = spaceGraphic.getBoundsInLocal().getHeight();
        LinkedList<double[]> playerPoints = playerPoints(width, height);

        // check how many players are already on this space. this will determine which
        // point is used for the player
        int count = 0;
        for (Node n : spaceGraphic.getChildren()) {
            if (n instanceof PlayerSquare) count++;
        }
        double[] point = playerPoints.get(count);
        playerSquare.setCenter(point[0], point[1]);
        spaceGraphic.getChildren().add(playerSquare);
    }

    /**
     * Moves a player square from one space to another.
     */
    private void moveSquare(Player player, Space to) {
        Pane original = obtainGraphic(player.getSpace());
        original.getChildren().remove(squareMap.get(player));

        // when removing a player square from a space, all other player squares
        // get shifted back to fill in the gap that the leaving one left
        LinkedList<PlayerSquare> q = new LinkedList<>();
        LinkedList<double[]> points = playerPoints(
                original.getBoundsInLocal().getWidth(),
                original.getBoundsInLocal().getHeight()
        );

        for (Node n : original.getChildren()) {
            if (n instanceof PlayerSquare) {
                q.add((PlayerSquare) n);
            }
        }

        for (double[] point : points) {
            if (q.size() == 0) break;
            PlayerSquare s = q.getFirst();
            original.getChildren().remove(s);
            s.setCenter(point[0], point[1]);
            original.getChildren().add(s);
            q.removeFirst();
        }

        setPlayerTo(player, to);
    }

    /**
     * Method to get an organized set of points based on how many players are
     * currently playing in the game. Then, these points can be used to properly
     * move a player square without player squares overlapping.
     *
     * @param width width of the property
     * @param height height of the property
     *
     * @return <code>LinkedList</code> of points for player squares to be
     */
    private LinkedList<double[]> playerPoints(double width, double height) {
        LinkedList<double[]> points = new LinkedList<>();
        int players = this.players.length;
        int columns = 2;
        int rows = 0;

        int count = players;
        while (count > 0) {
            count -= columns;
            rows++;
        }
        double dx = width / (columns + 1);
        double dy = height / (rows + 1);
        for (int i = 1; i <= rows; i++) {
            double py = dy * i;
            for (int j = 1; j <= columns; j++) {
                if (players == 0) break;
                double px = dx * j;
                players--;
                points.add(new double[] {px, py});
            }
        }
        return points;
    }

    private Pane obtainGraphic(Space s) {
        return (Pane) board.getChildren().get(s.getBoardIndex());
    }

    /**
     * Player Squares represent a graphic for any particular player in
     * the game of Monopoly.
     */
    class PlayerSquare extends Pane {

        PlayerSquare(double width, double height, Color color) {
            Rectangle main = new Rectangle(width, height, color);
            main.setStroke(Color.BLACK);
            Circle center = new Circle(3, Color.RED);
            center.setCenterX(width / 2);
            center.setCenterY(height / 2);
            this.getChildren().addAll(main, center);
        }

        void setCenter(double x, double y) {
            this.setTranslateX(x - this.getBoundsInLocal().getWidth() / 2);
            this.setTranslateY(y - this.getBoundsInLocal().getHeight() / 2);
        }
    }

    /**
     * Pane that provides current information about any particular property.
     */
    class PropertyInformationWindow extends Pane {

        PropertyInformationWindow(Property property) {

        }

    }

    /**
     * Pane that provides current information about any particular railroad.
     */
    class RailroadInforationWindow extends Pane {

        RailroadInforationWindow(Railroad railroad) {

        }

    }

    /**
     * Pane that provides current information about any particular Utility.
     */
    class UtilityInformationWindow extends Pane {

        UtilityInformationWindow(Utility utility) {

        }

    }

    /**
     * Pane that provides a generic description about a space, excluding
     * spaces that may be owned. This class thus provides an information pane for
     *
     * Chance
     * Community Chest
     * Go
     * Jail
     * Miscellaneous
     * Tax
     */
    class GenericInformationWindow extends Pane {

        GenericInformationWindow(Space space) {

        }
    }

    /**
     * Pane that provide information about a player.
     */
    class PlayerWindow extends Pane {

        PlayerWindow(Player player) {

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
