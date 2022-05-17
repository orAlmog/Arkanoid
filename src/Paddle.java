// or almog 315828178
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * @author or almog
 * This class defines a paddle based on a rectangle, with speed
 * that allows the user to move the padde using the arrows on the keyboard.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private biuoop.GUI gui;
    private Color color;
    private int leftLimit;
    private int rightLimit;
    private double dx = 5;
    private Velocity leftVelocity = new Velocity(-dx, 0);
    private Velocity rightVelocity = new Velocity(dx, 0);

    /**
     * A constructor that creates a paddle.
     *
     * @param rectangle the shape of the paddle
     * @param  color the color of the paddle
     * @param keyboard the keyboard sensor of the paddle
     * @param leftLimit the left limit of the screen for the paddle
     * @param rightLimit the right limit of the screen for the paddle
     * @param dx the dx value of the paddle's velocity
     */
    public Paddle(Rectangle rectangle, Color color, biuoop.KeyboardSensor keyboard, int leftLimit, int rightLimit,
                  double dx) {
        this.rectangle = rectangle;
        this.keyboard = keyboard;
        this.color = color;
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
    }

    /**
     * A method that moves the paddle left.
     * once the paddle gets to the left border, it can't move any left than that
     */
    public void moveLeft() {
        Point newUpperLeft = this.leftVelocity.applyToPoint(this.rectangle.getUpperLeft());
        // if the paddle arrived the left border
        if (newUpperLeft.getX() <= this.leftLimit) {
            this.rectangle.setUpperLeft(new Point(leftLimit, newUpperLeft.getY()));
            return;
        }
        this.rectangle.setUpperLeft(newUpperLeft);
    }

    /**
     * A method that moves the paddle right.
     * once the paddle gets to the right border, it can't move any right than that
     */
    public void moveRight() {
        Point newUpperLeft = this.rightVelocity.applyToPoint(this.rectangle.getUpperLeft());
        // if the paddle arrived the right border
        if (newUpperLeft.getX() + this.rectangle.getTopLine().length() >= this.rightLimit) {
            // place the paddle so that it touches the right border
            this.rectangle.setUpperLeft(new Point(rightLimit - this.rectangle.getTopLine().length(),
                                                                                    newUpperLeft.getY()));
            return;
        }
        this.rectangle.setUpperLeft(newUpperLeft);
    }


    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            moveRight();
        }
        return;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY()
                , (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY()
                , (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // getting the velocity in a vector
        double velocityVector = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                                        + Math.pow(currentVelocity.getDy(), 2));
        Line topLine = this.rectangle.getTopLine();
        Line[] topLineParts = new Line[5];
        double partLength = topLine.length() / 5;
        // dividing the top line for 5 parts
        for (int i = 0; i < topLineParts.length; i++) {
            // initializing the lines through the upperLeft point of the rectangle
            topLineParts[i] = new Line(topLine.start().getX() + (i * partLength), topLine.start().getY(),
                             topLine.start().getX() + partLength + (i * partLength), topLine.start().getY());
        }
        if (topLine.isInLine(collisionPoint)) {
            for (int i = 0; i < topLineParts.length; i++) {
                // if the collision point is not on the part of the line
                if (!topLineParts[i].isInLine(collisionPoint)) {
                    continue;
                }
                // checking which part of the line the ball collided with
                if (i == 0) {
                    currentVelocity = Velocity.fromAngleAndSpeed(300, velocityVector);
                } else if (i == 1) {
                    currentVelocity = Velocity.fromAngleAndSpeed(330, velocityVector);
                } else if (i == 2) {
                    currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                } else if (i == 3) {
                    currentVelocity = Velocity.fromAngleAndSpeed(30, velocityVector);
                } else {
                    currentVelocity = Velocity.fromAngleAndSpeed(60, velocityVector);
                }
            }
            // changing the dx if the ball touch the sides of the paddle
        } else if ((this.rectangle.getLeftLine().isInLine(collisionPoint))
                || (this.rectangle.getRightLine().isInLine(collisionPoint))) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * A method that returns the rectangle of the paddle.
     *
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * A method that returns the dx value of the velocity of the paddle.
     *
     * @return the velocity dx
     */
    public double getDx() {
        return this.dx;
    }
}