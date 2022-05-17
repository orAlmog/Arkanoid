// or almog 315828178
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;

/**
 * @author or almog
 * This class defines a game environment based on a list of collidable objects,
 * and a paddle to make it easier for the ball to be aware of it.
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidableList;
    private Paddle paddle;

    /**
     * A constructor that creates a game environment by setting a new list
     * for the collidable objects.
     */
    public GameEnvironment() {
        collidableList = new ArrayList<>();
    }

    /**
     * A method that adds a collidable to the environment's collidable
     * list.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * A method that returns the environment's collidable list.
     *
     * @return the collidable list
     */
    public java.util.List<Collidable> getCollidableList() {
        return collidableList;
    }

    /**
     * A method that returns the collision info of the closest
     * collision point to the start of a trajectory line.
     *
     * @param trajectory the line
     * @return the closest collision point to the start of the line if it exists,
     * null otherwise
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
    Point closestCollision = null;
    Point closestRecPoint = null;
    Collidable collidableObject = null;
    for (Collidable collidable : collidableList) {
        // if there are items in the list
        if (collidable.getCollisionRectangle().intersectionPoints(trajectory).size() != 0) {
            closestRecPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            //if there are no items on the list
            if (closestRecPoint == null) {
                continue;
            }
            if (closestCollision == null) {
                closestCollision = closestRecPoint;
                collidableObject = collidable;
                continue;
            }
            if (trajectory.start().distance(closestCollision)
                    > trajectory.start().distance(closestRecPoint)) {
                closestCollision = closestRecPoint;
                collidableObject = collidable;
            }
        }
    }
    if (closestCollision == null) {
        return null;
    }
    return new CollisionInfo(collidableObject, closestCollision);
    }

    /**
     * A method that sete a paddle to this game environment.
     *
     * @param gamePaddle the paddle
     */
    public void setPaddle(Paddle gamePaddle) {
        this.paddle = gamePaddle;
    }

    /**
     * A method that return this game environment's paddle.
     *
     * @return the paddle
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * A method that removes a collidable from the collidable list.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }
}
