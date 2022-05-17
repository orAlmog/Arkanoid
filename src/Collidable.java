// or almog 315828178

import geometry.Point;
import geometry.Rectangle;

/**
 * @author or almog
 * This interface defines the collidable interface as
 * any object you can collide into during a game.
 */
public interface Collidable {

    /**
     * A method that return the collision shape of the
     * object (for now it will be rectangle).
     *
     * @return the shape
     */
    Rectangle getCollisionRectangle();


    /**
     * A method that changes the object's speed according to the collision point
     * and the object that was collided with it.
     *
     * @param collisionPoint the collision point
     * @param currentVelocity the velocity of the moving object
     * @param hitter the ball that creates the hit
     *
     * @return the velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
