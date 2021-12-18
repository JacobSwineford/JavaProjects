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
 * Any space in the game of Monopoly that does not need a dedicated class for it.
 * Right now, this class does checks for the following spaces:
 * - Free Parking
 * - Go to Jail
 *
 * @author Jacob Swineford
 */
public class Miscellaneous extends Space {

    public Miscellaneous(int boardIndex, String name) {
        super(boardIndex, name);
        if (name.equals("Go to Jail")) {
            des = "Go directly to Jail";
        } else if (name.equalsIgnoreCase("Free Parking")) {
            des = "Free Parking! Nothing happens";
        }
    }

    @Override
    public Event check(Monopoly monopoly, Player player) {
        String name = "";
        String des = "";

        if (this.getName().equals("Go to Jail")) {
            name = "Go to Jail";
            des = "Go directly to jail, do not pass Go, do not collect Go money";
            Event e = new Event(monopoly, name, des);
            e.setToJail(true);
            return e;
        } else { // free parking
            return new Event(monopoly, name, des);
        }
    }

    @Override
    public Pane build(double width, double height) {
        Pane p = new Pane();
        Rectangle back = new Rectangle(width, height, Color.LIGHTGRAY);
        back.setStroke(Color.BLACK);
        VBox t = Property.shapeText(width, height, 0, -(height / 4), this);
        p.getChildren().addAll(back, t);
        return p;
    }
}
