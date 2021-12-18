package Misc.CustomClasses.BetterMenu.LinkedImplement;

import Misc.CustomClasses.BetterMenu.Direction;
import Misc.CustomClasses.BetterMenu.Grid.Item;

import java.util.HashMap;

/**
 * A version of <code>LinkedMenu</code> that uses the general <code>Item</code>
 * Interface to represent it's linked menu items. This allows for use of this
 * class without having to extend <code>LinkedItem</code> for it's items, in exchange having
 * to implement <code>Item</code>.
 *
 * @author Jacob Swineford
 */
public class LinkedMenuI {

    private Item current;
    private HashMap<Item, Association> associations = new HashMap<>();

    /**
     * Selects the given item by setting it to this menu's current
     * item and calling it's <code>navigatedOn</code> method.
     *
     * @param item given item
     */
    public void select(Item item) {
        current = item;
        current.navigatedOn(null);
    }

    /**
     * Associates an item to a given base item, using the given direction as a
     * guide. That is, now the associated item is linked to the base from the
     * given direction.
     *
     * Example:
     * menu.setNeighbor(base, associating, Direction.Left);
     * where the associating item is now to the left of the base item.
     *
     * @param base item to use as a base
     * @param toAssociate item to associate using the given direction
     * @param direction given direction
     */
    public void setNeighbor(Item base, Item toAssociate, Direction direction) {
        if (associations.get(base) == null) {
            associations.put(base, new Association());
        }

        Association a = associations.get(base);
        switch (direction) {
            case Left: {
                a.left = toAssociate;
                break;
            }
            case Right: {
                a.right = toAssociate;
                break;
            }
            case Up: {
                a.top = toAssociate;
                break;
            }
            case Down: {
                a.bottom = toAssociate;
                break;
            }
        }
    }

    /**
     * Executes the current item by calling it's <code>execute</code>
     * method. If there is no current item, then the method is not called.
     */
    public void execute() {
        if (current != null) current.execute();
    }

    /**
     * Navigates through the menu using the given direction and the current
     * item's associations. If there is no association associated with the
     * current item, or the current item is null, this method does nothing.
     *
     * The item that was navigated to is now the current item, and invokes
     * it's <code>navigatedOn</code> method after the previous item invokes
     * it's <code>navigatedOff</code> method.
     *
     * @param direction given direction
     */
    public void navigate(Direction direction) {
        if (current == null) return;
        Association a = associations.get(current);
        if (a == null) return;

        Item temp = current;
        boolean navigationBit = false;
        switch (direction) {
            case Right: {
                if (a.right != null) {
                    current = a.right;
                    navigationBit = true;
                }
                break;
            }
            case Left: {
                if (a.left != null) {
                    current = a.left;
                    navigationBit = true;
                }
                break;
            }
            case Up: {
                if (a.top != null) {
                    current = a.top;
                    navigationBit = true;
                }
                break;
            }
            case Down: {
                if (a.bottom != null) {
                    current = a.bottom;
                    navigationBit = true;
                }
                break;
            }
        }

        if (navigationBit) {
            temp.navigatedOff(direction);
            current.navigatedOn(direction.opposite());
        } else {
            current.navigatedOff(direction);
        }
    }

    /**
     * Returns the <code>Association</code> object associated with this item.
     * A null value may be returned for items that are not associated with this menu.
     *
     * @param item given item
     * @return <code>Association</code> object associated with the given item. Null
     *         if it does not exist
     */
    public Association getAssociationFor(Item item) {
        return associations.get(item);
    }
}

