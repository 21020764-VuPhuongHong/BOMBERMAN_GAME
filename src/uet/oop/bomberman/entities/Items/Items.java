package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public abstract class Items extends Entity {
    public Items(int x, int y, Image img) {
        super(x, y, img);
    }

    private boolean isCovered = true;
    private boolean isReceived = false;

    public boolean getCoveredState() {
        return isCovered;
    }

    public boolean getReceivedState() {
        return isReceived;
    }

    protected void changeCoveredState() {
        if (BombermanGame.killedEntities[this.getX() / SCALED_SIZE][this.getY() / SCALED_SIZE] == 1) {
            isCovered = false;
        }
    }

    protected boolean changeReceivedState() {
        if (!isCovered && BombermanGame.bomberman.getX() / SCALED_SIZE == this.getX() / SCALED_SIZE
                && BombermanGame.bomberman.getY() / SCALED_SIZE == this.getY() / SCALED_SIZE) {
            isReceived = true;
            return true;
        }
        return false;
    }
}
