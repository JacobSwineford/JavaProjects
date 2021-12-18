package Misc.CustomClasses.BetterMonopoly.Spaces;

import Misc.CustomClasses.BetterMonopoly.Events.Event;
import Misc.CustomClasses.BetterMonopoly.Monopoly;
import Misc.CustomClasses.BetterMonopoly.Player;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

/**
 * The space of "Jail" in the game of Monopoly. This space has two sections with two
 * different purposes:
 * 1. Just Visiting section. When you land on a jail normally, then you go into
 *    the Just Visiting section and functions as a 'Nothing' space
 * 2. Jail section. Certain card and space effects send a player to wait in a Jail,
 *    where traditionally they need to roll doubles on two dice or wait 3 turns to get out.
 *    Note that this may be implemented however one see's fit
 *
 * @author Jacob Swineford
 */
public class Jail extends Space {

    private LinkedList<Player> jailed;

    public Jail(int boardIndex, String name) {
        super(boardIndex, name);
        jailed = new LinkedList<>();
        des = "When landing on this space, you are 'just visiting' and nothing happens." +
                " When in jail, rolling two dice or waiting three turns will let you out" +
                " of jail";
    }

    @Override
    public Event check(Monopoly monopoly, Player player) {
        String name;
        String des;

        if (jailed.contains(player)) {
            name = "You are in Jail";
            des = "roll doubles to get out";
            Event e = new Event(monopoly, name, des);
            e.setNeedsDiceRoll(true);
            return e;
        } else {
            name = "Just visiting";
            des = "";
            return new Event(monopoly, name, des);
        }
    }

    @Override
    public Pane build(double width, double height) {
        Pane p = new Pane();
        Rectangle back = new Rectangle(width, height, Color.LIGHTGRAY);
        back.setStroke(Color.BLACK);
        Rectangle jail = new Rectangle(width * .6, height * .6, Color.LIGHTGRAY.darker());
        double jailX = width - jail.getWidth();
        jail.setX(jailX);
        jail.setStroke(Color.BLACK);
        VBox t = Property.shapeText(jail.getWidth(), jail.getHeight(), jailX, 0, this);
        p.getChildren().addAll(back, jail, t);
        return p;
    }

    public boolean contains(Player player) {return jailed.contains(player);}

    public void jail(Player player) {
        jailed.add(player);
        player.setSpace(this);
    }
    public void release(Player player) {jailed.remove(player);}
}
