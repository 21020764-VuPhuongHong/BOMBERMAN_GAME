package uet.oop.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class BombItem extends Items {
    public static int numBomsPut = 1;
    public static boolean hasBombItem = false;

    public BombItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        if (getCoveredState()) {
            changeCoveredState();
        }

        if (!getReceivedState() && changeReceivedState()) {
            hasBombItem = getReceivedState();
            BombermanGame.entities.remove(this);
        }
    }
}