// or almog 315828178
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author or almog
 * This class defines level four as a level of the game, meaning that it contains
 * the characteristics of the level itself.
 * implements level information.
 */
public class LevelFour implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new LinkedList<>();
        Velocity v1 = new Velocity(4.1, -4.1);
        Velocity v2 = new Velocity(-4.1, -4.1);
        Velocity v3 = new Velocity(0, -5.1);
        list.add(v1);
        list.add(v2);
        list.add(v3);
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<>();
        Color[] colors = new Color[7];
        colors[0] = Color.gray;
        colors[1] = Color.RED;
        colors[2] = Color.YELLOW;
        colors[3] = Color.GREEN;
        colors[4] = Color.LIGHT_GRAY;
        colors[5] = Color.pink;
        colors[6] = Color.cyan;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 14; j++) {
                blocks.add(new Block(new Rectangle(new Point(780 - 51 * (j + 1), 140 + 25 * i),
                        51, 25), colors[i]));
                if (j == 13) {
                    blocks.add(new Block(new Rectangle(new Point(20, 140 + 25 * i),
                            46, 25), colors[i]));
                }
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

    @Override
    public List<Point> initialBallPoints() {
        Point point1 = new Point(500, 450);
        Point point2 = new Point(300, 450);
        Point point3 = new Point(400, 400);
        List<Point> ballsPoints = new LinkedList<>();
        ballsPoints.add(point1);
        ballsPoints.add(point2);
        ballsPoints.add(point3);
        return ballsPoints;
    }
}