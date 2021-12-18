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
 * The space of "Go" in the game of Monopoly. Traditionally, this is the first space
 * that the players start on, and every time they pass it they add $200 to their funds.
 * An event happens when a player lands / moves over this space. This event can be
 * resolved via the <code>Events</code> class.
 *
 * @author Jacob Swineford
 */
public class Go extends Space {

    private double value;

    public Go(int boardIndex, double value, String name) {
        super(boardIndex, name);
        des = "When passing this space, collect $" + value;
        this.value = value;
    }

    @Override
    public Event check(Monopoly monopoly, Player player) {
        String name = "Landing on / passing Go";
        String des = "Collect $" + value;
        Event e = new Event(monopoly, name, des);
        e.setFunds(value);
        return e;
    }

    @Override
    public Pane build(double width, double height) {
        Pane p = new Pane();
        Rectangle back = new Rectangle(width, height, Color.LIGHTSALMON);
        back.setStroke(Color.BLACK);
        VBox t = Property.shapeText(width, height, 0, 0, this);
        p.getChildren().addAll(back, t);
        return p;
    }

    public double getValue() {return value;}
}
