// or almog 315828178
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author or almog
 * This class defines an end screen as a screen that shows up when
 * the game ends.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private boolean victory;

    /**
     * A constructor that creates an end screen based on keyboard sensor,
     * a counter and victory check to see if it should be a winning screen
     * or a loosing screen.
     *
     * @param k the keyboard sensor
     * @param score the counter
     * @param victory the victory check (true\ false)
     */
    public EndScreen(KeyboardSensor k, Counter score, boolean victory) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.victory = victory;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.victory) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
        }
//        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }
    @Override
    public boolean shouldStop() { return true; }
}