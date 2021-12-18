package Misc.CustomClasses.BetterMonopoly;

import Misc.CustomClasses.BetterMonopoly.Events.Event;
import Misc.CustomClasses.BetterMonopoly.Events.Events;
import Misc.CustomClasses.BetterMonopoly.Spaces.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Engine for the game of Monopoly.
 *
 * @author Jacob Swineford
 */
public class Monopoly implements Serializable {

    private Player current;
    private Player[] players;
    private LinkedList<Space> spaces;
    private LinkedList<Event> chanceDeck;
    private LinkedList<Event> CCDeck;

    public Monopoly(Player... players) {
        this.players = players;
        current = players[0];
        spaces = new LinkedList<>();
        chanceDeck = new LinkedList<>();
        CCDeck = new LinkedList<>();
        setDefaultSpaces();
        Events.initializeEvents(this);
        setDefaultChance();
        setDefaultCC();
        for (Player p : players) {
            p.setSpace(spaces.get(0)); // first space
        }
    }

    public Pane constructBoard(double width, double height, int spacesPerRow) {
        Pane root = new Pane();
        root.setStyle("-fx-border-color: rgb(100, 100, 100, 1);");
        double w = width / spacesPerRow;
        double h = height / ((double) spaces.size() / spacesPerRow);
        int dx = 0;
        int dy = 0;
        int onRow = 0;
        for (Space s : spaces) {
            Pane p = s.build(w, h);
            p.setTranslateX(dx * w);
            p.setTranslateY(dy * h);
            onRow++;
            if (onRow == spacesPerRow) {
                onRow = 0;
                dy++;
                dx = 0;
            } else {
                dx++;
            }
            root.getChildren().add(p);
        }
        return root;
    }

    LinkedList<Space> move(int numSpaces) {
        LinkedList<Space> traversed = traverse(current, numSpaces);
        current.setSpace(traversed.getLast());
        return traversed;
    }
    Event check() {
        return current.getSpace().check(this, current);
    }

    public Space spaceWithName(String name) {
        for (Space s : spaces) {
            if (s.getName().equals(name)) return s;
        }
        return null;
    }

