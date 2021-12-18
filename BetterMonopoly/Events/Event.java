package Misc.CustomClasses.BetterMonopoly.Events;

import Misc.CustomClasses.BetterMonopoly.Monopoly;
import Misc.CustomClasses.BetterMonopoly.Player;
import Misc.CustomClasses.BetterMonopoly.Spaces.Ownable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Jacob Swineford
 */
public class Event implements Serializable {

    private String name;
    private String des;
    private Monopoly monopoly;

    private double funds;
    private int moves;
    private int jailCards;
    private boolean toJail;
    private boolean needsDiceRoll;

    // properties that the event recipient has the ability to buy
    private LinkedList<Ownable> ownable;

    // events for other players to resolve as a part of the recipients event effect
    private HashMap<Player, Event> pairs;

    public Event(double funds, int moves, int jailCards, boolean toJail, LinkedList<Ownable> ownable) {
        this.funds = funds;
        this.moves = moves;
        this.jailCards = jailCards;
        this.toJail = toJail;
        this.ownable = ownable;
        this.pairs = new HashMap<>();
    }

    public Event(Monopoly monopoly, String name, String des) {
        this.monopoly = monopoly;
        this.name = name;
        this.des = des;
        ownable = new LinkedList<>();
        pairs = new HashMap<>();
    }


    public void setFunds(double funds) {this.funds = funds;}
    public double getFunds() {
        return funds;
    }

    public void setMoves(int moves) {this.moves = moves;}
    public int getMoves() {
        return moves;
    }

    public void addJailCard() {jailCards++;}
    public int getJailCards() {
        return jailCards;
    }

    public void setToJail(boolean b) {toJail = b;}
    public boolean toJail() {
        return toJail;
    }

    public void setNeedsDiceRoll(boolean b) {needsDiceRoll = b;}
    public boolean needsDiceRoll() {return needsDiceRoll;}

    public void addOwnable(Ownable o) {ownable.add(o);}
    public LinkedList<Ownable> getOwnable() {
        return ownable;
    }

    public void addPair(Player player, Event event) {pairs.put(player, event);}
    public HashMap<Player, Event> getPairs() {return pairs;}

    public String getName() {return name;}
    public String getDes() {return des;}
    public Monopoly getMonopoly() {return monopoly;}

    public String toString() {
        return funds + ", " + moves + ", " + jailCards + ", " + toJail;
    }

}
