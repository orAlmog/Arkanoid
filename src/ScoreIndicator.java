// or almog 315828178
import biuoop.DrawSurface;

/**
 * @author or almog
 * This class defines a score indicator as a counter that displays
 * the score. updated based on the counter.
 */
public class ScoreIndicator implements Sprite {
    private int xValue;
    private int yValue;
    private int size;
    private String string;
    private Counter score;

    /**
     * a constructor that creates a score indicator based on the upper
     * left point of the display, the size of the font, and the score counter.
     *
     * @param xValue the x value of the upper left point
     * @param yValue the y value of the upper left point
     * @param size the size of the font
     * @param score the score counter
     */
    public ScoreIndicator(int xValue, int yValue, int size, Counter score) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.size = size;
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.string = "Score: " + score.getValue();
    d.drawText(this.xValue, this.yValue, this.string, this.size);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
    g.addSprite(this);
    }
}
