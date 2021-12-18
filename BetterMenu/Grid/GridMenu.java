package Misc.CustomClasses.BetterMenu.Grid;

import Misc.CustomClasses.BetterMenu.Direction;

import java.util.LinkedList;

/**
 * A GridMenu that may be navigated through.
 *
 * @param <I> Object that extends <code>Item</code>
 * @author Jacob Swineford
 */
public class GridMenu<I extends Item> {

    private LinkedList<MenuRow<I>> rows;
    private I current;

    GridMenu() {
        rows = new LinkedList<>();
        current = null;
    }

    /**
     * Adds the given item to the specified row. If the row does not
     * exist in this menu and it is the next row to be built, then
     * it is automatically added with the item.
     *
     * @param item given item
     * @param row specified row
     */
    public void add(I item, int row) {
        try {
            MenuRow<I> r = rows.get(row);
            r.add(item);
        } catch (IndexOutOfBoundsException e) {
            rows.add(new MenuRow<>());
            MenuRow<I> r = rows.get(row);
            r.add(item);
        }
    }

    /**
     * Removes the given item from the specified row. If the item
     * does not exist in the specified row or the row does not exist,
     * this method does nothing. If the item is the last item in the row,
     * then the row is also removed.
     *
     * @param item given item
     * @param row specified row
     */
    public void delete(I item, int row) {
        try {
            MenuRow<I> r = rows.get(row);
            r.remove(item);
            if (r.isEmpty()) rows.remove(r);
            if (current == item) current = null;
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }

    /**
     * Selects the given item in this menu by invoking it's <code>navigatedOn()</code>
     * method, and the currently selected item's <code>navigatedOff()</code> if it
     * not equal to null. The given item is also set to be the currently selected item.
     *
     * @param item given item
     */
    public void select(I item) {
        try {
            if (this.contains(item)) {
                if (current != null) current.navigatedOff(null);
                current = item;
                current.navigatedOn(null);
            }
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }

    /**
     * Navigates through this menu using the given direction. An item that
     * is currently selected, given a valid direction to move in, will
     * be deselected using it's <code>navigatedOff()</code> method.
     * The item navigated to will become the currently selected item,
     * and become selected using it's <code>navigatedOn()</code> method.
     *
     * @param direction direction to navigate
     */
    public void navigate(Direction direction) {
        Item temp = current;
        MenuRow<I> row = currentRow(); if (row == null) return;
        int cI = row.indexOf(current);
        switch (direction) {
            case Right: {
                if (row.next(current) == null) return;
                current = row.next(current);
                break;
            }
            case Left: {
                if (row.previous(current) == null) return;
                current = row.previous(current);
                break;
            }
            case Up: {
                try {
                    MenuRow<I> n = rows.get(rows.indexOf(row) - 1);
                    if (n.get(cI) == null) return;
                    current = n.get(cI);
                } catch (IndexOutOfBoundsException e) {
                    return;
                }
                break;
            }
            case Down: {
                try {
                    MenuRow<I> n = rows.get(rows.indexOf(row) + 1);
                    if (n.get(cI) == null) return;
                    current = n.get(cI);
                } catch (IndexOutOfBoundsException e) {
                    return;
                }
                break;
            }
        }
        current.navigatedOn(direction);
        temp.navigatedOff(direction);
    }

    /**
     * Executes the currently selected item if it is not equal to null.
     */
    public void execute() {if (current != null) current.execute();}

    /**
     * Returns whether the given item is in this list or not.
     *
     * @param item given item
     * @return true if so, false otherwise
     */
    public boolean contains(I item) {
        for (MenuRow<I> row : rows) {
            if (row.contains(item)) return true;
        }
        return false;
    }

    /**
     * Returns the row that is currently holding the currently selected item.
     *
     * @return <code>MenuRow</code> that contains the selected item. null otherwise
     */
    private MenuRow<I> currentRow() {
        for (MenuRow<I> row : rows) {
            if (row.contains(current))
                return row;
        }
        return null;
    }

    /**
     * A row for a menu. Rows of a menu may be treated in this class as
     * a normal list, but with convenience methods for checking movements
     * along the items in the menu.
     *
     * @param <E> Object that extends <code>Item</code>
     * @author Jacob Swineford
     */
    private class MenuRow<E extends Item> {

        private LinkedList<E> items = new LinkedList<>();

        void add(E item) {items.add(item);}
        void remove(E item) {items.remove(item);}
        boolean contains(E item) {return items.contains(item);}
        boolean isEmpty() {return items.isEmpty();}

        /**
         * Gets the next item in this row, given the current item.
         * It is implicitly trusted that this row contains the given item.
         *
         * @param current current item
         * @return The next item in this list, null otherwise
         */
        E next(E current) {
            try {
                return items.get(items.indexOf(current) + 1);
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }

        /**
         * Gets the previous item in this row, given the current item.
         * It is implicitly trusted that this row contains the given item.
         *
         * @param current current item
         * @return The previous item in this list, null otherwise
         */
        E previous(E current) {
            try {
                return items.get(items.indexOf(current) - 1);
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }

        /**
         * Gets the item at the specified index in this row.
         *
         * @param index given index
         * @return The item at the specified index
         */
        E get(int index) {
            try {
                return items.get(index);
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }

        /**
         * Gets the index of the given item in this row.
         *
         * @param item given item to check for
         * @return The index of the given item
         */
        int indexOf(E item) {
            return items.indexOf(item);
        }

    }

}
