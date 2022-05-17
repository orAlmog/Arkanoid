// or almog 315828178
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * @author or almog
 * This class defines a game that includes mainly an environment and sprites.
 * the game has fields like gui and sleeper to show the environment and sprites.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * A constructor that creates a game based on all of the arguments it
     * receives upon call.
     *
     * @param levelInformation the level information
     * @param keyboardSensor the keyboard sensor
     * @param animationRunner the animation runner
     * @param gui the gui
     * @param sleeper the sleeper
     * @param score the score counter
     */
    public GameLevel(LevelInformation levelInformation , KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, GUI gui, Sleeper sleeper, Counter score) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.score = score;
        this.levelInformation = levelInformation;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.gui = gui;
        this.sleeper = sleeper;
        this.running = true;
    }

    /**
     * A method that adds a collidable to the collidable list.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * A method that adds a sprite to the sprite list.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * A method that initializes the game.
     * creates all the blocks, the paddle, the balls and every other related
     * thing to the game, and adding it to the game.
     */
    public void initialize() {
        Random rand = new Random();
        int paddleWidth = this.levelInformation.paddleWidth();
        List<Velocity> ballsVelocities = this.levelInformation.initialBallVelocities();
        // placing the paddle in the middle based on it's width
        Paddle paddle = new Paddle(new Rectangle(new Point(400 - (int) (paddleWidth / 2), 535),
                        paddleWidth, 25), Color.RED, keyboard, 20, 780, 25);
        paddle.addToGame(this);
        this.environment.setPaddle(paddle);
        // setting up the borders
        Block topBlock = new Block(new Rectangle(new Point(20, 0), 760, 40), Color.gray);
        Block scoreBlock = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.lightGray);
        Block rightBlock = new Block(new Rectangle(new Point(0, 0), 20, 650), Color.gray);
        Block leftBlock = new Block(new Rectangle(new Point(780, 0), 20, 650), Color.gray);
        Block deathBlock = new Block(new Rectangle(new Point(0, 600), 800, 40), Color.gray);
        topBlock.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        rightBlock.addToGame(this);
        leftBlock.addToGame(this);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);
        scoreBlock.addToGame(this);
        // adding score indicator for the score block
        ScoreIndicator scoreIndicator = new ScoreIndicator(325, 15, 15, score);
        scoreIndicator.addToGame(this);
        // adding level indicator to show the current level
        LevelIndicator levelIndicator = new LevelIndicator(600, 15, 15, this.levelInformation);
        levelIndicator.addToGame(this);
        Ball[] balls = new Ball[this.levelInformation.numberOfBalls()];
        List<Point> ballPoints = this.levelInformation.initialBallPoints();
        // loop for setting the balls based on their locations and velocities
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(ballPoints.get(i), 5, Color.black);
            balls[i].setVelocity(ballsVelocities.get(i));
            balls[i].setGameEnvironment(environment);
            balls[i].addToGame(this);
        }
        ballCounter.increase(balls.length);
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);
        List<Block> blocks = this.levelInformation.blocks();
        // a loop to set up the blocks with random colors
        for (int i = 0; i < this.levelInformation.numberOfBlocksToRemove(); i++) {
            blocks.get(i).addToGame(this);
            blocks.get(i).addHitListener(blockRemover);
            blocks.get(i).addHitListener(scoreListener);
            blockCounter.increase(1);
            }
        }

    /**
     * A method that runs the game through using the sleeper and
     * through notifying all the sprites that time passed.
     */
    public void run() {
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * A method that removes a collidable from the game's environment.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * A method that removes a Sprite from the game's sprite collection.
     *
     * @param s the Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    this.keyboard.SPACE_KEY, new PauseScreen(this.keyboard)));
        }
        if ((blockCounter.getValue() == 0) || (ballCounter.getValue() == 0)) {
            if (blockCounter.getValue() == 0) {
                // increasing the score by 100 if the player managed to clear an entire level
                this.score.increase(100);
            }
            this.running = false;
        }
        d.setColor(new Color(153, 255, 255));
        d.fillRectangle(0, 0, 800, 600);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * A method that checks if the game level has current balls that are active.
     *
     * @return true/ false
     */
    public boolean hasBalls() {
        if (this.ballCounter.getValue() == 0) {
            return false;
        }
        return true;
    }
}
