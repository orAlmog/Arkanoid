// or almog 315828178
import geometry.Point;
import java.util.List;
/**
 * @author or almog
 * This interface defines a level information as an object that contains
 * the game level characteristics such as: number of balls, the blocks, etc...
 */
public interface LevelInformation {

    /**
     * A method that returns the number of balls in it.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * A method that returns the initial velocities of the balls.
     *
     * @return a list of the balls velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * A method that returns the speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * A method that returns the width of the paddle.
     *
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * A method that returns the name of the level as a string
     * in order to display it.
     *
     * @return the name of the level.
     */
    String levelName();

    /**
     * A method that returns the name of the level as a string
     * in order to display it.
     *
     * @return the name of the level.
     */
    Sprite getBackground();

    /**
     * A method that returns a list of blocks from which
     * the level is made from. contains the size, color and location
     * of the blocks.
     *
     * @return the list of the blocks.
     */
    List<Block> blocks();

    /**
     * A method that returns the number of blocks that need to
     * be removed before the current level is cleared.
     *
     * @return the number of blocks to be removed.
     */
    int numberOfBlocksToRemove();

    /**
     * A method that returns a list of points that are
     * the center of the balls that need to be placed on the surface
     * of the level.
     *
     * @return the list of the center points.
     */
    List<Point> initialBallPoints();
}