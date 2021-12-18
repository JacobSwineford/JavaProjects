package Misc.CustomClasses.Snake;

/**
 * @author Jacob Swineford
 */
class P2D {
    private double x;
    private double y;

    P2D(double x, double y) {this.x = x; this.y = y;}
    double getX() {return x;}
    double getY() {return y;}
    void setLocation(double x, double y) {this.x = x; this.y = y;}

}
