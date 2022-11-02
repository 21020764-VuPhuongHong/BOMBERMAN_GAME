package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.find_path.FindShortestPath;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Enemy {
    public static boolean spawn = false;
    public static int spawnPosX;
    public static int spawnPosY;

    public Doll(int x, int y, Image img) {
        super(x, y, img);
    }

    private void moveDoll() {
        this.setVelocity(2, 2);

        direction = FindShortestPath.find(this, BombermanGame.bomberman);

        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        switch (direction) {
            case 1:
                this.setY(this.getY() - this.getVelocityY());
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

    private void killDoll() {
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
            BombermanGame.listEnemies.remove(this);
        }
    }

    public void update() {
        if (!isAlive) {
            this.killDoll();
        } else {
            this.moveDoll();
        }
    }
}