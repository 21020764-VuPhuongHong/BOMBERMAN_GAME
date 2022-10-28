package uet.oop.bomberman.entities.items;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.CollisionHandle;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.ConfigLevel;

public abstract class Items extends Entity {
    protected boolean isReceived = false;
    public static boolean[][] isUncovered = new boolean[ConfigLevel.width][ConfigLevel.height];

    public Items(int x, int y, Image img) {
        super(x, y, img);
    }

    protected boolean changeReceivedState() {
        if (isUncovered[this.getX() / Sprite.SCALED_SIZE][this.getY() / Sprite.SCALED_SIZE]) {
            javafx.geometry.Rectangle2D rec1 = CollisionHandle.getBoundary(BombermanGame.bomberman);
            javafx.geometry.Rectangle2D rec2 = new Rectangle2D(rec1.getMinX(), rec1.getMinY(), rec1.getWidth() * 3 / 4, rec1.getHeight());

            if (CollisionHandle.intersects(this, rec2)) {
                isReceived = true;
                return true;
            }
        }
        return false;
    }
}
