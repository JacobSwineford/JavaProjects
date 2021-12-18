package Misc.CustomClasses.BetterMenu.Linked;

import Misc.CustomClasses.BetterMenu.Direction;
import Misc.CustomClasses.BetterMenu.Grid.Item;

/**
 * A menu item that has links to other linked menu items. Any
 *
 * @author Jacob Swineford
 */
public abstract class LinkedItem implements Item {

    private LinkedItem right;
    private LinkedItem left;
    private LinkedItem top;
    private LinkedItem bottom;

    public LinkedItem getRight() {
        return right;
    }

    public void setRight(LinkedItem right) {
        this.right = right;
    }

    public LinkedItem getLeft() {
        return left;
    }

    public void setLeft(LinkedItem left) {
        this.left = left;
    }

    public LinkedItem getTop() {
        return top;
    }

    public void setTop(LinkedItem top) {
        this.top = top;
    }

    public LinkedItem getBottom() {
        return bottom;
    }

    public void setBottom(LinkedItem bottom) {
        this.bottom = bottom;
    }

    @Override
    public abstract void navigatedOn(Direction direction);

    @Override
    public abstract void navigatedOff(Direction direction);

    @Override
    public abstract void execute();
}
