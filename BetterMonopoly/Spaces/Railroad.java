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
 * A Railroad in the game of Monopoly. A Railroad may be bought and sold,
 * and the more any particular Player owns the higher it's rent when someone
 * other than the owner lands on it. A Railroads check for rent works the
 * same as it does for Properties.
 *
 * @author Jacob Swineford
 */
public class Railroad extends Space implements Ownable {

    private Player owner;

    private double buyPrice;
    private double mortgageValue;

    public Railroad(int boardIndex, double buyPrice, double mortgageValue, String name) {
        super(boardIndex, name);
        owner = null;
        this.buyPrice = buyPrice;
        this.mortgageValue = mortgageValue;
        des = "If owned, this railroad may be bought from the bank. Otherwise if owned," +
                " you must pay rent to the owner depending on how many railroads they own";
    }

    public void setOwner(Player toOwn) {owner = toOwn;}
    public Player getOwner() {return owner;}

    public double getBuyPrice() {return buyPrice;}
    public double getMortgageValue() {return mortgageValue;}

    public double getRent(Monopoly monopoly) {
        if (owner == null) return 0;
        double rentPrice = 25;
        for (Space s : monopoly.getSpaces()) {
            if (s instanceof Railroad) {
                Railroad r = (Railroad) s;
                if (r.getOwner() == owner) {
                    rentPrice *= 2;
                }
            }
        }
        return rentPrice;
    }

    @Override
    public Event check(Monopoly monopoly, Player player) {
        String name;
        String des;

        if (owner == player) {
            name = "Welcome Home";
            des = "You own this Railroad";
            return new Event(monopoly, name, des);
        }

        if (owner != null) {
            name = "Pay Railroad rent";
            des = "You owe " + getRent(monopoly);
            Event e = new Event(monopoly, name, des);
            e.setFunds(-getRent(monopoly));
            return e;
        }

        // no owner
        name = "Railroad up for sale";
        des = "This Railroad costs " + getBuyPrice();
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
