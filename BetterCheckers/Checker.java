package Misc.CustomClasses.BetterCheckers;

import java.util.LinkedList;

/**
 * Interface defining methods that a checker piece needs to have to be used
 * in the <code>Checkers</code> engine.
 *
 * @author Jacob Swineford
 */
abstract class Checker {

    private int type;

    Checker(int type) {
        this.type = type;
    }
    int getType() {return type;}

    /**
     * Returns a list of bias to be used by the <code>Checkers</code> engine
     * to determine the correct moves that this checker can make.
     *
     * @return a <code>LinkedList</code> of integer arrays in the format [x, y]
     *         that are the bias of movement from the original piece.
     */
    abstract LinkedList<int[]> moveBias();

    /**
     * Returns a list of bias to be used by the <code>Checkers</code> engine
     * to determine the correct attacks that this checker can make.
     *
     * @return a <code>LinkedList</code> of integer arrays in the format [x, y]
     *         that are the bias of attacks from the original piece.
     */
    LinkedList<int[]> attackBias() {
        LinkedList<int[]> bias = new LinkedList<>();
        bias.add(new int[] {-2, -2});
        bias.add(new int[] {-2, 2});
        bias.add(new int[] {2, -2});
        bias.add(new int[] {2, 2});
        return bias;
    }
}
