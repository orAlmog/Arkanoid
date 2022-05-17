// or almog 315828178
package geometry;
import java.util.ArrayList;

/**
 * @author or almog
 * This class defines a rectangle based on an upper left point,
 * height and width
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line topLine;
    private Line bottomLine;
    private Line leftLine;
    private Line rightLine;

    // Create a new rectangle with location and width/height.
    /**
     * A constructor that creates a rectangle from given upper left point
     * width and height.
     *
     * @param upperLeft the upper left point
     * @param width the width
     * @param height the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        defineLines();
    }

    /**
     * A method that defines the lines of the rectangle
     * in the proper fields.
     */
    private void defineLines() {
        double yHigh = this.upperLeft.getY();
        double yLow = this.upperLeft.getY() + height;
        double xLow = this.upperLeft.getX();
        double xHigh = this.upperLeft.getX() + width;
        this.topLine = new Line(this.upperLeft, new Point(xHigh, yHigh));
        this.bottomLine = new Line(xLow, yLow, xHigh, yLow);
        this.leftLine = new Line(this.upperLeft, new Point(xLow, yLow));
        this.rightLine = new Line(xHigh, yHigh, xHigh, yLow);
    }

    /**
     * A method that returns a list of intersection points with
     * a given line.
     *
     * @param line the line
     * @return the list (can be empty if there are no intersections)
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> list = new ArrayList<>();
        if (line.isIntersecting(this.topLine)) {
            Point p1 = line.intersectionWith(this.topLine);
            if (p1 != null) {
                list.add(p1);
            }
        }
        if (line.isIntersecting(this.bottomLine)) {
            Point p2 = line.intersectionWith(this.bottomLine);
            //if p2 is null or if the list contains p2 don't add p2
            if ((p2 != null) && (!isInList(p2, list))) {
                list.add(p2);
            }
        }
        if (line.isIntersecting(this.leftLine)) {
            Point p3 = line.intersectionWith(this.leftLine);
            if ((p3 != null) && (!isInList(p3, list))) {
                list.add(p3);
            }
        }
        if (line.isIntersecting(this.rightLine)) {
            Point p4 = line.intersectionWith(this.rightLine);
            if ((p4 != null) && (!isInList(p4, list))) {
                list.add(p4);
            }
        }
        return list;
    }

    /**
     * A method that checks if a point is in a list of points
     * (regarding the actual values and not just the object).
     *
     * @param p the point
     * @param list the list
     * @return true if the point is in the list,
     * false otherwise.
     */
    public boolean isInList(Point p, java.util.List<Point> list) {
        for (int i = 0; i < list.size(); i++) {
            if (p.equals(list.get(i))) {
                return true;
            }
        }
        return false;
    }


    /**
     * A method that returns the width of the rectangle.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * A method that returns the height of the rectangle.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * A method that returns the upper left point of the rectangle.
     *
     * @return the upper left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * A method that returns the top line of the rectangle.
     *
     * @return the top line
     */
    public Line getTopLine() {
        return this.topLine;
    }

    /**
     * A method that returns the bottom line of the rectangle.
     *
     * @return the bottom line
     */
    public Line getBottomLine() {
        return this.bottomLine;
    }

    /**
     * A method that returns the left line of the rectangle.
     *
     * @return the left line
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**
     * A method that returns the right line of the rectangle.
     *
     * @return the right line
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     * A method that sets the upper left point of the rectangle.
     *
     * @param upperLeftPoint the desired upper left point of the rectangle
     */
    public void setUpperLeft(Point upperLeftPoint) {
        this.upperLeft = upperLeftPoint;
        defineLines();
    }

    /**
     * A method that checks if a point is inside the rectangle.
     *
     * @param point the point
     *
     * @return true if the point is inside the rectangle,
     * false otherwise
     */
    public boolean isInRec(Point point) {
        // if the x value of the point is in the x range of the rectangle
        if ((point.getX() > this.upperLeft.getX()) && (point.getX() < (this.upperLeft.getX() + this.width))) {
            // if the y value of the point is in the y range of the rectangle
            if ((point.getY() > this.upperLeft.getY()) && (point.getY() < (this.upperLeft.getY() + this.width))) {
                return true;
            }
        }
        return false;
    }
}
