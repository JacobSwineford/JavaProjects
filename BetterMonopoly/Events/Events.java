package Misc.CustomClasses.BetterMonopoly.Events;

import Misc.CustomClasses.BetterMonopoly.Monopoly;
import Misc.CustomClasses.BetterMonopoly.Player;
import Misc.CustomClasses.BetterMonopoly.Spaces.*;

import java.util.LinkedList;

/**
 * Class that is used to manage and initialize Monopoly events.
 *
 * @author Jacob Swineford
 */
public class Events {

    private static Event chanceA;
    private static Event chanceB;
    private static Event chanceC;
    private static Event chanceD;
    private static Event chanceE;
    private static Event chanceF;
    private static Event chanceG;
    private static Event chanceH;
    private static Event chanceI;
    private static Event chanceJ;
    private static Event chanceK;
    private static Event chanceL;
    private static Event chanceM;
    private static Event chanceN;
    private static Event chanceO;

    private static Event chestA;
    private static Event chestB;
    private static Event chestC;
    private static Event chestD;
    private static Event chestE;
    private static Event chestF;
    private static Event chestG;
    private static Event chestH;
    private static Event chestI;
    private static Event chestJ;
    private static Event chestK;
    private static Event chestL;
    private static Event chestM;
    private static Event chestN;
    private static Event chestO;
    private static Event chestP;

    /**
     * Method to be used to properly initialize the events found in this class.
     * Once the events have been updated or "checked", then they are to be resolved
     * using the <code>resolve(...)</code> method in this class. This method exists
     * as to be able to get data about an event before resolving said event.
     *
     * @param player main target of event
     * @param event the event to be checked
     *
     * @return checked event, ready to be resolved
     */
    public static Event check(Player player, Event event) {
        if (event == chanceA) {
            event.setFunds(200);
            return event;
        }

        if (event == chanceB) {
            LinkedList<Space> t =
                    event.getMonopoly().traverse(
                            player,
                            event.getMonopoly().spaceWithName("Reading Railroad"));

            for (Space s : t) {
                if (s instanceof Go) {
                    event.addPair(player, s.check(event.getMonopoly(), player));
                }
            }
            event.setMoves(t.size());
            return event;
        }

        if (event == chanceC) {
            event.setFunds(50);
            return event;
        }

        if (event == chanceD) {
            event.setFunds(-15);
            return event;
        }

        if (event == chanceE) {
            event.setFunds(150);
            return event;
        }

        if (event == chanceF) {
            event.setMoves(-3);
        }

        if (event == chanceG) {
           int size = event.getMonopoly().traverse(
                   player,
                   event.getMonopoly().spaceWithName("Illinois Avenue")).size();
           event.setMoves(size);
           return event;
        }

        if (event == chanceH) {
            LinkedList<Space> t =
                    event.getMonopoly().traverse(
                            player,
                            event.getMonopoly().spaceWithName("Saint Charles Place"));
            for (Space s : t) {
                if (s instanceof Go) {
                    event.addPair(player, s.check(event.getMonopoly(), player));
                }
            }

            event.setMoves(t.size());
            return event;
        }

        if (event == chanceI) {
            event.addJailCard();
            return event;
        }

        if (event == chanceJ) {
            event.setToJail(true);
            return event;
        }

        if (event == chanceK) {
            double funds;
            LinkedList<Space> t = event.getMonopoly().traverse(player, "Railroad");
            Space s = t.get(t.size() - 1);
            assert (s instanceof Railroad);
            Railroad r = (Railroad) s;

            if (r.getOwner() != null) {
                if (r.getOwner() != player) {
                    funds = r.getRent(event.getMonopoly()) * 2;
                    event.setFunds(-funds);
                    event.setMoves(t.size());

                    String name = "Rent";
                    String des = "";
                    Event pair = new Event(event.getMonopoly(), name, des);
                    pair.setFunds(funds);
                    event.addPair(r.getOwner(), pair);


                    Event d = new Event(-funds, t.size(), 0, false, null);
                    d.addPair(r.getOwner(), new Event(funds, 0, 0, false, null));
                }
            } else {
                event.addOwnable(r);
            }

            return event;
        }

        if (event == chanceL) {
            int funds = 0;
            for (Player p : event.getMonopoly().getPlayers()) {
                if (p == player) continue;
                funds += 50;
            }
            event.setFunds(-funds);
            return event;
        }

        // special - needs a dice roll to function
        if (event == chanceM) {
            LinkedList<Space> t = event.getMonopoly().traverse(player, "Utility");
            Space s = t.get(t.size() - 1);
            assert (s instanceof Utility);
            Utility u = (Utility) s;

            if (u.getOwner() == null) {
                event.addOwnable(u);
            } else {
                event.setNeedsDiceRoll(true);
            }

            event.setMoves(t.size());
            return event;
        }

        if (event == chanceN) {
            LinkedList<Space> t = event.getMonopoly().traverse(player,
                    event.getMonopoly().spaceWithName("Boardwalk"));

            event.setMoves(t.size());
            return event;
        }

        if (event == chanceO) {
            double funds = 0;
            for (Ownable o : player.getOwned()) {
                if (o instanceof Property) {
                    Property p = (Property) o;
                    funds += (p.getHouses() * 25);
                    funds += (p.getHotels() * 100);
                }
            }
            event.setFunds(-funds);
            return event;
        }

        if (event == chestA) {
            event.addJailCard();
            return event;
        }

        if (event == chestB) {
            double funds = 0;
            for (Ownable o : player.getOwned()) {
                if (o instanceof Property) {
                    Property p = (Property) o;
                    funds += p.getHouses() * 40;
                    funds += p.getHotels() * 115;
                }
            }
            event.setFunds(-funds);
            return event;
        }

        if (event == chestC) {
            event.setFunds(-100);
            return event;
        }

        if (event == chestD) {
            event.setFunds(-150);
            return event;
        }

        if (event == chestE) {
            event.setFunds(20);
            return event;
        }

        if (event == chestF) {
            event.setFunds(100);
            return event;
        }

        if (event == chestG) {
            int funds = 0;
            for (Player p : event.getMonopoly().getPlayers()) {
                if (p != player) {
                    funds += 50;

                    String name = "Opera seat";
                    String des = "";
                    Event pair = new Event(event.getMonopoly(), name, des);
                    pair.setFunds(-50);
                    event.addPair(p, pair);
                }
            }
            event.setFunds(funds);
            return event;
        }

        if (event == chestH) {
            event.setFunds(100);
            return event;
        }

        if (event == chestI) {
            event.setFunds(-50);
            return event;
        }

        if (event == chestJ) {
            event.setFunds(25);
            return event;
        }

        if (event == chestK) {
            LinkedList<Space> t = event.getMonopoly().traverse(player, "Go");

            assert(t.get(t.size() - 1) instanceof Go);
            Go go = (Go) t.get(t.size() - 1);
            event.setFunds(go.getValue());
            event.setMoves(t.size());
            return event;
        }

        if (event == chestL) {
            event.setFunds(10);
            return event;
        }

        if (event == chestM) {
            event.setFunds(45);
            return event;
        }

        if (event == chestN) {
            event.setToJail(true);
            return event;
        }

        if (event == chestO) {
            event.setFunds(200);
            return event;
        }

        if (event == chestP) {
            event.setFunds(100);
            return event;
        }

        return event; // no match
    }

