// or almog 315828178
import biuoop.DrawSurface;

/**
 * @author or almog
 * This class defines a level indicator as a sprite that displays the
 * current level name.
 */
public class LevelIndicator implements Sprite {
    private LevelInformation levelInformation;
    private int xValue;
    private int yValue;
    private int size;
    private String string;

    /**
     * a constructor that creates a level indicator based on the upper
     * left point of the display, the size of the font, and the level information.
     *
     * @param xValue the x value of the upper left point
     * @param yValue the y value of the upper left point
     * @param size the size of the font
     * @param levelInformation the level information
     */
    public LevelIndicator(int xValue, int yValue, int size, LevelInformation levelInformation)
    {
        this.levelInformation = levelInformation;
        this.xValue = xValue;
        this.yValue = yValue;
        this.size = size;
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.string = "Level Name: " + levelInformation.levelName();
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
