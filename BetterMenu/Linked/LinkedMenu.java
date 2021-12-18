package Misc.CustomClasses.BetterMenu.Linked;

import Misc.CustomClasses.BetterMenu.Direction;

import java.util.List;

/**
 * Menu that manages linked menu items.
 *
 * @author Jacob Swineford
 */
public class LinkedMenu {

    private Misc.CustomClasses.BetterMenu.Linked.LinkedItem current;

    public void select(LinkedItem item) {
        current = item;
        current.navigatedOn(null);
    }

    /**
     * Executes the current menu item.
     */
    public void execute() {
        current.execute();
    }


    // has a bug
    /**
     * Navigates through this list in the given direction. The item that was selected
     * off from will execute it's <code>navigatedOff</code> method and the item navigated
     * onto will execute it <code>navigatedOn</code> method.
     *
     * @param direction direction used for navigation
     */
    public void navigate(Direction direction) {
        if (current == null) return;
        LinkedItem temp = current;
        switch (direction) {
            case Right: {
                if (current.getRight() != null) current = current.getRight();
                break;
            }
            case Left: {
                if (current.getLeft() != null) current = current.getLeft();
                break;
            }
            case Up: {
                if (current.getTop() != null) current = current.getTop();
                break;
            }
            case Down: {
                if (current.getBottom() != null) current = current.getBottom();
                break;
            }
        }
        temp.navigatedOff(direction);
        current.navigatedOn(direction.opposite());
    }

    /**
     * Links the given list of <code>LinkedItem</code> into a row.
     *
     * @param wrap whether wrap should be set for this row
     * @param items given items to link into a row
     */
    public static void linkRow(boolean wrap, LinkedItem... items) {
        if (items.length <= 1) return;
        LinkedItem first = items[0];
        LinkedItem last = items[items.length - 1];
        first.setRight(items[1]);
        if (wrap) items[0].setLeft(last);
        for (int i = 1; i < items.length - 1; i++) {
            items[i].setRight(items[i + 1]);
            items[i].setLeft(items[i - 1]);
        }
        last.setLeft(items[items.length - 2]);
        if (wrap) last.setRight(first);
    }

    /**
     * Links the given list of <code>LinkedItem</code> into a row.
     *
     * @param wrap whether wrap should be set for this row
     * @param items given items to link into a row
     */
    public static void linkRow(boolean wrap, List<LinkedItem> items) {
        if (items.size() <= 1) return;
        LinkedItem first = items.get(0);
        LinkedItem last = items.get(items.size() - 1);
        first.setRight(items.get(1));
        if (wrap) items.get(0).setLeft(last);
        for (int i = 1; i < items.size() - 1; i++) {
            items.get(i).setRight(items.get(i + 1));
            items.get(i).setLeft(items.get(i - 1));
        }
        last.setLeft(items.get(items.size() - 2));
        if (wrap) last.setRight(first);
    }

    /**
     * Links the given list of <code>LinkedItem</code> into a column.
     *
     * @param wrap whether wrap should be set for this column
     * @param items given items to link into a row
     */
    public static void linkColumn(boolean wrap, LinkedItem... items) {
        if (items.length <= 1) return;
        LinkedItem first = items[0];
        LinkedItem last = items[items.length - 1];
        first.setBottom(items[1]);
        if (wrap) items[0].setTop(last);
        for (int i = 1; i < items.length - 1; i++) {
            items[i].setBottom(items[i + 1]);
            items[i].setTop(items[i - 1]);
        }
        last.setTop(items[items.length - 2]);
        if (wrap) last.setBottom(first);
    }

    /**
     * Links the given list of <code>LinkedItem</code> into a column.
     *
     * @param wrap whether wrap should be set for this column
     * @param items given items to link into a row
     */
    public static void linkColumn(boolean wrap, List<LinkedItem> items) {
        if (items.size() <= 1) return;
        LinkedItem first = items.get(0);
        LinkedItem last = items.get(items.size() - 1);
        first.setBottom(items.get(1));
        if (wrap) items.get(0).setTop(last);
        for (int i = 1; i < items.size() - 1; i++) {
            items.get(i).setBottom(items.get(i + 1));
            items.get(i).setTop(items.get(i - 1));
        }
        last.setTop(items.get(items.size() - 2));
        if (wrap) last.setBottom(first);
    }

    /**
     * Severs an item from the menu, and cuts all of it's connections.
     *
     * @param item given <code>LinkedItem</code>
     */
    public static void sever(LinkedItem item) {
        item.setTop(null);
        item.setBottom(null);
        item.setRight(null);
        item.setLeft(null);
    }
}
