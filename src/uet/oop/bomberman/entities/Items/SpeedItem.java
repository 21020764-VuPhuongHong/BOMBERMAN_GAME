package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Items {
    public SpeedItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        if (!isReceived && changeReceivedState()) {
            BombermanGame.bomberStep *= 2;
            BombermanGame.bomberman.setX(BombermanGame.bomberman.getX() - BombermanGame.bomberman.getX() % Sprite.SCALED_SIZE);
            BombermanGame.bomberman.setY(BombermanGame.bomberman.getY() - BombermanGame.bomberman.getY() % Sprite.SCALED_SIZE);
            BombermanGame.bomberman.setVelocity(BombermanGame.bomberStep, BombermanGame.bomberStep);
            BombermanGame.entities.remove(this);
        }
    }
}
