package Misc.CustomClasses.BetterSnake;

import javafx.scene.paint.Color;
import java.util.ArrayList;

import static Misc.CustomClasses.BetterSnake.Direction.*;

/**
 * Object that represents a snake in the game of Snake.
 *
 * @author Jacob Swineford
 */
public class Snake {

    private ArrayList<int[]> segments;
    private Direction moveDirection = Right;
    private Color snakeColor;

    /**
     * Constructor for a snake.
     *
     * @param start starting position of the snake
     * @param length starting length of the snake
     * @param snakeColor color of the snake
     */
    Snake(int[] start, int length, Color snakeColor) {
        this.snakeColor = snakeColor;
        segments = new ArrayList<>();
        segments.add(start);
        assert length > 2;

        int x = start[0];
        int y = start[1];
        for (int i = 1; i < length; i++) {
            segments.add(new int[] {x, y});
        }
    }

    /**
     * Expands this snake with the given amount of segments. Expanding
     * a snake is defined here as adding a duplicate tail segment, thus
     * allowing the game engine, upon a tick, not have to worry about
     * <code>IndexOutOfBounds</code> exceptions.
     *
     * @param amount amount of segments to add
     */
    void expand(int amount) {
        int[] last = segments.get(segments.size() - 1);
        for (int i = 0; i < amount; i++)
            segments.add(new int[] {last[0], last[1]});
    }

    /**
     * Forwards the snake's head using the snakes current direction, effectively
     * moving each segment forward to where the leading segment was.
     * This method is not responsible for any <code>IndexOutOfBounds</code>
     * exceptions that may occur.
     */
    void advance() {
        int[] head = segments.get(0);

        // old values used for copying
        int x = head[0];
        int y = head[1];

        // advance the head based on the direction
        if (moveDirection == Right) {
            head[0]++;
        } else if (moveDirection == Left) {
            head[0]--;
        } else if (moveDirection == Up) {
            head[1]--;
        } else if (moveDirection == Down) {
            head[1]++;
        }

        for (int i = 1; i < segments.size(); i++) {
            int[] segment = segments.get(i);
            int xOld = segment[0];
            int yOld = segment[1];
            segment[0] = x;
            segment[1] = y;
            x = xOld;
            y = yOld;
        }
    }

    /**
     * Checks to see if this snake has one of it's segments at the given
     * location.
     *
     * @param location location to check for segments
     * @return true if a segment is found at the given location. false otherwise.
     */
    boolean hasSegmentAt(int[] location) {
        for (int[] seg : segments) {
            if (seg[0] == location[0] && seg[1] == location[1])
                return true;
        }
        return false;
    }

    /**
     * Checks to see if this snake is in an invalid position. An invalid position
     * is defined as the head of the snake not being unique, or being beyond
     * any particular boundary.
     *
     * @param boardX width of board
     * @param boardY height of board
     * @return true if so, false otherwise
     */
    boolean isInvalid(int boardX, int boardY) {
        int[] head = segments.get(0);
        for (int[] seg : segments) {
            if (head != seg && head[0] == seg[0] && head[1] == seg[1]) {
                return true;
            }
        }
        return head[0] >= boardX || head[1] >= boardY;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{\n");
        for (int[] segment : segments) {
            sb.append("[x=");
            sb.append(segment[0]);
            sb.append(" ,y=");
            sb.append(segment[1]);
            sb.append("]\n");
        }
        sb.append("}");
        return sb.toString();
    }

    void setMoveDirection(Direction direction) {moveDirection = direction;}

    Color getSnakeColor() {return snakeColor;}

    /**
     * Method that checks that this snake is spread out, which means that
     * all of it's segments coordinates are independent of one another.
     *
     * @return true if so, false otherwise
     */
    boolean isSpreadOut() {
        for (int[] seg : segments) {
            for (int[] against : segments) {
                if (seg != against && seg[0] == against[0] && seg[1] == against[1])
                    return false;
            }
        }
        return true;
    }

    int[] getHead() {return segments.get(0);}
    ArrayList<int[]> getSegments() {return segments;}

    public static void main(String[] args) {
        Snake s = new Snake(new int[] {4, 4}, 3, Color.BLUE);
        System.out.println(s);
        s.setMoveDirection(Right);
        s.advance();
        System.out.println("\n" + s);
        s.setMoveDirection(Up);
        s.advance();
        System.out.println("\n" + s);
    }

}
