package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.CollisionHandle;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public abstract class Items extends Entity {
    protected boolean isReceived = false;

    public Items(int x, int y, Image img) {
        super(x, y, img);
    }

    protected boolean changeReceivedState() {
        if (CollisionHandle.intersects(this, BombermanGame.bomberman)) {
            isReceived = true;
            return true;
        }
        return false;
    }
}
