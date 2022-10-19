package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ui.GameOver;
import uet.oop.bomberman.control.CollisionHandle;
import uet.oop.bomberman.entities.Enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private static final int MAX_NUM_FRAMES = 25;
    private int countFrame = 0;
    private boolean isAlive = true;
    private int swapDeathImg = 1;
    private int heart = 1;

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public int getHeart() {
        return this.heart;
    }

    public void loseHeart() {
        System.out.println(heart);
        --heart;

        if (heart > 0) {
            BombermanGame.soundControl.playSoundLoseHeart();
            this.setX(Sprite.SCALED_SIZE);
            this.setY(Sprite.SCALED_SIZE);
            this.setImage(Sprite.player_right.getFxImage());
        }
    }

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
            BombermanGame.soundControl.playSoundBomberDie();
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
            BombermanGame.soundControl.playSoundGameOver();
            GameOver.createGameOver();
        }
    }


    @Override
    public void update() {
        if (!isAlive) {
            this.killBomber();
        } else {
            if (this.getHeart() <= 0) {
                this.setAliveState(false);
            }
            for (int i = BombermanGame.entities.size() - 1; i >= 0; --i) {
                Entity e = BombermanGame.entities.get(i);
                javafx.geometry.Rectangle2D rec1 = CollisionHandle.getBoundary(this);
                javafx.geometry.Rectangle2D rec2 = new Rectangle2D(rec1.getMinX(), rec1.getMinY(), rec1.getWidth() * 5 / 6, rec1.getHeight());
                if (e instanceof Enemy && CollisionHandle.intersects(e, rec2)) {
                    this.loseHeart();
                }
            }
        }
    }
}

