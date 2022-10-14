package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Move;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    public void moveOneal(Bomber bomber) {
        Random seed = new Random();
        this.setVelocity(2 + seed.nextInt(6), 2 + seed.nextInt(6));
        if (this.getX() > bomber.getX()) {
            Move.moveLeft(this);
            if (this.swapMoveImg == 1) {
                this.setImage(Sprite.oneal_left1.getFxImage());
                swapMoveImg = 2;
            } else if (this.swapMoveImg == 2) {
                this.setImage(Sprite.oneal_left2.getFxImage());
                swapMoveImg = 3;
            } else if (this.swapMoveImg == 3) {
                this.setImage(Sprite.oneal_left3.getFxImage());
                swapMoveImg = 1;
            }
        } else if (this.getX() < bomber.getX()) {
            Move.moveRight(this);
            if (this.swapMoveImg == 1) {
                this.setImage(Sprite.oneal_right1.getFxImage());
                swapMoveImg = 2;
            } else if (this.swapMoveImg == 2) {
                this.setImage(Sprite.oneal_right2.getFxImage());
                swapMoveImg = 3;
            } else if (this.swapMoveImg == 3) {
                this.setImage(Sprite.oneal_right3.getFxImage());
                swapMoveImg = 1;
            }
        }

        if (this.getY() > bomber.getY()) {
            Move.moveUp(this);
            if (this.swapMoveImg == 1) {
                this.setImage(Sprite.oneal_left1.getFxImage());
                swapMoveImg = 2;
            } else if (this.swapMoveImg == 2) {
                this.setImage(Sprite.oneal_left2.getFxImage());
                swapMoveImg = 3;
            } else if (this.swapMoveImg == 3) {
                this.setImage(Sprite.oneal_left3.getFxImage());
                swapMoveImg = 1;
            }
        } else if (this.getY() < bomber.getY()) {
            Move.moveDown(this);
            if (this.swapMoveImg == 1) {
                this.setImage(Sprite.oneal_right1.getFxImage());
                swapMoveImg = 2;
            } else if (this.swapMoveImg == 2) {
                this.setImage(Sprite.oneal_right2.getFxImage());
                swapMoveImg = 3;
            } else if (this.swapMoveImg == 3) {
                this.setImage(Sprite.oneal_right3.getFxImage());
                swapMoveImg = 1;
            }
        }
    }

    private void killOneal() {
        if (swapDeathImg == 1) {
            this.setImage(Sprite.oneal_dead.getFxImage());
            swapDeathImg = 2;
        } else if (swapDeathImg == 2) {
            this.setImage(Sprite.mob_dead1.getFxImage());
            swapDeathImg = 3;
        } else if (swapDeathImg == 3) {
            this.setImage(Sprite.mob_dead2.getFxImage());
            swapDeathImg = 4;
        } else if (swapDeathImg == 4) {
            this.setImage(Sprite.mob_dead3.getFxImage());
            swapDeathImg = 5;
        } else {
            BombermanGame.entities.remove(this);
        }
    }

    public void update() {
        if (!isAlive) {
            this.killOneal();
        } else {
            this.moveOneal(BombermanGame.bomberman);
        }
    }
}
