package Misc.CustomClasses.BetterMonopoly.Spaces;

import Misc.CustomClasses.BetterMonopoly.Events.Event;
import Misc.CustomClasses.BetterMonopoly.Monopoly;
import Misc.CustomClasses.BetterMonopoly.Player;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * A Space that may be landed on in the game of Monopoly.
 *
 * @author Jacob Swineford
 */
public abstract class Space implements Serializable {

    private int boardIndex;
    private String name;
    protected String des;

    Space(int boardIndex, String name) {
        this.boardIndex = boardIndex;
        this.name = name;
    }

    /**
     * Method to be used to check the Event to be done for any particular
     * Space for any particular Player. Events returned by this method
     * are to be resolved using the <code>resolve(...)</code> method of
     * the <code>Events</code> class.
     *
     * @param monopoly monopoly board the player is on
     * @param player target player for event
     *
     * @return Event to be done for this space
     */
    public abstract Event check(Monopoly monopoly, Player player);


    /**
     * Method to be used to build a graphic for this space. The graphic
     * will be returned as a <code>Pane</code> object.
     *
     * @param width to-be width of this graphic
     * @param height to-be height of this graphic
     *
     * @return default graphic for this space
     */
    public abstract Pane build(double width, double height);


    /**
     * Method to find the space adjacent-front to this one in a list.
     * This method has the potential to not work if this space
     * is not in the given list.
     *
     * @param spaces list of spaces
     * @return next space in the list after this one
     */
    public Space next(LinkedList<Space> spaces) {
        return spaces.get((boardIndex + 1) % spaces.size());
    }

    /**
     * Method to find the space adjacent-front to this one in a list.
     * This method has the potential to not work if this space
     * is not in the given list.
     *
     * @param spaces list of spaces
     * @return next space in the list after this one
     */
    public Space previous(LinkedList<Space> spaces) {
        int index = boardIndex - 1;
        if (index < 0) index = spaces.size() - 1;
        return spaces.get(index);
    }

    public int getBoardIndex() {return boardIndex;}
    public String getName() {return name;}
    public String getDes() {return des;}
}
