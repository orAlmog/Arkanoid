// or almog 315828178
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author or almog
 * This class defines pause screen as a screen that pops up after
 * pressing a specific button, and close after pressing the same button another time.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * a constructor that creates a pause screen based on a given
     * keyboard sensor.
     *
     * @param k the keyboard sensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() { return true; }
}
