package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class SpeedItem extends Items {
    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        if (getCoveredState()) {
            changeCoveredState();
        }

        if (!getReceivedState() && changeReceivedState()) {
            BombermanGame.step *= 2;
            BombermanGame.entities.remove(this);
        }
    }
}
