package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;


public class FlameItem extends Items {
    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        if (!isReceived && changeReceivedState()) {
            Bomb.explodingLength = 2;
            BombermanGame.soundControl.playMoveItem();
            BombermanGame.entities.remove(this);
        }
    }
}
