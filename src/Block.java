// or almog 315828178
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author or almog
 * This class defines a block as an object based on a rectangle shape
 * and color, with extra features.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * A constructor that creates a block based on given rectangle
     * and color.
     *
     * @param rectangle the rectangle of the block
     * @param color the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new LinkedList<>();
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = null;
        // if the item touches the bottom or top line of the rectangle
        if ((this.rectangle.getTopLine().isInLine(collisionPoint))
            || (this.rectangle.getBottomLine().isInLine(collisionPoint))) {
            // changing the dy direction
            newVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // if the item touches the left or right line of the rectangle
        if ((this.rectangle.getRightLine().isInLine(collisionPoint))
                || (this.rectangle.getLeftLine().isInLine(collisionPoint))) {
            // changing the dx direction
            newVelocity = new Velocity(-currentVelocity.getDx(),
                                        currentVelocity.getDy());
        }
         this.notifyHit(hitter);
        return newVelocity;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY()
                                         , (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY()
                , (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void timePassed() {

    }

    /**
     * A method that remove a block as a sprite from a given game.
     *
     * @param gameLevel the game that contains the block
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * A method that notifies all the listeners of this block the it got hit
     * and sends the ball that hit it to the hitEvent method.
     *
     * @param hitter the ball that hits the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
