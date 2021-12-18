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
 * A Utility in the game of Monopoly. A Utility may be bought and sold,
 * and the more any particular Player owns the higher it's rent when someone
 * other than the owner lands on it. A Utilities check for rent works the same
 * as it does for properties.
 *
 * @author Jacob Swineford
 */
public class Utility extends Space implements Ownable {

    private Player owner;
    private double buyPrice;
    private double mortgageValue;

    public Utility(int boardIndex, double buyPrice, double mortgageValue, String name) {
        super(boardIndex, name);
        this.buyPrice = buyPrice;
        this.mortgageValue = mortgageValue;
        des = "If owned, this utility may be bought from the bank. Otherwise if owned," +
                " you must pay rent to the owner depending on how many utilities they own";
    }

    public void setOwner(Player toOwn) {owner = toOwn;}
    public Player getOwner() {return owner;}

    public double getBuyPrice() {return buyPrice;}
    public double getMortgageValue() {return mortgageValue;}

    public double getRent(int diceValue, Player current) {
        int owned = 0;
        for (Ownable o : current.getOwned()) {
            if (o instanceof Utility) owned++;
        }
        if (owned == 0) return 0;

        // rent is described in a sequence of 4, 10... by adding 6
        int multiplier = 4;
        while (owned > 1) {
            multiplier += 6;
            owned--;
        }
        return diceValue * multiplier;
    }

    @Override
    public Event check(Monopoly monopoly, Player player) {
        String name;
        String des;

        if (owner == player) {
            name = "Welcome Home";
            des = "You own this Utility";
            return new Event(monopoly, name, des);
        }

        if (owner != null) {
            name = "Pay Utility rent - roll dice";
            des = "You owe an amount of money based on a sequence of 4, 10...  for however many" +
                    " Utilities the owner has " +
                    "multiplied by what you rolled for this rent check";
            Event e = new Event(monopoly, name, des);
            e.setNeedsDiceRoll(true);
            return e;
        }

        // no owner
        name = "Utility up for sale";
        des = "This Utility costs " + getBuyPrice();
        Event e = new Event(monopoly, name, des);
        e.addOwnable(this);
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
