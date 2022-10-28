package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class FlamePassItem extends Items {
    public static boolean isExplosionImmune = false;

    public FlamePassItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        if (!isReceived && changeReceivedState()) {
            isExplosionImmune = isReceived;
            BombermanGame.soundControl.playMoveItem();
            BombermanGame.entities.remove(this);
        }
    }
}
