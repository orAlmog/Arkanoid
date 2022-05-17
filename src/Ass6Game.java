// or almog 315828178

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.util.LinkedList;
import java.util.List;

/**
 * @author or almog
 * This class builds a game based on the game object.
 */
public class Ass6Game {
    /**
     * This main method creates the game and runs it.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new LinkedList<>();
        int[] argsArray = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            try {
            argsArray[i] = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                argsArray[i] = 0;
            }
        }
        for (int j : argsArray) {
            if (j == 1) {
                levels.add(new LevelOne());
            } else if (j == 2) {
                levels.add(new LevelTwo());
            } else if (j == 3) {
                levels.add(new LevelThree());
            } else if (j == 4) {
                levels.add(new LevelFour());
            }
        }
        GUI gui = new GUI("game", 800, 600);
        Sleeper sleeper = new Sleeper();
        AnimationRunner animationRunner = new AnimationRunner(gui, sleeper);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gui, sleeper);
        gameFlow.runLevels(levels);
        gui.close();
    }
}
