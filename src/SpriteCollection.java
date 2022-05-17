// or almog 315828178
import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * @author or almog
 * This class defines a a collection of sprites as a list that has the ability
 * to add sprites, draw them all, and let them all know that time has passed.
 */
public class SpriteCollection {
    private java.util.List<Sprite> list;

    /**
     * A constructor that creates the collection as a list.
     */
    public SpriteCollection() {
        this.list = new ArrayList<>();
    }

    /**
     * A method that adds a given sprite to the list of sprites.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        list.add(s);
    }

    /**
     * A method that lets all the sprites know that time has passed.
     */
    public void notifyAllTimePassed() {
        // creating a copy of the original list so we could change it while iterating
        java.util.List<Sprite> list2 = new ArrayList<>(this.list);
        for (Sprite s : list2) {
            s.timePassed();
        }
    }

    /**
     * A method that draws all the sprites that are in the list on a
     * given surface.
     *
     * @param d the surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : list) {
            s.drawOn(d);
        }
    }

    /**
     * A method that removes a given sprite from the sprite list.
     *
     * @param s the given sprite
     */
    public void removeSprite(Sprite s) {
        this.list.remove(s);
    }
}
