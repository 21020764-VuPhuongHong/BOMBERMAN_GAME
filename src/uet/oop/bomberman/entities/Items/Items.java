package uet.oop.bomberman.entities.Items;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.CollisionHandle;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;

public abstract class Items extends Entity {
    protected boolean isReceived = false;

    public Items(int x, int y, Image img) {
        super(x, y, img);
    }

    protected boolean changeReceivedState() {
        javafx.geometry.Rectangle2D rec1 = CollisionHandle.getBoundary(BombermanGame.bomberman);
        javafx.geometry.Rectangle2D rec2 = new Rectangle2D(rec1.getMinX(), rec1.getMinY(), rec1.getWidth()*3/4, rec1.getHeight());

        if (CollisionHandle.intersects(this, rec2)) {
            isReceived = true;
            return true;
        }
        return false;
    }
}
