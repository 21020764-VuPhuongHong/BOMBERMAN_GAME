package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.CollisionHandle;
import uet.oop.bomberman.entities.Enemies.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.level.Level1;
import uet.oop.bomberman.level.Level2;
import uet.oop.bomberman.level.Level3;
import uet.oop.bomberman.ui.NextLevel;
import uet.oop.bomberman.ui.WinGame;

public class Portal extends Entity {

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        int countEnemy = 0;
        for (Entity e : BombermanGame.entities) {
            if (e instanceof Enemy) {
                countEnemy++;
            }
        }

        if (countEnemy == 0) {
            javafx.geometry.Rectangle2D rec1 = CollisionHandle.getBoundary(BombermanGame.bomberman);
            javafx.geometry.Rectangle2D rec2 = new Rectangle2D(rec1.getMinX(), rec1.getMinY(), rec1.getWidth() * 3 / 4, rec1.getHeight());

            if (CollisionHandle.intersects(this, rec2)) {
                BombermanGame.soundControl.playMoveItem();
                NextLevel.nextLevel();
            }
        }
    }
}