    /**
     * Method that may be used to resolve Events for any particular Player.
     * This method assumes that, if an event was gotten from this class,
     * that the event was checked using the <code>check(...)</code> and
     * iterated based on the current status of the Board, Properties, etc.
     *
     * @param player player used to resolve the event
     * @param event event to be resolved
     */
    public static void resolve(Player player, Event event, boolean resolvePair) {
        player.addFunds(event.getFunds());
        player.addCard(event.getJailCards());
        player.setSpace(event.getMonopoly().traverse(player, event.getMoves()).getLast());
        if (event.toJail()) {
            LinkedList<Space> t = event.getMonopoly().traverse(player, "Jail"); // next jail in our way
            Space s = t.get(t.size() - 1);
            assert (s instanceof Jail);
            Jail j = (Jail) s;
            j.jail(player);
        }
    }

    /**
     * Method to initialize all card events with their connected board, name, and
     * description. when events are meant to be resolved, they must be iterated
     * over using the <code>check(...)</code> method then fed to the
     * <code>resolve(...)</code> for resolving an event's effects.
     *
     * This method is only meant to be called once.
     */
    public static void initializeEvents(Monopoly monopoly) {
        String name = "Advance to Go";
        String des = "Collect Go money";
        chanceA = new Event(monopoly, name, des);

        name = "Take a ride on Reading Railroad";
        des = "If you pass Go collect Go money";
        chanceB = new Event(monopoly, name, des);

        name = "Bank pays you dividend of $50";
        des = "";
        chanceC = new Event(monopoly, name, des);

        name = "Pay poor tax of $15";
        des = "";
        chanceD = new Event(monopoly, name, des);

        name = "Your Building and Loan matures";
        des = "Collect $150";
        chanceE = new Event(monopoly, name, des);

        name = "Go back 3 spaces";
        des = "";
        chanceF = new Event(monopoly, name, des);

        name = "Advance to Illinois Avenue";
        des = "";
        chanceG = new Event(monopoly, name, des);

        name = "Advance to Saint Charles Place";
        des = "If you pass Go collect Go money";
        chanceH = new Event(monopoly, name, des);

        name = "Get out of Jail free";
        des = "This card may be kept until needed, or sold";
        chanceI = new Event(monopoly, name, des);

        name = "Go directly to Jail";
        des = "Do not pass Go, do not collect Go money";
        chanceJ = new Event(monopoly, name, des);

        name = "Advance token to the nearest Railroad";
        des = "Pay the owner twice the rental to which he/she is" +
                "otherwise entitled. If the Railroad is unowned, you may" +
                "buy it from the bank";
        chanceK = new Event(monopoly, name, des);

        name = "You been elected Chairman of the Board";
        des = "Pay each Player $50";
        chanceL =new Event(monopoly, name, des);

        name = "Advance token to nearest Utility";
        des = "If unowned, you may buy it from the bank. " +
                "If owned, throw dice and pay owner a total of ten times " +
                "the amount shown";
        chanceM = new Event(monopoly, name, des);
        chanceM.setNeedsDiceRoll(true);

        name = "Take a walk on the Boardwalk";
        des = "Advance token to Boardwalk";
        chanceN = new Event(monopoly, name, des);

        name = "Make general repairs on all of your Property";
        des = "For each house pay $25, For each hotel pay $100";
        chanceO = new Event(monopoly, name, des);

        name = "Get out of Jail free";
        des = "This card may be kept until needed, or sold";
        chestA = new Event(monopoly, name, des);

        name = "You are assessed for street repairs";
        des = "$40 per house, $115 per hotel";
        chestB = new Event(monopoly, name, des);

        name = "Pay hospital $100";
        des = "";
        chestC = new Event(monopoly, name, des);

        name = "Pay school tax of $150";
        des = "";
        chestD = new Event(monopoly, name, des);

        name = "Income tax refund";
        des = "Collect $20";
        chestE = new Event(monopoly, name, des);

        name = "Life insurance matures";
        des = "Collect $100";
        chestF = new Event(monopoly, name, des);

        name = "Grand Opera opening";
        des = "Collect $50 from each Player for opening night seats";
        chestG = new Event(monopoly, name, des);

        name = "You inherit $100";
        des = "";
        chestH = new Event(monopoly, name, des);

        name = "Doctor's fee";
        des = "Pay $50";
        chestI = new Event(monopoly, name, des);

        name = "Receive $25 for services";
        des = "";
        chestJ = new Event(monopoly, name, des);

        name = "Advance to Go";
        des = "Collect Go money";
        chestK = new Event(monopoly, name, des);

        name = "You have won second prize in a beauty contest";
        des = "Collect $10";
        chestL = new Event(monopoly, name, des);

        name = "From sale of stock you get $45";
        des = "";
        chestM = new Event(monopoly, name, des);

        name = "Go directly to Jail";
        des = "Do not pass Go, do not collect Go money";
        chestN = new Event(monopoly, name, des);

        name = "Bank error in your favor";
        des = "Collect $200";
        chestO = new Event(monopoly, name, des);

        name = "Xmas fund matures";
        des = "Collect $100";
        chestP = new Event(monopoly, name, des);
    }

