package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Move;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Ballom extends Enemy {
    private int direction = 0;
    public Ballom(int x, int y, Image img) {
        super(x, y, img);
    }

    private void moveBallom() {
        this.setVelocity(Sprite.DEFAULT_SIZE / 4, Sprite.DEFAULT_SIZE / 4);
        Random seed = new Random();
        if (this.getX() % Sprite.SCALED_SIZE == 0 && this.getY() % Sprite.SCALED_SIZE == 0) {
            direction = seed.nextInt(4);
        }
        switch (direction) {
            case 0:
                Move.moveUp(this);
                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.balloom_left1.getFxImage());
                    swapMoveImg = 2;
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.balloom_left2.getFxImage());
                    swapMoveImg = 3;
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.balloom_left3.getFxImage());
                    swapMoveImg = 1;
                }
                break;
            case 1:
                Move.moveDown(this);
                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.balloom_right1.getFxImage());
                    swapMoveImg = 2;
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.balloom_right2.getFxImage());
                    swapMoveImg = 3;
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.balloom_right3.getFxImage());
                    swapMoveImg = 1;
                }
                break;
            case 2:
                Move.moveRight(this);
                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.balloom_right1.getFxImage());
                    swapMoveImg = 2;
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.balloom_right2.getFxImage());
                    swapMoveImg = 3;
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.balloom_right3.getFxImage());
                    swapMoveImg = 1;
                }
                break;
            case 3:
                Move.moveLeft(this);
                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.balloom_left1.getFxImage());
                    swapMoveImg = 2;
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.balloom_left2.getFxImage());
                    swapMoveImg = 3;
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.balloom_left3.getFxImage());
                    swapMoveImg = 1;
                }
                break;
        }
    }

    private void killBallom() {
        if (swapDeathImg == 1) {
            this.setImage(Sprite.balloom_dead.getFxImage());
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
            this.killBallom();
        } else {
            this.moveBallom();
        }
    }
}

