package Misc.CustomClasses.BetterMonopoly.Spaces;

import Misc.CustomClasses.BetterMonopoly.Events.Event;
import Misc.CustomClasses.BetterMonopoly.Monopoly;
import Misc.CustomClasses.BetterMonopoly.Player;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The space of "Chance" in the game of Monopoly. When landing on this space, a card is drawn
 * from the game's Chance deck and is removed from the deck.
 * It can be resolved via the <code>Events</code> class.
 *
 * @author Jacob Swineford
 */
public class Chance extends Space {

    public Chance(int boardIndex, String name) {
        super(boardIndex, name);
        des = "Draw a Chance card with a random effect";
    }

    @Override
    public Event check(Monopoly monopoly, Player player) {
        String name = "Chance";
        String des = "Draw a Chance card";
        Event e = new Event(monopoly, name, des);
        monopoly.getChanceDeck().remove(e);
        return e;
    }

    @Override
    public Pane build(double width, double height) {
        Pane p = new Pane();
        Rectangle back = new Rectangle(width, height, Color.LIGHTGRAY);
        back.setStroke(Color.BLACK);
        VBox t = Property.shapeText(width, height, 0, 0, this);
        p.getChildren().addAll(back, t);
        return p;
    }
}
