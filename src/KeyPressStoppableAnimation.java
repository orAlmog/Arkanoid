// or almog 315828178
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author or almog
 * This class defines a key press stoppable animation as an animtaion that
 * can be stopped by pressing a specific key.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * A constructor that creates a key press stoppable animation from
     * the following arguments.
     *
     * @param sensor the keyboard sensor
     * @param key the key that stops the animation
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(key)) {
            if (isAlreadyPressed) {
                return;
            }
            // if the key is not already pressed
            this.stop = true;
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}