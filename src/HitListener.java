// or almog 315828178
/**
 * @author or almog
 * This interface defines a hit listener as an object that observe
 * another object and activating a method based on a hit event.
 */
public interface HitListener {

    /**
     * A method that creates the event based on the hitter ball
     * and the block that is being hit.
     *
     * @param hitter the hitter ball
     * @param beingHit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
