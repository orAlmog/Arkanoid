// or almog 315828178
import biuoop.DrawSurface;

/**
 * @author or almog
 * This interface defines a sprite as an object that can draw himself on a surface,
 * add himself to a game and that supports movement.
 */
public interface Sprite {
    /**
     * A method that draws the sprite on a given surface.
     *
     * @param d the surface
     */
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed
    /**
     * A method that notifies the object that time passes, meaning that
     * there is another frame coming and changes the object according to
     * it's speed or movement ability.
     * the method will stay empty if the object does not supposed to move
     */
    void timePassed();

    /**
     * A method that addes a sprite object to a given game object.
     *
     * @param g the game
     */
    void addToGame(GameLevel g);
}