    /**
     * A list of all Chance events manually created by this class.
     *
     * @return <code>LinkedList</code> of all Chance events
     */
    public static LinkedList<Event> allChanceEvents() {
        LinkedList<Event> l = new LinkedList<>();
        l.add(chanceA);
        l.add(chanceB);
        l.add(chanceC);
        l.add(chanceD);
        l.add(chanceE);
        l.add(chanceF);
        l.add(chanceG);
        l.add(chanceH);
        l.add(chanceI);
        l.add(chanceJ);
        l.add(chanceK);
        l.add(chanceL);
        l.add(chanceM);
        l.add(chanceN);
        l.add(chanceO);
        return l;
    }

    /**
     * A list of all Community Chest events manually created by this class.
     *
     * @return <code>LinkedList</code> of all Community Chest events
     */
    public static LinkedList<Event> allCCEvents() {
        LinkedList<Event> l = new LinkedList<>();
        l.add(chestA);
        l.add(chestB);
        l.add(chestC);
        l.add(chestD);
        l.add(chestE);
        l.add(chestF);
        l.add(chestG);
        l.add(chestH);
        l.add(chestI);
        l.add(chestJ);
        l.add(chestK);
        l.add(chestL);
        l.add(chestM);
        l.add(chestN);
        l.add(chestO);
        l.add(chestP);
        return l;
    }
}
