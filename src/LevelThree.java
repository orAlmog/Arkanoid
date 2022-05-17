// or almog 315828178
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author or almog
 * This class defines level one as a level of the game, meaning that it contains
 * the characteristics of the level itself.
 * implements level information.
 */
public class LevelThree implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new LinkedList<>();
        Velocity v1 = new Velocity(3, -4);
        Velocity v2 = new Velocity(-3, -4);
        list.add(v1);
        list.add(v2);
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        Random rand = new Random();
        List<Block> blocks = new LinkedList<>();
        // a loop to set up the blocks with random colors
        for (int i = 12; i > 0; i--) {
            if (i == 6) {
                break;
            }
            // getting a random color
            float red = rand.nextFloat();
            float green = rand.nextFloat();
            float blue = rand.nextFloat();
            Color randomColor = new Color(red, green, blue);
            for (int j = i; j > 0; j--) {
                blocks.add(new Block(new Rectangle(new Point(780 - 50 * j, 140 + 25 * (12 - i)),
                        50, 25), randomColor));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 57;
    }

    @Override
    public List<Point> initialBallPoints() {
        Point point1 = new Point(500, 450);
        Point point2 = new Point(300, 450);
        List<Point> ballsPoints = new LinkedList<>();
        ballsPoints.add(point1);
        ballsPoints.add(point2);
        return ballsPoints;
    }
}