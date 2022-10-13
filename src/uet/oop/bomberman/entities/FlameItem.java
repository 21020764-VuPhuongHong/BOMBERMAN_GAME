package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class FlameItem extends Items {
    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        if (getCoveredState()) {
            changeCoveredState();
        }

        if (!getReceivedState() && changeReceivedState()) {
            Bomb.explodingLength = 2;
            BombermanGame.entities.remove(this);
        }
    }
}
