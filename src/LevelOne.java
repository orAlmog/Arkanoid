// or almog 315828178
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author or almog
 * This class defines level one as a level of the game, meaning that it contains
 * the characteristics of the level itself.
 * implements level information.
 */
public class LevelOne implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new LinkedList<>();
        list.add(new Velocity(0, -4));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 25;
    }

    @Override
    public int paddleWidth() {
        return 130;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<>();
        blocks.add(new Block(new Rectangle(new Point(375, 100), 50, 50), Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public List<Point> initialBallPoints() {
        List<Point> ballPoints = new LinkedList<>();
        ballPoints.add(new Point(400, 500));
        return ballPoints;
    }
}
