// or almog 315828178

import geometry.Point;

/**
 * @author or almog
 * This class defines Velocity that specifies the change in position
 * on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    /* using 90 degrees so the starting point will by the positive y line
     and that the measure of the degree is clockwise */
    private static final int RELATIVE_ANGLE = 90;

    /**
     * A constructor that sets a velocity based on the
     * change in position on the `x` and the `y` axes
     * (dx and dy).
     *
     * @param dx the 'x' axe velocity.
     * @param dy the 'y' axe velocity.
     */
    public Velocity(double dx, double dy) {
    this.dx = dx;
    this.dy = dy;
    }

    /**
     * A constructor that sets a velocity based on angle
     * and the velocity power (speed).
     *
     * @param angle the angle that determines the direction of the speed.
     * @param speed the velocity that is needed to be seperated to dx and dy.
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // switching to radians to work with trigonometric functions
        double radianAngle = Math.toRadians(angle - RELATIVE_ANGLE);
        double dx = speed * Math.cos(radianAngle);
        double dy = speed * Math.sin(radianAngle);
        return new Velocity(dx, dy);
    }

    /**
     * A method that gets a point and return the point after adding
     * the change in location based on the velocity.
     *
     * @param p the initial point
     * @return the point after the movement
     */
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * A method that returns the dx of this velocity.
     *
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * A method that returns the dy of this velocity.
     *
     * @return dy
     */
    public double getDy() {
        return this.dy;
    }
}