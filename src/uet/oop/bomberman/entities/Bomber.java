package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ui.GameOver;
import uet.oop.bomberman.control.CollisionHandle;
import uet.oop.bomberman.entities.Enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private static final int MAX_NUM_FRAMES = 20;
    private int countFrame = 0;
    private boolean isAlive = true;
    private int swapDeathImg = 1;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setAliveState(boolean state) {
        this.isAlive = state;
    }

    public boolean getAliveState() {
        return isAlive;
    }

    private void killBomber() {
        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        if (swapDeathImg == 1) {
            this.setImage(Sprite.player_dead1.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                swapDeathImg = 2;
            }
        } else if (swapDeathImg == 2) {
            this.setImage(Sprite.player_dead2.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                swapDeathImg = 3;
            }
        } else if (swapDeathImg == 3) {
            this.setImage(Sprite.player_dead3.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                swapDeathImg = 4;
            }
        } else {
            BombermanGame.entities.remove(this);

            GameOver.createGameOver();
        }
    }

    @Override
    public void update() {
        if (!isAlive) {
            this.killBomber();
        } else {
            for (Entity e : BombermanGame.entities) {
                javafx.geometry.Rectangle2D rec1 = CollisionHandle.getBoundary(this);
                javafx.geometry.Rectangle2D rec2 = new Rectangle2D(rec1.getMinX(), rec1.getMinY(), rec1.getWidth() * 3 / 4, rec1.getHeight());
                if (e instanceof Enemy && CollisionHandle.intersects(e, rec2)) {
                    this.setAliveState(false);
                }
            }
        }
    }
}
