// or almog 315828178
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author or almog
 * This class defines animation runner as an object that runs a given animation
 * on a given gui. requires the sleeper and also the frames per second.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * A constructor that creates an animation runner based on gui and sleeper.
     *
     * @param gui the gui
     * @param sleeper the sleeper
     */
    public AnimationRunner(GUI gui, Sleeper sleeper) {
    this.gui = gui;
    this.sleeper = sleeper;
    this.framesPerSecond = 60;
    }

    /**
     * A method that runs an animation that is given in the function.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}