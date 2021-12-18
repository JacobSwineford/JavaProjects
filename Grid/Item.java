package Misc.CustomClasses.BetterMenu.Grid;

import Misc.CustomClasses.BetterMenu.Direction;

/**
 * Interface that specifies what a menu item must be able to do
 * to be added to a menu.
 *
 * @author Jacob Swineford
 */
public interface Item {

    /**
     * Action for an item that is being navigated on to. A direction is provided
     * for items that have different actions based on where it was entered from.
     *
     * Null will be provided for cases of general selection, such as a menu's
     * <code>select()</code> method.
     */
    void navigatedOn(Direction direction);

    /**
     * Action for an item being navigated off of. A direction is provided for
     * items that have different actions based on where it was exited from.
     *
     * Null will be provided for cases of general selection, such as a menu's
     * <code>select()</code> method.
     */
    void navigatedOff(Direction direction);

    /**
     * Action for an item upon being navigated to and selected.
     */
    void execute();
}
