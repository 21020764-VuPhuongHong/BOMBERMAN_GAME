package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class BombPassItem extends Items {
    public static boolean canPassBomb = false;

    public BombPassItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        if (!isReceived && changeReceivedState()) {
            canPassBomb = isReceived;
            BombermanGame.soundControl.playMoveItem();
            BombermanGame.entities.remove(this);
        }
    }
}
