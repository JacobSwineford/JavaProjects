package Misc.CustomClasses.BetterMonopoly.Spaces;

import Misc.CustomClasses.BetterMonopoly.Events.Event;
import Misc.CustomClasses.BetterMonopoly.Monopoly;
import Misc.CustomClasses.BetterMonopoly.Player;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

import java.util.LinkedList;

/**
 * A Property in the game of Monopoly. When a Player lands on a space with an unowned
 * Property, then that Player has the opportunity to buy that property. Properties can
 * be bought, sold (mortgaged), and upgraded.
 *
 * When the Owner of a Property lands on their Property, it is treated as a 'Nothing' space
 * and nothing happens. When a Player lands on a Property that is owned by someone other than
 * them, they must pay rent, which is determined by the amount of houses / hotels on the
 * Property.
 *
 * @author Jacob Swineford
 */
public class Property extends Space implements Ownable {

    private boolean mortgaged;

    private Player owner;
    private Color color;

    private double buyPrice;

    private double noHouseRent;
    private double oneHouseRent;
    private double twoHouseRent;
    private double threeHouseRent;
    private double fourHouseRent;
    private double hotelRent;

    private double mortgageValue;
    private double mortgageBuybackPrice;
    private double housePrice;
    private double hotelPrice;
    private double houseSellPrice;
    private double hotelSellPrice;

    private int houses;
    private int hotels;

    public Property(int boardIndex, Color color, String name, double[] prices) {
        super(boardIndex, name);
        mortgaged = false;
        owner = null;
        this.color = color;
        buyPrice = prices[0];
        noHouseRent = prices[1];
        oneHouseRent = prices[2];
        twoHouseRent = prices[3];
        threeHouseRent = prices[4];
        fourHouseRent = prices[5];
        hotelRent = prices[6];
        mortgageValue = prices[7];
        mortgageBuybackPrice = mortgageValue *= 1.1;
        housePrice = prices[8];
        houseSellPrice = housePrice / 2;
        hotelPrice = prices[9];
        hotelSellPrice = hotelPrice / 2;
        houses = 0;
        des = "If owned, this property may be bought from the bank. Otherwise if owned," +
                " you must pay rent to the owner depending on this property's upgrade" +
                " status";
    }

    public void upgrade() {
        if (houses == 4) {
            houses = 0;
            hotels = 1;
        } else {
            houses++;
        }
    }

    public void degrade() {
        if (hotels == 1) {
            houses = 4;
            hotels = 0;
        } else {
            houses--;
        }
    }

    public double getRent() {
        if (owner == null) return 0;
        if (houses == 0 && hotels == 0) return noHouseRent;
        if (houses == 1) return oneHouseRent;
        if (houses == 2) return twoHouseRent;
        if (houses == 3) return threeHouseRent;
        if (houses == 4) return fourHouseRent;
        return hotelRent;
    }

    public boolean isMortgaged() {return mortgaged;}
    public void setMortgaged(boolean b) {mortgaged = b;}

    public void setOwner(Player toOwn) {owner = toOwn;}
    public Player getOwner() {return owner;}

    public Color getColor() {return color;}
    public double getBuyPrice() {
        if (mortgaged) return mortgageBuybackPrice;
        return buyPrice;
    }
    public double getMortgageValue() {return mortgageValue;}
    public double getHousePrice() {return housePrice;}
    public double getHotelPrice() {return hotelPrice;}
    public double getHouseSellPrice() {return houseSellPrice;}
    public double getHotelSellPrice() {return hotelSellPrice;}
    public int getHouses() {return houses;}
    public int getHotels() {return hotels;}

    @Override
    public Event check(Monopoly monopoly, Player player) {
        String name;
        String des;

        if (owner == player) {
            name = "Welcome Home";
            des = "You own this Property";
            return new Event(monopoly, name, des);
        }

        if (owner != null) {
            name = "Pay Property rent";
            des = "You owe " + getRent();
            Event e = new Event(monopoly, name, des);
            e.setFunds(-getRent());
            return e;
        }

        // no owner
        name = "Property up for sale";
        des = "This Property costs " + getBuyPrice();
        Event e = new Event(monopoly, name, des);
        e.addOwnable(this);
        return e;
    }

    @Override
    public Pane build(double width, double height) {
        Pane p = new Pane();
        Rectangle back = new Rectangle(width, height, Color.LIGHTGRAY);
        back.setStroke(Color.BLACK);
        double bannerHeight = height / 3.5;
        Rectangle banner = new Rectangle(width, bannerHeight, color);
        banner.setStroke(Color.BLACK);
        VBox t = shapeText(width, height - bannerHeight, 0, 10, this);
        p.getChildren().addAll(back, banner, t);
        return p;
    }

    static VBox shapeText(double width, double height, double xBias, double yBias, Space space) {
        VBox v = new VBox();
        String[] tokens = space.getName().split(" ");
        Font defaultFont = Font.font(
                Font.getFamilies().get(1),
                FontWeight.LIGHT,
                FontPosture.REGULAR,
                15);
        for (String s : tokens) {
            Text t = new Text(s);
            t.setFont(defaultFont);
            v.getChildren().add(t);
        }

        // if the text on the card is too big, either by it width or it's height,
        // it's made smaller until it's bounds fit the width and height
        double w = v.getBoundsInLocal().getWidth();
        double h = v.getBoundsInLocal().getHeight();

        double dwindle = defaultFont.getSize();
        while (w > width || h > height) {
            v.getChildren().clear();
            for (String s : tokens) {
                Text t = new Text(s);
                t.setFont(Font.font(
                        Font.getFamilies().get(1),
                        FontWeight.BOLD,
                        FontPosture.REGULAR,
                        --dwindle));
                v.getChildren().add(t);
            }
            w = v.getBoundsInLocal().getWidth();
            h = v.getBoundsInLocal().getHeight();
        }


        v.setTranslateX((width - w) / 2 + xBias);
        v.setTranslateY((height - h) / 2 + yBias);
        return v;
    }
}
