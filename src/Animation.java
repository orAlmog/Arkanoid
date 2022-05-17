import biuoop.DrawSurface;

/**
 * @author or almog
 * This interface defines animation as any object that you want
 * to show in an animation.
 */
public interface Animation {

    /**
     * A method that creates one frame at a time on a given surface.
     *
     * @param d the surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * A method that returns if the animation should stop or not.
     *
     * @return true\ false
     */
    boolean shouldStop();
}
