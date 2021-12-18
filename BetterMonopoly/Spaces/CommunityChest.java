package Misc.CustomClasses.BetterMonopoly.Spaces;

import Misc.CustomClasses.BetterMonopoly.Events.Event;
import Misc.CustomClasses.BetterMonopoly.Monopoly;
import Misc.CustomClasses.BetterMonopoly.Player;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The space of "Community Chest" in the game of Monopoly.
 * When landing on this space, a card is drawn
 * from the game's Community Chest and is removed from the deck.
 * It can be resolved via the <code>Events</code> class.
 *
 * @author Jacob Swineford
 */
public class CommunityChest extends Space {

    public CommunityChest(int boardIndex, String name) {
        super(boardIndex, name);
        des = "Draw a Community Chest card with a random effect";
    }

    @Override
    public Event check(Monopoly monopoly, Player player) {
        String name = "Community Chest";
        String des = "Draw a Community Chest card";
        Event e = new Event(monopoly, name, des);
        monopoly.getCCDeck().remove(e);
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
