package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.FindSortestPath;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Doll extends Enemy{
    private int direction = 0;
    public Doll(int x, int y, Image img) {
        super(x, y, img);
    }

    private void moveDoll() {
        this.setVelocity(2, 2);
        /*
        Random seed = new Random();
        if (this.getX() % Sprite.SCALED_SIZE == 0 && this.getY() % Sprite.SCALED_SIZE == 0) {
            direction = seed.nextInt(4);
        }*/

        direction = FindSortestPath.Find(this, BombermanGame.bomberman);
        System.out.println("direction: " + direction);

        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        switch (direction) {
            case 1:
                this.setY(this.getY() - this.getVelocityY());
                //System.out.println("moveup");
                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.doll_left1.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 2;
                    }
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.doll_left2.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 3;
                    }
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.doll_left3.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 1;
                    }
                }
                break;
            case 3:
                this.setY(this.getY() + this.getVelocityY());
                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.doll_right1.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 2;
                    }
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.doll_right2.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 3;
                    }
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.doll_right3.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 1;
                    }
                }
                break;
            case 2:
                this.setX(this.getX() + this.getVelocityX()); // move right
                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.doll_right1.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 2;
                    }
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.doll_right2.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 3;
                    }
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.doll_right3.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 1;
                    }
                }
                break;
            case 4:
                this.setX(this.getX() - this.getVelocityX()); // move left
                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.doll_left1.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 2;
                    }
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.doll_left2.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 3;
                    }
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.doll_left3.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 1;
                    }
                }
                break;
        }
    }

    private void killBallom() {
        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        if (swapDeathImg == 1) {
            BombermanGame.soundControl.playSoundEnemyDie();
            this.setImage(Sprite.doll_dead.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                swapDeathImg = 2;
            }
        } else if (swapDeathImg == 2) {
            this.setImage(Sprite.mob_dead1.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                swapDeathImg = 3;
            }
        } else if (swapDeathImg == 3) {
            this.setImage(Sprite.mob_dead2.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                swapDeathImg = 4;
            }
        } else if (swapDeathImg == 4) {
            this.setImage(Sprite.mob_dead3.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                swapDeathImg = 5;
            }
        } else {
            BombermanGame.entities.remove(this);
        }
    }

    public void update() {
        if (!isAlive) {
            this.killBallom();
        } else {
            this.moveDoll();
        }
    }
}
