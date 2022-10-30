package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.find_path.FindPath1;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends Enemy {
    public static boolean spawn = false;
    public static int spawnPosX;
    public static int spawnPosY;
    public Minvo(int x, int y, Image img) {
        super(x, y, img);
    }

    private void moveMinvo() {
        this.setVelocity(2 + seed.nextInt(2), 2 + seed.nextInt(2));

        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        FindPath1.findPath(this);

        switch (direction) {
            case 0:
                if (Move.canMoveUp(this)) {

                    Move.moveUp(this);

                    shouldMoveUp = true;
                    shouldMoveLeft = true;
                    shouldMoveRight = true;

                    if (this.swapMoveImg == 1) {
                        this.setImage(Sprite.minvo_left1.getFxImage());
                        countFrame++;
                        if (countFrame == MAX_NUM_FRAMES) {
                            swapMoveImg = 2;
                        }
                    } else if (this.swapMoveImg == 2) {
                        this.setImage(Sprite.minvo_left2.getFxImage());
                        countFrame++;
                        if (countFrame == MAX_NUM_FRAMES) {
                            swapMoveImg = 3;
                        }
                    } else if (this.swapMoveImg == 3) {
                        this.setImage(Sprite.minvo_left3.getFxImage());
                        countFrame++;
                        if (countFrame == MAX_NUM_FRAMES) {
                            swapMoveImg = 1;
                        }
                    }
                } else if (Move.canMoveDown(this)) {
                    shouldMoveUp = false;
                    direction = 1;
                } else if (Move.canMoveLeft(this)) {
                    shouldMoveUp = false;
                    direction = 2;
                } else {
                    shouldMoveUp = false;
                    direction = 3;
                }
                break;

            case 1:
                if (Move.canMoveDown(this)) {

                    Move.moveDown(this);

                    shouldMoveDown = true;
                    shouldMoveLeft = true;
                    shouldMoveRight = true;

                    if (this.swapMoveImg == 1) {
                        this.setImage(Sprite.minvo_right1.getFxImage());
                        countFrame++;
                        if (countFrame == MAX_NUM_FRAMES) {
                            swapMoveImg = 2;
                        }
                    } else if (this.swapMoveImg == 2) {
                        this.setImage(Sprite.minvo_right2.getFxImage());
                        countFrame++;
                        if (countFrame == MAX_NUM_FRAMES) {
                            swapMoveImg = 3;
                        }
                    } else if (this.swapMoveImg == 3) {
                        this.setImage(Sprite.minvo_right3.getFxImage());
                        countFrame++;
                        if (countFrame == MAX_NUM_FRAMES) {
                            swapMoveImg = 1;
                        }
                    }
                } else if (Move.canMoveUp(this)) {
                    shouldMoveDown = false;
                    direction = 0;
                } else if (Move.canMoveLeft(this)) {
                    shouldMoveDown = false;
                    direction = 2;
                } else {
                    shouldMoveDown = false;
                    direction = 3;
                }
                break;

            case 2:
                if (Move.canMoveLeft(this)) {

                    Move.moveLeft(this);

                    shouldMoveLeft = true;
                    shouldMoveUp = true;
                    shouldMoveDown = true;

                    if (this.swapMoveImg == 1) {
                        this.setImage(Sprite.minvo_left1.getFxImage());
                        countFrame++;
                        if (countFrame == MAX_NUM_FRAMES) {
                            swapMoveImg = 2;
                        }
                    } else if (this.swapMoveImg == 2) {
                        this.setImage(Sprite.minvo_left2.getFxImage());
                        countFrame++;
                        if (countFrame == MAX_NUM_FRAMES) {
                            swapMoveImg = 3;
                        }
                    } else if (this.swapMoveImg == 3) {
                        this.setImage(Sprite.minvo_left3.getFxImage());
                        countFrame++;
                        if (countFrame == MAX_NUM_FRAMES) {
                            swapMoveImg = 1;
                        }
                    }
                } else if (Move.canMoveRight(this)) {
                    shouldMoveLeft = false;
                    direction = 3;
                } else if (Move.canMoveUp(this)) {
                    shouldMoveLeft = false;
                    direction = 0;
                } else {
                    shouldMoveLeft = false;
                    direction = 1;
                }
                break;

            case 3:
                if (Move.canMoveRight(this)) {

                    Move.moveRight(this);

                    shouldMoveRight = true;
                    shouldMoveUp = true;
                    shouldMoveDown = true;

                    if (this.swapMoveImg == 1) {
                        this.setImage(Sprite.minvo_right1.getFxImage());
                        countFrame++;
                        if (countFrame == MAX_NUM_FRAMES) {
                            swapMoveImg = 2;
                        }
                    } else if (this.swapMoveImg == 2) {
                        this.setImage(Sprite.minvo_right2.getFxImage());
                        countFrame++;
                        if (countFrame == MAX_NUM_FRAMES) {
                            swapMoveImg = 3;
                        }
                    } else if (this.swapMoveImg == 3) {
                        this.setImage(Sprite.minvo_right3.getFxImage());
                        countFrame++;
                        if (countFrame == MAX_NUM_FRAMES) {
                            swapMoveImg = 1;
                        }
                    }
                } else if (Move.canMoveLeft(this)) {
                    shouldMoveRight = false;
                    direction = 2;
                } else if (Move.canMoveUp(this)) {
                    shouldMoveRight = false;
                    direction = 0;
                } else {
                    shouldMoveRight = false;
                    direction = 1;
                }
                break;
        }
    }

    private void killMinvo() {
        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        if (swapDeathImg == 1) {
            BombermanGame.soundControl.playSoundEnemyDie();
            this.setImage(Sprite.minvo_dead.getFxImage());
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
            this.killMinvo();
        } else {
            this.moveMinvo();
        }
    }
}
