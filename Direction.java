package Misc.CustomClasses.BetterMenu;

/**
 * Directions that may be used for navigation.
 *
 * @author Jacob Swineford
 */
public enum Direction {
    Left,
    Right,
    Up,
    Down;

    public Direction opposite() {
        if (this == Left) return Right;
        if (this == Right) return Left;
        if (this == Up) return Down;
        return Up;
    }
}
