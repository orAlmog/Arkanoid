// or almog 315828178
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author or almog
 * This class defines level two as a level of the game, meaning that it contains
 * the characteristics of the level itself.
 * implements level information.
 */
public class LevelTwo implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new LinkedList<>();
        Velocity v1 = new Velocity(4, -4);
        Velocity v2 = new Velocity(-4, -4);
        for (int i = 0; i < this.numberOfBalls(); i++) {
            if (i < 5) {
                list.add(v1);
                continue;
            }
            list.add(v2);
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 650;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new LinkedList<>();
        Color[] colors = new Color[15];
        for (int i = 0; i < 15; i++) {
            if (i < 2) {
                colors[i] = Color.cyan;
            } else if (i < 4) {
                colors[i] = Color.pink;
            } else if (i < 6) {
                colors[i] = Color.BLUE;
            } else if (i < 9) {
                colors[i] = Color.GREEN;
            } else if (i < 11) {
                colors[i] = Color.YELLOW;
            } else if (i < 13) {
                colors[i] = Color.ORANGE;
            } else {
                colors[i] = Color.RED;
            }
        }
        // creating blocks with colors from colors array
        for (int i = 0; i < 14; i++) {
            blocks.add(new Block(new Rectangle(new Point(780 - 51 * (i + 1), 250), 51, 25), colors[i]));
        }
        blocks.add(new Block(new Rectangle(new Point(20, 250), 46, 25), Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public List<Point> initialBallPoints() {
        List<Point> ballsPoints = new LinkedList<>();
        Point[] points = new Point[10];
        for (int i = 0; i < 5; i++) {
            points[i] = new Point(425 + 25 * i, 400 + 10 * i);
            ballsPoints.add(points[i]);
        }
        for (int i = 5; i < 10; i++) {
            points[i] = new Point(375 - 25 * (i - 5), 400 + 10 * (i - 5));
            ballsPoints.add(points[i]);
        }
        return ballsPoints;
    }
}