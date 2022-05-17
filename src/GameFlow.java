// or almog 315828178
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.util.List;

/**
 * @author or almog
 * This class defines a game flow as an object that links between game levels
 * so that we can launch several levels at once and play them one after the other.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;
    private Sleeper sleeper;

    /**
     * A constructor that creates a game flow based on given animation runner,
     * keyboard sensor, gui and sleeper.
     *
     * @param ks the keyboard sensor
     * @param ar the animation runner
     * @param gui the gui
     * @param sleeper the sleeper
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui,
                        Sleeper sleeper) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.sleeper = sleeper;
    }

    /**
     * A method that runs all levels that are in the given levels list.
     *
     * @param levels the list of levels
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter();
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner, this.gui, this.sleeper, score);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (!level.hasBalls()) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        this.keyboardSensor.SPACE_KEY, new EndScreen(this.keyboardSensor, score, false)));
                return;
            }

        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, this.keyboardSensor.SPACE_KEY,
                new EndScreen(this.keyboardSensor, score, true)));
    }
}