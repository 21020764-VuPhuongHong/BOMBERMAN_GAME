package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class WallPassItem extends Items {
    public static boolean canPassBrick = false;

    public WallPassItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        if (!isReceived && changeReceivedState()) {
            canPassBrick = isReceived;
            BombermanGame.soundControl.playMoveItem();
            BombermanGame.entities.remove(this);
        }
    }
}
