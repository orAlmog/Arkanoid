import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;

/**
 * @author or almog
 * This class defines a ball based on center point, radius,
 * color and velocity.
 * in addition, every ball has limits to where it can move
 * on a given surface.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    private int topLimit;
    private int bottomLimit;
    private int leftLimit;
    private int rightLimit;
    // a flag to see if the paddle added velocity to the ball
    private boolean addedPaddleVelocity;

    /**
     * A constructor that sets a ball based on center point,
     * radius and color.
     *
     * @param center center point.
     * @param r the radius.
     * @param color the color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.addedPaddleVelocity = false;
    }

    /**
     * A constructor that sets a ball based on center point
     * that is received with two double values (x,y),
     * radius and color.
     *
     * @param x the x value of the center point.
     * @param y the y value of the center point.
     * @param r the radius.
     * @param color the color.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * A method that returns the x value of the ball's center point.
     *
     * @return int
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * A method that returns the y value of the ball's center point.
     *
     * @return int
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * A method that returns the size of the ball (radius).
     *
     * @return int
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * A method that returns the color of the ball.
     *
     * @return Color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * A method that draw the ball on a given surface.
     *
     * @param surface the given surface.
     */
    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), this.radius);
    }
    /**
     * A method that sets the velocity of this ball.
     *
     * @param v2 the velocity set.
     */
    public void setVelocity(Velocity v2) {
        this.v = v2;
    }

    /**
     * A method that sets the velocity of this ball
     * based on the double values of the velocity (dx, dy).
     *
     * @param dx the dx value of the velocity set.
     * @param dy the dy value of the velocity set.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * A method that returns the velocity of this ball.
     *
     * @return Velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * A method that returns the center point of this ball.
     *
     * @return geometry.Point
     */
    public Point getCenter() {
            return this.center;
    }

    /**
     * A method that sets the limits of this ball for any given
     * surface.
     *
     * @param topLimit2 the max vertical limit
     * @param bottomLimit2 the min vertical limit
     * @param leftLimit2 the max horizontal limit
     * @param rightLimit2 the min horizontal limit
     */
    public void setLimits(int topLimit2, int bottomLimit2, int leftLimit2,
                     int rightLimit2) {
        this.topLimit = topLimit2;
        this.bottomLimit = bottomLimit2;
        this.leftLimit = leftLimit2;
        this.rightLimit = rightLimit2;
    }

    /**
     * A method that moves the ball one step based on his location
     * (the center point) and based on his speed.
     * the ball change the direction of his speed when it reaches
     * any of the borders, meaning that the ball bounce back.
     */
    public void moveOneStep() {
        double paddleVelocity = this.gameEnvironment.getPaddle().getDx();
        // if the paddle velocity was already added
        if (this.addedPaddleVelocity) {
            paddleVelocity = 0;
        }
        // checking if the ball is in the paddle
        if (this.gameEnvironment.getPaddle().getRectangle().isInRec(this.center)) {
            // getting the upper left and upper right corners of the paddle
            Point upperLeft = this.gameEnvironment.getPaddle().getRectangle().getUpperLeft();
            Point upperRight = new Point(upperLeft.getX()
                    + this.gameEnvironment.getPaddle().getRectangle().getWidth(), upperLeft.getY());
            // if the ball is closer to the upper left point more than the right one
            if (upperLeft.distance(this.center) < upperRight.distance(this.center)) {
                this.center = new Point(upperLeft.getX() - this.radius, this.center.getY());
                // applying more velocity to the hit direction of the paddle
                this.v = new Velocity(-paddleVelocity - Math.abs(this.v.getDx()),
                        this.v.getDy());
            } else {
                this.center = new Point(upperRight.getX() + this.radius, this.center.getY());
                // adding the dx value of the paddle to the ball
                this.v = new Velocity(paddleVelocity + Math.abs(this.v.getDx()),
                        this.v.getDy());
            }
            this.addedPaddleVelocity = true;
        }
        Line trajectory = new Line(this.center, this.v.applyToPoint(this.center));
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
        Velocity newVelocity;
        // if there is no collision
        if (info == null) {
            this.center = this.v.applyToPoint(this.center);
            return;
        } else {
            newVelocity = info.collisionObject().hit(this, info.collisionPoint(), this.v);
            this.v = newVelocity;
            // checking if there is another hit coming right after the previous one
            trajectory = new Line(this.center, this.v.applyToPoint(this.center));
            info = this.gameEnvironment.getClosestCollision(trajectory);
            if (info != null) {
                newVelocity = info.collisionObject().hit(this, info.collisionPoint(), this.v);
                this.v = newVelocity;
            }
            this.center = this.v.applyToPoint(this.center);
        }
        return;
    }

    /**
     * A method that sets the ball a given game environment.
     *
     * @param environment the game environment
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * A method that removes a ball as a sprite from a given game.
     *
     * @param gameLevel the game that contains the ball
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
