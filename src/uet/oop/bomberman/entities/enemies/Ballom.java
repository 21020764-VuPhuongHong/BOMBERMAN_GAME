package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.graphics.Sprite;

public class Ballom extends Enemy {
    public static boolean spawn = false;
    public static int spawnPosX;
    public static int spawnPosY;
    public Ballom(int x, int y, Image img) {
        super(x, y, img);
    }

    private void moveBallom() {
        this.setVelocity(1, 1);
        if (this.getX() % Sprite.SCALED_SIZE == 0 && this.getY() % Sprite.SCALED_SIZE == 0) {
            direction = seed.nextInt(4);
        }

        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        switch (direction) {
            case 0:
                Move.moveUp(this);
                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.balloom_left1.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 2;
                    }
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.balloom_left2.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 3;
                    }
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.balloom_left3.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 1;
                    }
                }
                break;
            case 1:
                Move.moveDown(this);
                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.balloom_right1.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 2;
                    }
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.balloom_right2.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 3;
                    }
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.balloom_right3.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 1;
                    }
                }
                break;
            case 2:
                Move.moveRight(this);
                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.balloom_right1.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 2;
                    }
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.balloom_right2.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 3;
                    }
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.balloom_right3.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 1;
                    }
                }
                break;
            case 3:
                Move.moveLeft(this);
                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.balloom_left1.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 2;
                    }
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.balloom_left2.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 3;
                    }
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.balloom_left3.getFxImage());
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
            this.setImage(Sprite.balloom_dead.getFxImage());
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
            BombermanGame.listEnemies.remove(this);
            BombermanGame.score += 100;
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

