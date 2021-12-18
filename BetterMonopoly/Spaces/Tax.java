package Misc.CustomClasses.BetterMonopoly.Spaces;

import Misc.CustomClasses.BetterMonopoly.Events.Event;
import Misc.CustomClasses.BetterMonopoly.Monopoly;
import Misc.CustomClasses.BetterMonopoly.Player;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

/**
 * A tax in the game of Monopoly. If a Player lands on a Tax space, then that
 * player must pay a tax based on this Tax space's value.
 *
 * @author Jacob Swineford
 */
public class Tax extends Space {

    private double tax;

    public Tax(int boardIndex, double tax, String name) {
        super(boardIndex, name);
        this.tax = tax;
        des = "A tax of $" + tax + " must be payed to the bank if landing on this space";
    }

    public double getTax() {return tax;}

    @Override
    public Event check(Monopoly monopoly, Player player) {
        String name = getName();
        String des = "";
        Event e = new Event(monopoly, name, des);
        e.setFunds(-tax);
        return e;
    }

    @Override
    public Pane build(double width, double height) {
        Pane p = new Pane();
        Rectangle back = new Rectangle(width, height, Color.LIGHTGRAY);
        back.setStroke(Color.BLACK);
        VBox v = Property.shapeText(width, height, 0, 0, this);
        p.getChildren().addAll(back, v);
        return p;
    }
}
