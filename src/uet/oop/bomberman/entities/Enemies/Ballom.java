package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.Move;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Ballom extends Enemy {
    private int direction = 0;
    public Ballom(int x, int y, Image img) {
        super(x, y, img);
    }

    public void move() {
        this.setVelocity(Sprite.DEFAULT_SIZE / 4, Sprite.DEFAULT_SIZE / 4);
        Random seed = new Random();
        if (this.getX() % Sprite.SCALED_SIZE == 0 && this.getY() % Sprite.SCALED_SIZE == 0) {
            direction = seed.nextInt(4);
        }
        switch (direction) {
            case 0:
                Move.moveUp(this);
                if (this.getSwapMoveImg() == 1) {
                    this.setImage(Sprite.balloom_left1.getFxImage());
                    this.setSwapMoveImg(2);
                } else if (this.getSwapMoveImg() == 2) {
                    this.setImage(Sprite.balloom_left2.getFxImage());
                    this.setSwapMoveImg(3);
                } else if (this.getSwapMoveImg() == 3) {
                    this.setImage(Sprite.balloom_left3.getFxImage());
                    this.setSwapMoveImg(1);
                }
                break;
            case 1:
                Move.moveDown(this);
                if (this.getSwapMoveImg() == 1) {
                    this.setImage(Sprite.balloom_right1.getFxImage());
                    this.setSwapMoveImg(2);
                } else if (this.getSwapMoveImg() == 2) {
                    this.setImage(Sprite.balloom_right2.getFxImage());
                    this.setSwapMoveImg(3);
                } else if (this.getSwapMoveImg() == 3) {
                    this.setImage(Sprite.balloom_right3.getFxImage());
                    this.setSwapMoveImg(1);
                }
                break;
            case 2:
                Move.moveRight(this);
                if (this.getSwapMoveImg() == 1) {
                    this.setImage(Sprite.balloom_right1.getFxImage());
                    this.setSwapMoveImg(2);
                } else if (this.getSwapMoveImg() == 2) {
                    this.setImage(Sprite.balloom_right2.getFxImage());
                    this.setSwapMoveImg(3);
                } else if (this.getSwapMoveImg() == 3) {
                    this.setImage(Sprite.balloom_right3.getFxImage());
                    this.setSwapMoveImg(1);
                }
                break;
            case 3:
                Move.moveLeft(this);
                if (this.getSwapMoveImg() == 1) {
                    this.setImage(Sprite.balloom_left1.getFxImage());
                    this.setSwapMoveImg(2);
                } else if (this.getSwapMoveImg() == 2) {
                    this.setImage(Sprite.balloom_left2.getFxImage());
                    this.setSwapMoveImg(3);
                } else if (this.getSwapMoveImg() == 3) {
                    this.setImage(Sprite.balloom_left3.getFxImage());
                    this.setSwapMoveImg(1);
                }
                break;
        }
    }

    public void update() {
        super.update();
        this.move();
    }
}

