
/**
 * @author or almog
 * This class defines a ball remover by setting it a game to listen to,
 * and a counter that says how many balls are in it.
 * if a ball touches a killer brick, then the ball is out of the game.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * A constructor that sets the game and the counter of the remaining balls
     * for the ball remover.
     *
     * @param gameLevel the game that it listens to
     * @param remainingBalls the remaining balls in the game
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        this.remainingBalls.decrease(1);
    }
}
