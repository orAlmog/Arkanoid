// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
/**
 * @author or almog
 * This class defines a block remover by setting it a game to listen to,
 * and a counter that says how many blocks are in it.
 * if all the blocks are cleared the level has been passed
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * A constructor that sets the game and the counter of the remaining blocks
     * for the block remover.
     *
     * @param gameLevel the game that it listens to
     * @param removedBlocks the remaining blocks in the game
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}