// or almog 315828178

import geometry.Point;

/**
 * @author or almog
 * This class defines a collision info as the collision point
 * and the collision object.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * A constructor that creates a collision info based on the object
     * and the collision point with the object.
     *
     * @param object the object that we are colliding with
     * @param collisionPoint the collision point
     */
    public CollisionInfo(Collidable object, Point collisionPoint) {
        this.collisionObject = object;
        this.collisionPoint = collisionPoint;
    }

    /**
     * A method that returns the collision point of this
     * collision info.
     *
     * @return the collision point
     */
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * A method that returns the collision object that is involved in
     * the collision.
     *
     * @return the collision object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
