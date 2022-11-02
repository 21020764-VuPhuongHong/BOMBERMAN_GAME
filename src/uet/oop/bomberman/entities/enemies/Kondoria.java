package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.find_path.SmartFindPath;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy {
    public static boolean spawn = false;
    public static int spawnPosX;
    public static int spawnPosY;

    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
    }

    private void moveKondoria() {
        this.setVelocity(1, 1);

        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        if (BombermanGame.bomberman.getAliveState()) {
            SmartFindPath.findPath(this);
        }

        switch (direction) {
            case 0:
                Move.moveUp(this);

                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.kondoria_left1.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 2;
                    }
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.kondoria_left2.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 3;
                    }
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.kondoria_left3.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 1;
                    }
                }
                break;

            case 1:
                Move.moveDown(this);

                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.kondoria_right1.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 2;
                    }
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.kondoria_right2.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 3;
                    }
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.kondoria_right3.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 1;
                    }
                }
                break;

            case 2:
                Move.moveLeft(this);

                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.kondoria_left1.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 2;
                    }
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.kondoria_left2.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 3;
                    }
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.kondoria_left3.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 1;
                    }
                }
                break;

            case 3:
                Move.moveRight(this);

                if (this.swapMoveImg == 1) {
                    this.setImage(Sprite.kondoria_right1.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 2;
                    }
                } else if (this.swapMoveImg == 2) {
                    this.setImage(Sprite.kondoria_right2.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 3;
                    }
                } else if (this.swapMoveImg == 3) {
                    this.setImage(Sprite.kondoria_right3.getFxImage());
                    countFrame++;
                    if (countFrame == MAX_NUM_FRAMES) {
                        swapMoveImg = 1;
                    }
                }
                break;
        }
    }

    private void killKondoria() {
        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        if (swapDeathImg == 1) {
            BombermanGame.soundControl.playSoundEnemyDie();
            this.setImage(Sprite.kondoria_dead.getFxImage());
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
            BombermanGame.score += 1400;
        }
    }

    public void update() {
        if (!isAlive) {
            this.killKondoria();
        } else {
            this.moveKondoria();
        }
    }
}
