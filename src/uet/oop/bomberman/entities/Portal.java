package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.CollisionHandle;
import uet.oop.bomberman.entities.enemies.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.items.Items;
import uet.oop.bomberman.ui.NextLevel;

public class Portal extends Items {

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        if (BombermanGame.listEnemies.size() == 0 && !isReceived && changeReceivedState()) {
            BombermanGame.soundControl.playMoveItem();
            NextLevel.nextLevel();
        }
    }
}
