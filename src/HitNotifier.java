// or almog 315828178
/**
 * @author or almog
 * This interface defines a hit notifier as an object that can
 * add hit listeners and remove them.
 */
public interface HitNotifier {

    /**
     * A method that adds a hit listener to the object.
     *
     * @param hl the hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * A method that removes a hit listener to the object.
     *
     * @param hl the hit listener
     */
    void removeHitListener(HitListener hl);
}