    public Event drawChance() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return chanceDeck.get(rand.nextInt(chanceDeck.size()));
    }

    public Event drawCC() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return chanceDeck.get(rand.nextInt(chanceDeck.size()));
    }

    public LinkedList<Space> traverse(Player player, Space to) {
        LinkedList<Space> t = new LinkedList<>();
        Space s = player.getSpace();
        while (s != to) {
            s = s.next(spaces);
            t.add(s);
        }
        return t;
    }
    public LinkedList<Space> traverse(Player player, String type) {
        LinkedList<Space> t = new LinkedList<>();
        Space s = player.getSpace();
        while (true) {
            if (type.equals("Jail") && s instanceof Jail) break;
            else if (type.equals("Railroad") && s instanceof Railroad) break;
            else if (type.equals("Property") && s instanceof Property) break;
            else if (type.equals("Tax") && s instanceof Tax) break;
            else if (type.equals("Utility") && s instanceof Utility) break;
            else if (type.equals("Chance") && s instanceof Chance) break;
            else if (type.equals("CommunityChest") && s instanceof CommunityChest) break;
            else if (type.equals("Miscellaneous") && s instanceof Miscellaneous) break;
            else if (type.equals("Go") && s instanceof Go) break;

            s = s.next(spaces);
            t.add(s);
        }
        return t;
    }
    public LinkedList<Space> traverse(Player player, int diceValue) {
        LinkedList<Space> t = new LinkedList<>();
        Space s = player.getSpace();
        while (diceValue != 0) {
            if (diceValue > 0) {
                s = s.next(spaces);
                t.add(s);
                diceValue--;
            } else { // moving backwards
                s = s.previous(spaces);
                t.add(s);
                diceValue++;
            }

        }
        return t;
    }

    void nextPlayer() {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == current) {
                current = players[(i + 1) % players.length];
                return;
            }
        }
    }
    public Player getCurrent() {return current;}
    public Player[] getPlayers() {return players;}
    public LinkedList<Space> getSpaces() {return spaces;}
    public LinkedList<Event> getChanceDeck() {return chanceDeck;}
    public LinkedList<Event> getCCDeck() {return CCDeck;}

    private void setDefaultSpaces() {
        spaces.add(new Go(0, 200, "Go"));
        spaces.add(new Property(1, Color.PURPLE, "Mediterranean Avenue",
                new double[] {60, 2, 10, 30, 90, 160, 250, 30, 50, 50}));
        spaces.add(new CommunityChest(2, "Community Chest 1"));
        spaces.add(new Property(3, Color.PURPLE, "Baltic Avenue",
                new double[] {60, 4, 20, 60, 180, 320, 450, 30, 50, 50}));
        spaces.add(new Tax(4, 200, "Income Tax"));
        spaces.add(new Railroad(5, 200, 100, "Reading Railroad"));
        spaces.add(new Property(6, Color.WHITE, "Oriental Avenue",
                new double[] {100, 6, 30, 90, 270, 400, 550, 50, 50, 50}));
        spaces.add(new Chance(7, "Chance 1"));
        spaces.add(new Property(8, Color.WHITE, "Vermont Avenue",
                new double[] {100, 6, 30, 90, 270, 400, 550, 50, 50, 50}));
        spaces.add(new Property(9, Color.WHITE, "Connecticut Avenue",
                new double[] {120, 8, 40, 100, 300, 450, 600, 60, 50, 50}));
        spaces.add(new Jail(10, "Jail"));
        spaces.add(new Property(11, Color.PINK, "Saint Charles Place",
                new double[] {140, 10, 50, 150, 450, 625, 750, 70, 100, 100}));
        spaces.add(new Utility(12, 150, 75, "Electric Company"));
        spaces.add(new Property(13, Color.PINK, "States Avenue",
                new double[] {140, 10, 50, 150, 450, 625, 750, 70, 100, 100}));
        spaces.add(new Property(14, Color.PINK, "Virginia Avenue",
                new double[] {160, 12, 60, 180, 500, 700, 900, 80, 100, 100}));
        spaces.add(new Railroad(15, 200, 100, "Pennsylvania Railroad"));
        spaces.add(new Property(16, Color.ORANGE, "Saint James Place",
                new double[] {180, 14, 70, 200, 550, 750, 950, 90, 100, 100}));
        spaces.add(new CommunityChest(17, "Community Chest 2"));
        spaces.add(new Property(18, Color.ORANGE, "Tennessee Avenue",
                new double[] {180, 14, 70, 200, 550, 750, 950, 90, 100, 100}));
        spaces.add(new Property(19, Color.ORANGE, "New York Avenue",
                new double[] {200, 16, 80, 220, 600, 800, 1000, 100, 100, 100}));
        spaces.add(new Miscellaneous(20, "Free Parking"));
        spaces.add(new Property(21, Color.RED, "Kentucky Avenue",
                new double[] {220, 18, 90, 250, 700, 875, 1050, 110, 150, 150}));
        spaces.add(new Chance(22, "Chance 2"));
        spaces.add(new Property(23, Color.RED, "Indiana Avenue",
                new double[] {220, 18, 90, 250, 700, 875, 1050, 110, 150, 150}));
        spaces.add(new Property(24, Color.RED, "Illinois Avenue",
                new double[] {240, 20, 100, 300, 750, 925, 1100, 120, 150, 150}));
        spaces.add(new Railroad(25, 200, 100, "B&O Railroad"));
        spaces.add(new Property(26, Color.YELLOW, "Atlantic Avenue",
                new double[] {260, 22, 110, 330, 800, 975, 1150, 130, 150, 150}));
        spaces.add(new Property(27, Color.YELLOW, "Ventnor Avenue",
                new double[] {260, 22, 110, 330, 800, 975, 1150, 130, 150, 150}));
        spaces.add(new Utility(28, 150, 75, "Water Works"));
        spaces.add(new Property(29, Color.YELLOW, "Marvin Gardens",
                new double[] {280, 24, 120, 360, 850, 1025, 1200, 140, 150, 150}));
        spaces.add(new Miscellaneous(30, "Go to Jail"));
        spaces.add(new Property(31, Color.GREEN, "Pacific Avenue",
                new double[] {300, 26, 130, 390, 900, 1100, 1275, 150, 200, 200}));
        spaces.add(new Property(32, Color.GREEN, "North Carolina Avenue",
                new double[] {300, 26, 130, 390, 900, 1100, 1275, 150, 200, 200}));
        spaces.add(new CommunityChest(33, "Community Chest 2"));
        spaces.add(new Property(34, Color.GREEN, "Pennsylvania Avenue",
                new double[] {320, 28, 150, 450, 1000, 1200, 1400, 160, 200, 200}));
        spaces.add(new Railroad(35, 200, 100, "Short Line"));
        spaces.add(new Chance(36, "Chance 3"));
        spaces.add(new Property(37, Color.BLUE, "Park Place",
                new double[] {350, 35, 175, 500, 1100, 1300, 1500, 175, 200, 200}));
        spaces.add(new Tax(38, 75, "Luxury Tax"));
        spaces.add(new Property(39, Color.BLUE, "Boardwalk",
                new double[] {400, 50, 200, 600, 1400, 1700, 2000, 200, 200, 200}));
    }
    private void setDefaultChance() {chanceDeck.addAll(Events.allChanceEvents());}
    private void setDefaultCC() {CCDeck.addAll(Events.allCCEvents());}

    public static void main(String[] args) {
        Player p1 = new Player("Jacob", 2000);
        Player p2 = new Player("Joe", 2000);
        Monopoly m = new Monopoly(p1, p2);
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int r = rand.nextInt(11) + 2;
        System.out.println(m.move(r));
        Event e = m.check();
        System.out.println(e.getName());
        System.out.println(e.getFunds());
        System.out.println(e.getPairs());
    }

}
