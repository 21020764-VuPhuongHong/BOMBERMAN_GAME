package uet.oop.bomberman.entities.Items;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import javafx.scene.image.Image;

public class Portal extends Items {

    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        if (!isReceived && changeReceivedState()) {
            BombermanGame.bomberman.setX(BombermanGame.bomberman.getX() - BombermanGame.bomberman.getX() % Sprite.SCALED_SIZE);
            BombermanGame.bomberman.setY(BombermanGame.bomberman.getY() - BombermanGame.bomberman.getY() % Sprite.SCALED_SIZE);
            BombermanGame.soundControl.playMoveItem();
            System.out.println("next level");
            System.exit(1);
        }
    }
}
