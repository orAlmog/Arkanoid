// or almog 315828178
package geometry;
/**
 * @author or almog
 * This class defines a line as two points (start and end) with
 * different methods related to that.
 */
public class Line {
    private final Point start;
    private final Point end;
    private Point middle;
    private double scale;
    private double yIntersection;
    private boolean isValidScale = false;

    /**
     * A constructor that gets 2 points as geometry.Point class.
     *
     * @param start the starting point
     * @param end the ending point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.middle = this.middle();
        if (start.getX() != end.getX()) {
            setScale();
            setyIntersection();
        }
    }
    /**
     * A constructor that gets 2 points as double x and y values
     * (x,y).
     *
     * @param x1 the x value of start point
     * @param y1 the y value of start point.
     * @param x2 the x value of end point
     * @param y2 the y value of end point
     */
    public Line(double x1, double y1, double x2, double y2) {
            start = new Point(x1, y1);
            end = new Point(x2, y2);
        this.middle = this.middle();
        if (x1 != x2) {
            setScale();
            setyIntersection();
        }
    }

    /**
     * A method that returns the length of the current line.
     *
     * @return the length of the current line in double value
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * A method that returns the middle point of the current line.
     *
     * @return geometry.Point middle
     */
    public Point middle() {
        // getting the middle values of the points
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;
        middle = new Point(x, y);
        return middle;
    }

    /**
     * A method that returns the start point of the current line.
     *
     * @return geometry.Point start
     */
    public Point start() {
        return this.start;
    }

    /**
     * A method that returns the end point of the current line.
     *
     * @return geometry.Point end
     */
    public Point end() {
        return this.end;
    }

    /**
     * A method that checks if this line intersect with another.
     *
     * @param other the other line
     * @return true if they intersect, and false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        if (this.scale == other.scale) {
            if (this.yIntersection != other.yIntersection) {
                return false;
            }
            if (other.isInsideLine(this.start)
                    || other.isInsideLine(this.end)) {
                return true;
            }
        }
        return false;
    }

    /**
     * A method that returns the intersection point this line
     * has with another line.
     *
     * @param other the other line
     * @return geometry.Point intersection if they intersect in one point,
     * null otherwise
     */
    public Point intersectionWith(Line other) {
        Point intersection;
        double x;
        double y;
        if (this.equals(other)) {
            return null;
        }
        /* if both of the lines are paralel to the y line
        or that the lines have similar scale */
        if (((!this.isValidScale) && (!other.isValidScale))
                || (this.isSameScale(other))) {
            //checking if there is one mutual dot
            if ((this.start.equals(other.start)
                    || this.start.equals(other.end))) {
                return this.start;
            } else if (this.end.equals(other.start)
                    || this.end.equals(other.end)) {
                return this.end;
            } else {
                return null;
            }
        }
        // if only the current line is paralel to the y line
        if (!this.isValidScale) {
            y = other.scale * this.start.getX() + other.yIntersection;
            intersection = new Point(this.start.getX(), y);
            if (this.isInLine(intersection) && other.isInLine(intersection)) {
                return intersection;
            }
        }
        // if only the other line is paralel to the y line
        if (!other.isValidScale) {
            y = this.scale * other.start.getX() + this.yIntersection;
            intersection = new Point(other.start.getX(), y);
            if (this.isInLine(intersection) && other.isInLine(intersection)) {
                return intersection;
            }
        }

        x = (other.yIntersection - this.yIntersection)
                / (this.scale - other.scale);
        y = this.scale * x + this.yIntersection;
        intersection = new Point(x, y);
        if (this.isInLine(intersection) && other.isInLine(intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * A method that checks if this line is equals to another line.
     *
     * @param other the other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        // checking if the two lines have the same equation and same length
        if ((this.start.equals(other.start)
                && this.end.equals(other.end))
                || (this.start.equals(other.end)
                && this.end.equals(other.start))) {
            return true;
        }
        return false;
    }

    /**
     * A method that checks if a point is in this line.
     *
     * @param p1 the point
     * @return true is the point is in this line, false otherwise
     */
    public boolean isInLine(Point p1) {
        // comparing the distances with epsilon
        double a = this.start.distance(p1) + p1.distance(this.end);
        double b = this.start.distance(this.end);
        double epsilon = Math.pow(10, -13);
        if (Math.abs(a - b) <= epsilon) {
            return true;
        }
        return false;
    }

    /**
     * A method that checks if a point is in this line
     * but not one of the edges.
     *
     * @param p1 the point
     * @return returns true if the point is in line but not in edges,
     * false otherwise
     */
    public boolean isInsideLine(Point p1) {
        // if the sum of the part lines is the length of the original line
        if (this.start.distance(p1) + p1.distance(this.end)
                == this.start.distance(this.end)) {
            // if the point is one of the edges
            if ((p1.equals(this.start)) || (p1.equals(this.end))) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * A method that set the scale of the line.
     * the valid scale flag will be true only for lines
     * that are not parallel to the y line.
     */
    // updates the scale of the line
    public void setScale() {
        scale = (this.start.getY() - this.end.getY())
                / (this.start.getX() - this.end.getX());
        isValidScale = true;
    }

    /**
     * A method that sets the y intersection, which is the scale of this
     * line (m) in the equation y = mx + n.
     */
    // updates the scale of the line
    public void setyIntersection() {
        yIntersection = this.start.getY() - this.start.getX() * this.scale;
    }

    /**
     * A method that returns the closest intersection point, with a given
     * rectangle, to the start of the line.
     *
     * @param rect the rectangle
     * @return returns the point if the line intersect with the rectangle,
     * null otherwise
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> list = rect.intersectionPoints(this);
        Point min = null;
        for (int i = 0; i < list.size(); i++) {
            if ((i == 0) || (min.distance(this.start) > list.get(i).distance(this.start))) {
                min = list.get(i);
            }
        }
        return min;
    }

    /**
     * A method that checks if 2 lines have the same scale.
     * this method is needed because for lines that are paralel to the y axe
     * are saved with the scale 0 as default.
     *
     * @param other the other line
     * @return returns true if the scales are similar,
     * false otherwise
     */
    public boolean isSameScale(Line other) {
        if ((this.scale == other.scale) && (this.isValidScale == other.isValidScale)) {
            return true;
        }
        return false;
    }
}