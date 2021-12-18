package Misc.CustomClasses.BetterSnake;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Engine for the game of Snake. Snake is a game where a snake on a
 * two-dimensional grid tries to eat fruit and get a bigger score without running
 * into any of the walls or itself. When the snake eats a fruit, it expands, thus
 * making it harder to get the fruit and not run into anything.
 *
 * @author Jacob Swineford
 */
class SnakeGame extends Pane {

    private int boardX;
    private int boardY;

    // both snakes for competitive play
    private Snake snake;
    private Snake snake2;

    private ArrayList<int[]> fruits;
    private int fruitAmount;

    private Rectangle[][] boxes;

    SnakeGame(int boardX, int boardY, int snakeLength,
                     int[] snakeStart, int[] snakeStart2, Color snakeColor) {
        super();
        fruits = new ArrayList<>();
        this.boardX = boardX;
        this.boardY = boardY;
        snake = new Snake(snakeStart, snakeLength, snakeColor);
        snake2 = new Snake(snakeStart2, snakeLength, snakeColor.invert());
    }

    void makeGame(int boxWidth, int boxHeight, int fruitAmount) {
        boxes = new Rectangle[boardY][boardX];
        for (int i = 0; i < boardY; i++) {
            for (int j = 0; j < boardX; j++) {
                int x = j * boxWidth;
                int y = i * boxHeight;
                Rectangle box = new Rectangle();
                box.setFill(Color.TRANSPARENT);
                box.setStroke(Color.BLACK);
                box.setStrokeWidth(2);
                box.setX(x);
                box.setY(y);
                box.setHeight(boxHeight);
                box.setWidth(boxWidth);
                this.getChildren().add(box);
                boxes[i][j] = box;
            }
        }
        this.fruitAmount = fruitAmount;
        for (int i = 0; i < fruitAmount; i++)
            generateFruit();
        drawGame();
    }

    void startGame(int durationMillis) {
        if (boxes == null) return;
        Thread t = new Tick(durationMillis);
        t.start();
    }

    class Tick extends Thread {

        private int durationMillis;
        private boolean hasSpreadOut;

        Tick(int durationMillis) {
            this.durationMillis = durationMillis;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(durationMillis);
                    snake.advance();
                    snake2.advance();

                    if (snakesIntersecting()) break;

                    if (!hasSpreadOut && snake.isSpreadOut())
                        hasSpreadOut = true;
                    if (hasSpreadOut && snake.isInvalid(boardX, boardY)) break;

                    if (!hasSpreadOut && snake2.isSpreadOut())
                        hasSpreadOut = true;
                    if (hasSpreadOut && snake2.isInvalid(boardX, boardY)) break;

                    int[] fruit = caughtFruit();
                    if (fruit != null) {
                        int amount = 1;
                        int check = fruit[2];
                        fruits.remove(fruit);
                        if (check == 1)
                            snake.expand(amount);
                        else if (check == 2)
                            snake2.expand(amount);

                        if (fruits.size() == 0) {
                            for (int i = 0; i < fruitAmount; i++)
                                generateFruit();
                        }
                    }

                    drawGame();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    Snake getSnake() {return snake;}
    Snake getSnake2() {return snake2;}

    private void drawGame() {
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[i].length; j++) {
                if (fruitAt(new int[] {j, i})) {
                    boxes[i][j].setFill(Color.DARKRED);
                    continue;
                }

                if (snake.hasSegmentAt(new int[] {j, i})) {
                    boxes[i][j].setFill(snake.getSnakeColor());
                } else if (snake2.hasSegmentAt(new int[] {j, i})) {
                    boxes[i][j].setFill(snake2.getSnakeColor());
                } else {
                    boxes[i][j].setFill(Color.TRANSPARENT);
                }
            }
        }
    }

    /**
     * Generates a fruit in a random location and puts it on the board.
     * Fruit cannot spawn on either of the snakes segments.
     */
    private void generateFruit() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int x;
        int y;
        while(true) {
            x = rand.nextInt(boardX);
            y = rand.nextInt(boardY);
            int[] coor = new int[] {x, y};
            if (!snake.hasSegmentAt(coor) && !snake2.hasSegmentAt(coor) && !fruitAt(coor)) {
                fruits.add(coor);
                return;
            }
        }
    }

    /**
     * Checks to see if there is a fruit at the specified coordinates.
     *
     * @param coor coordinates
     * @return true if so, false otherwise
     */
    private boolean fruitAt(int[] coor) {
        for (int[] fruit : fruits) {
            if (coor[0] == fruit[0] && coor[1] == fruit[1]) return true;
        }
        return false;
    }

    /**
     * Checks to see if the head occupies the same space as a fruit.
     * If a fruit is found, then it is returned. A separate int is used
     * to determine whether the fruit was picked up by snake 1 or snake
     * 2, and is returned along with the coordinates of the caught fruit.
     *
     * @return fruit if found, null otherwise
     */
    private int[] caughtFruit() {
        int[] head = snake.getHead();
        int[] head2 = snake2.getHead();
        for (int[] fruit : fruits) {
            if (head[0] == fruit[0] && head[1] == fruit[1])
                return new int[] {fruit[0], fruit[1], 1};
            if (head2[0] == fruit[0] && head2[1] == fruit[1])
                return new int[] {fruit[0], fruit[1], 2};
        }
        return null;
    }

    /**
     * Checks to see if the snakes are intersecting with each other. That is,
     * The locations of any particular segments are the same.
     *
     * @return true if so, false otherwise
     */
    private boolean snakesIntersecting() {
        ArrayList<int[]> segments = snake.getSegments();
        for (int[] coor : segments) {
            if (snake2.hasSegmentAt(coor)) return true;
        }
        return false;
    }
}
