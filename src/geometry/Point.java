// or almog 315828178
package geometry;
/**
 * @author or almog
 * This class defines a point as two double values (x,y) with
 * different methods related to that.
 */
public class Point {
    private double x;
    private double y;

    /**
     * A constructor that sets a point based on two double values.
     *
     * @param  x as double value.
     * @param y as double value.
     */
    public Point(double x, double y) {
         this.x = x;
         this.y = y;
    }

    /**
     * A method that returns the distance of this point to the other point.
     *
     * @param other different point.
     * @return distance
     */
    public double distance(Point other) {
        /* getting the square root of the distance's powers
           just like the pythagorean formula
         */
        double distance = Math.sqrt(Math.pow((this.getX() - other.getX()), 2)
                + Math.pow((this.getY() - other.getY()), 2));
        return distance;
    }

    /**
     * A method that checks if a point is equal to another.
     *
     * @param other different point.
     * @return true if the points are equal and false otherwise
     */
    public boolean equals(Point other) {
        if ((this.getX() == other.getX()) && (this.getY() == other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * A method that returns the x and y values of this point.
     * @return x the x value of the point
     */
    public double getX() {
        return x;
    }

    /**
     * A method that returns the x and y values of this point.
     * @return y the y value of the point
     */
    public double getY() {
        return y;
    }
}
