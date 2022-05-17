// or almog 315828178
/**
 * @author or almog
 * This class defines a score tracking listener as an observer
 * that updates the score based on hit events of blocks.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * a constructor that creates a score tracking listener based
     * on a score counter.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

       this.currentScore.increase(5);
    }
}