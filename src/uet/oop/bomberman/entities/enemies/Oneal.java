package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    public static boolean spawn = false;
    public static int spawnPosX;
    public static int spawnPosY;
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    public void moveOneal() {
        this.setVelocity(1 + seed.nextInt(3), 1 + seed.nextInt(3));

        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        if (this.getX() > BombermanGame.bomberman.getX()) {
            Move.moveLeft(this);
            if (this.swapMoveImg == 1) {
                this.setImage(Sprite.oneal_left1.getFxImage());
                countFrame++;
                if (countFrame == MAX_NUM_FRAMES) {
                    swapMoveImg = 2;
                }
            } else if (this.swapMoveImg == 2) {
                this.setImage(Sprite.oneal_left2.getFxImage());
                countFrame++;
                if (countFrame == MAX_NUM_FRAMES) {
                    swapMoveImg = 3;
                }
            } else if (this.swapMoveImg == 3) {
                this.setImage(Sprite.oneal_left3.getFxImage());
                countFrame++;
                if (countFrame == MAX_NUM_FRAMES) {
                    swapMoveImg = 1;
                }
            }
        } else if (this.getX() < BombermanGame.bomberman.getX()) {
            Move.moveRight(this);
            if (this.swapMoveImg == 1) {
                this.setImage(Sprite.oneal_right1.getFxImage());
                countFrame++;
                if (countFrame == MAX_NUM_FRAMES) {
                    swapMoveImg = 2;
                }
            } else if (this.swapMoveImg == 2) {
                this.setImage(Sprite.oneal_right2.getFxImage());
                countFrame++;
                if (countFrame == MAX_NUM_FRAMES) {
                    swapMoveImg = 3;
                }
            } else if (this.swapMoveImg == 3) {
                this.setImage(Sprite.oneal_right3.getFxImage());
                countFrame++;
                if (countFrame == MAX_NUM_FRAMES) {
                    swapMoveImg = 1;
                }
            }
        }

        if (this.getY() > BombermanGame.bomberman.getY()) {
            Move.moveUp(this);
            if (this.swapMoveImg == 1) {
                this.setImage(Sprite.oneal_left1.getFxImage());
                countFrame++;
                if (countFrame == MAX_NUM_FRAMES) {
                    swapMoveImg = 2;
                }
            } else if (this.swapMoveImg == 2) {
                this.setImage(Sprite.oneal_left2.getFxImage());
                countFrame++;
                if (countFrame == MAX_NUM_FRAMES) {
                    swapMoveImg = 3;
                }
            } else if (this.swapMoveImg == 3) {
                this.setImage(Sprite.oneal_left3.getFxImage());
                countFrame++;
                if (countFrame == MAX_NUM_FRAMES) {
                    swapMoveImg = 1;
                }
            }
        } else if (this.getY() < BombermanGame.bomberman.getY()) {
            Move.moveDown(this);
            if (this.swapMoveImg == 1) {
                this.setImage(Sprite.oneal_right1.getFxImage());
                countFrame++;
                if (countFrame == MAX_NUM_FRAMES) {
                    swapMoveImg = 2;
                }
            } else if (this.swapMoveImg == 2) {
                this.setImage(Sprite.oneal_right2.getFxImage());
                countFrame++;
                if (countFrame == MAX_NUM_FRAMES) {
                    swapMoveImg = 3;
                }
            } else if (this.swapMoveImg == 3) {
                this.setImage(Sprite.oneal_right3.getFxImage());
                countFrame++;
                if (countFrame == MAX_NUM_FRAMES) {
                    swapMoveImg = 1;
                }
            }
        }
    }

    private void killOneal() {
        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        if (swapDeathImg == 1) {
            BombermanGame.soundControl.playSoundEnemyDie();
            this.setImage(Sprite.oneal_dead.getFxImage());
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
            this.killOneal();
        } else {
            this.moveOneal();
        }
    }
}
