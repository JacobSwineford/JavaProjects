package Misc.CustomClasses.BetterMonopoly;

import Misc.CustomClasses.BetterMonopoly.Spaces.Ownable;
import Misc.CustomClasses.BetterMonopoly.Spaces.Space;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * A player in the game of Monopoly.
 *
 * @author Jacob Swineford
 */
public class Player implements Serializable {

    private String name;
    private double money;
    private LinkedList<Ownable> owned;
    private int getOutOfJailCards;

    private Space space;

    public Player(String name, double money) {
        this.name = name;
        this.money = money;
        owned = new LinkedList<>();
        getOutOfJailCards = 0;
    }

    public void addOwnable(Ownable toAdd) {
        owned.add(toAdd);
    }

    public void removeOwnable(Ownable toRemove) {
        owned.remove(toRemove);
    }

    public void addFunds(double amount) {
        money += amount;
    }

    public String getName() {return name;}

    public double getMoney() {return money;}

    public LinkedList<Ownable> getOwned() {return owned;}

    public int getGetOutOfJailCards() {
        return getOutOfJailCards;
    }

    public void addCard(int num) {getOutOfJailCards += num;}
    public void removeCard() {getOutOfJailCards--;}

    public Space getSpace() {return space;}
    public void setSpace(Space setTo) {space = setTo;}

    @Override
    public String toString() {
        return "[" + name + ", " + money + ", " + space.getName() + "]";
    }
}
