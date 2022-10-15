package uet.oop.bomberman.entities.Block;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    private static final int MAX_NUM_FRAMES = 20;
    private int countFrame = 0;
    private int swapExplodedImg = 1;
    private boolean isExploded = false;
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    private void explodeBrick() {
        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        if (swapExplodedImg == 1) {
            this.setImage(Sprite.brick_exploded.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                swapExplodedImg = 2;
            }
        } else if (swapExplodedImg == 2) {
            this.setImage(Sprite.brick_exploded1.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                swapExplodedImg = 3;
            }
        } else if (swapExplodedImg == 3) {
            this.setImage(Sprite.brick_exploded2.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                swapExplodedImg = 4;
            }
        } else {
            BombermanGame.entities.remove(this);
        }
    }

    public void setIsExploded(boolean isExploded) {
        this.isExploded = isExploded;
    }

    @Override
    public void update() {
        if (isExploded) {
            this.explodeBrick();
        }
    }
}
