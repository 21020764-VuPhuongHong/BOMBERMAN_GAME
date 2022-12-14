package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ui.HighScore;
import uet.oop.bomberman.ui.GameOver;
import uet.oop.bomberman.control.CollisionHandle;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private static final int MAX_NUM_FRAMES = 25;
    private int countFrame = 0;
    private boolean isAlive = true;
    private int swapDeathImg = 1;
    private static int heart = 1;

    public static void setHeart(int numHearts) {
        heart = numHearts;
    }

    public static int getHeart() {
        return heart;
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
            BombermanGame.soundControl.playSoundGameOver();

            BombermanGame.entities.clear();
            BombermanGame.stillObjects.clear();
            BombermanGame.listEnemies.clear();
            BombermanGame.listBombs.clear();

            HighScore.handleScore();
            GameOver.createGameOver();
            HighScore.renderYourScoreView();
        }
    }


    @Override
    public void update() {
        if (!isAlive) {
            this.killBomber();
        } else {
            if (getHeart() <= 0) {
                this.setAliveState(false);
            } else {
                for (Enemy e : BombermanGame.listEnemies) {
                    if (CollisionHandle.intersects(e, this)) {
                        this.loseHeart();
                    }
                }
            }
        }
    }
}

