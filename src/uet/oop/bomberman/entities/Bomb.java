package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.CollisionHandle;
import uet.oop.bomberman.entities.block.Brick;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.entities.items.*;
import uet.oop.bomberman.level.ConfigLevel;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Bomb extends Entity {
    public static int numOfBombs = 25;
    private long timeOfBomb;
    private int bombImgStatus = 1;
    private int countFrame = 0;
    private static final int MAX_NUM_FRAMES = 9;
    private int explosiveState = 1;
    private List<Entity> middleHorizontalExplosionList = new ArrayList<>();
    private List<Entity> middleVerticalExplosionList = new ArrayList<>();
    private boolean canAddUp = true;
    private boolean canAddDown = true;
    private boolean canAddRight = true;
    private boolean canAddLeft = true;
    private Entity downEdge = null;
    private Entity upEdge = null;
    private Entity leftEdge = null;
    private Entity rightEdge = null;
    private boolean hasEdge = false;
    private boolean hasMiddle = false;
    private int bombStatus = 0;
    public static int explodingLength = 1;
    public int timeBeforeExploding = 2000;
    private static final int TIME_EXPLODING = 700;
    public static boolean bomberFirstGoRightThroughBomb = true;
    public static boolean bomberFirstGoLeftThroughBomb = true;
    public static boolean bomberFirstGoUpThroughBomb = true;
    public static boolean bomberFirstGoDownThroughBomb = true;
    public boolean isExplosionTriggered = false;
    public static int numBombsPutAtATime = 1;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public void putBomb() {
        if (bombStatus == 0) {
            BombermanGame.soundControl.playSoundPutBomb();
            bombStatus = 1;
            numOfBombs--;
            timeOfBomb = System.currentTimeMillis();
            bomberFirstGoRightThroughBomb = true;
            bomberFirstGoLeftThroughBomb = true;
            bomberFirstGoUpThroughBomb = true;
            bomberFirstGoDownThroughBomb = true;
        }
    }

    public void swapBombImg() {
        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        if (bombImgStatus == 1) {
            this.setImage(Sprite.bomb.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                bombImgStatus = 2;
            }
        } else if (bombImgStatus == 2) {
            this.setImage(Sprite.bomb_1.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                bombImgStatus = 3;
            }
        } else if (bombImgStatus == 3) {
            this.setImage(Sprite.bomb_2.getFxImage());
            countFrame++;
            if (countFrame == MAX_NUM_FRAMES) {
                bombImgStatus = 1;
            }
        }
    }

    public void createExplodingEdge() {
        BombermanGame.soundControl.playSoundBomb();

        if (this.getY() / SCALED_SIZE + explodingLength < ConfigLevel.height && canAddDown) {
            if (!CollisionHandle.checkWall(this.getX(), this.getY() + explodingLength * SCALED_SIZE)
                    && !CollisionHandle.checkBrick(this.getX(), this.getY() + explodingLength * SCALED_SIZE)) {
                downEdge = new Bomb(this.getX() / SCALED_SIZE, this.getY() / SCALED_SIZE + explodingLength, Sprite.transparent.getFxImage());
                BombermanGame.entities.add(downEdge);
            } else {
                handleBrickCollision(this.getX(), this.getY() + explodingLength * SCALED_SIZE);
                canAddDown = false;
            }
        }

        if (this.getY() / SCALED_SIZE - explodingLength >= 0 && canAddUp) {
            if (!CollisionHandle.checkWall(this.getX(), this.getY() - explodingLength * SCALED_SIZE)
                    && !CollisionHandle.checkBrick(this.getX(), this.getY() - explodingLength * SCALED_SIZE)) {
                upEdge = new Bomb(this.getX() / SCALED_SIZE, this.getY() / SCALED_SIZE - explodingLength, Sprite.transparent.getFxImage());
                BombermanGame.entities.add(upEdge);
            } else {
                handleBrickCollision(this.getX(), this.getY() - explodingLength * SCALED_SIZE);
                canAddUp = false;
            }
        }

        if (this.getX() / SCALED_SIZE - explodingLength >= 0 && canAddLeft) {
            if (!CollisionHandle.checkWall(this.getX() - explodingLength * SCALED_SIZE, this.getY())
                    && !CollisionHandle.checkBrick(this.getX() - explodingLength * SCALED_SIZE, this.getY())) {
                leftEdge = new Bomb(this.getX() / SCALED_SIZE - explodingLength, this.getY() / SCALED_SIZE, Sprite.transparent.getFxImage());
                BombermanGame.entities.add(leftEdge);
            } else {
                handleBrickCollision(this.getX() - explodingLength * SCALED_SIZE, this.getY());
                canAddLeft = false;
            }
        }

        if (this.getX() / SCALED_SIZE + explodingLength < ConfigLevel.width && canAddRight) {
            if (!CollisionHandle.checkWall(this.getX() + explodingLength * SCALED_SIZE, this.getY())
                    && !CollisionHandle.checkBrick(this.getX() + explodingLength * SCALED_SIZE, this.getY())) {
                rightEdge = new Bomb(this.getX() / SCALED_SIZE + explodingLength, this.getY() / SCALED_SIZE, Sprite.transparent.getFxImage());
                BombermanGame.entities.add(rightEdge);
            } else {
                handleBrickCollision(this.getX() + explodingLength * SCALED_SIZE, this.getY());
                canAddRight = false;
            }
        }
    }

    public void createMiddleExplosion() {
        Entity middle;
        for (int i = 1; i < explodingLength; i++) {
            if (canAddDown) {
                if (!CollisionHandle.checkWall(this.getX(), this.getY() + i * SCALED_SIZE)
                        && !CollisionHandle.checkBrick(this.getX(), this.getY() + i * SCALED_SIZE)) {
                    middle = new Bomb(this.getX() / SCALED_SIZE, this.getY() / SCALED_SIZE + i, Sprite.transparent.getFxImage());
                    middleVerticalExplosionList.add(middle);
                } else {
                    handleBrickCollision(this.getX(), this.getY() + i * SCALED_SIZE);
                    canAddDown = false;
                }
            }

            if (canAddUp) {
                if (!CollisionHandle.checkWall(this.getX(), this.getY() - i * SCALED_SIZE)
                        && !CollisionHandle.checkBrick(this.getX(), this.getY() - i * SCALED_SIZE)) {
                    middle = new Bomb(this.getX() / SCALED_SIZE, this.getY() / SCALED_SIZE - i, Sprite.transparent.getFxImage());
                    middleVerticalExplosionList.add(middle);
                } else {
                    handleBrickCollision(this.getX(), this.getY() - i * SCALED_SIZE);
                    canAddUp = false;
                }
            }

            if (canAddLeft) {
                if (!CollisionHandle.checkWall(this.getX() - i * SCALED_SIZE, this.getY())
                        && !CollisionHandle.checkBrick(this.getX() - i * SCALED_SIZE, this.getY())) {
                    middle = new Bomb(this.getX() / SCALED_SIZE - i, this.getY() / SCALED_SIZE, Sprite.transparent.getFxImage());
                    middleHorizontalExplosionList.add(middle);
                } else {
                    handleBrickCollision(this.getX() - i * SCALED_SIZE, this.getY());
                    canAddLeft = false;
                }
            }

            if (canAddRight) {
                if (!CollisionHandle.checkWall(this.getX() + i * SCALED_SIZE, this.getY())
                        && !CollisionHandle.checkBrick(this.getX() + i * SCALED_SIZE, this.getY())) {
                    middle = new Bomb(this.getX() / SCALED_SIZE + i, this.getY() / SCALED_SIZE, Sprite.transparent.getFxImage());
                    middleHorizontalExplosionList.add(middle);
                } else {
                    handleBrickCollision(this.getX() + i * SCALED_SIZE, this.getY());
                    canAddRight = false;
                }
            }
        }

        BombermanGame.entities.addAll(middleHorizontalExplosionList);
        BombermanGame.entities.addAll(middleVerticalExplosionList);
    }


    public void explosion() {
        if (countFrame > MAX_NUM_FRAMES) {
            countFrame = 1;
        }

        if (explosiveState == 1) {
            countFrame++;

            this.setImage(Sprite.bomb_exploded.getFxImage());
            handleCollision(this);

            if (middleVerticalExplosionList.size() > 0) {
                for (Entity e : middleVerticalExplosionList) {
                    e.setImage(Sprite.explosion_vertical.getFxImage());
                    handleCollision(e);
                }
            }

            if (middleHorizontalExplosionList.size() > 0) {
                for (Entity e : middleHorizontalExplosionList) {
                    e.setImage(Sprite.explosion_horizontal.getFxImage());
                    handleCollision(e);
                }
            }

            if (downEdge != null) {
                downEdge.setImage(Sprite.explosion_vertical_down_last.getFxImage());
                handleCollision(downEdge);
            }

            if (upEdge != null) {
                upEdge.setImage(Sprite.explosion_vertical_top_last.getFxImage());
                handleCollision(upEdge);
            }

            if (leftEdge != null) {
                leftEdge.setImage(Sprite.explosion_horizontal_left_last.getFxImage());
                handleCollision(leftEdge);
            }

            if (rightEdge != null) {
                rightEdge.setImage(Sprite.explosion_horizontal_right_last.getFxImage());
                handleCollision(rightEdge);
            }

            if (countFrame == MAX_NUM_FRAMES) {
                explosiveState = 2;
            }
        } else if (explosiveState == 2) {
            countFrame++;

            this.setImage(Sprite.bomb_exploded1.getFxImage());

            if (downEdge != null) {
                downEdge.setImage(Sprite.explosion_vertical_down_last1.getFxImage());
            }


            if (upEdge != null) {
                upEdge.setImage(Sprite.explosion_vertical_top_last1.getFxImage());
            }


            if (leftEdge != null) {
                leftEdge.setImage(Sprite.explosion_horizontal_left_last1.getFxImage());
            }


            if (rightEdge != null) {
                rightEdge.setImage(Sprite.explosion_horizontal_right_last1.getFxImage());
            }

            if (middleVerticalExplosionList.size() > 0) {
                for (Entity e : middleVerticalExplosionList) {
                    e.setImage(Sprite.explosion_vertical1.getFxImage());
                }
            }

            if (middleHorizontalExplosionList.size() > 0) {
                for (Entity e : middleHorizontalExplosionList) {
                    e.setImage(Sprite.explosion_horizontal1.getFxImage());
                }
            }

            if (countFrame == MAX_NUM_FRAMES) {
                explosiveState = 3;
            }
        } else if (explosiveState == 3) {
            countFrame++;

            this.setImage(Sprite.bomb_exploded2.getFxImage());

            if (middleVerticalExplosionList.size() > 0) {
                for (Entity e : middleVerticalExplosionList) {
                    e.setImage(Sprite.explosion_vertical2.getFxImage());
                }
            }

            if (middleHorizontalExplosionList.size() > 0) {
                for (Entity e : middleHorizontalExplosionList) {
                    e.setImage(Sprite.explosion_horizontal2.getFxImage());
                }
            }

            if (downEdge != null) {
                downEdge.setImage(Sprite.explosion_vertical_down_last2.getFxImage());
            }

            if (upEdge != null) {
                upEdge.setImage(Sprite.explosion_vertical_top_last2.getFxImage());
            }

            if (leftEdge != null) {
                leftEdge.setImage(Sprite.explosion_horizontal_left_last2.getFxImage());
            }

            if (rightEdge != null) {
                rightEdge.setImage(Sprite.explosion_horizontal_right_last2.getFxImage());
            }

            if (countFrame == MAX_NUM_FRAMES) {
                explosiveState = 4;
            }
        } else if (explosiveState == 4) {
            countFrame++;

            this.setImage(Sprite.bomb_exploded.getFxImage());

            if (middleVerticalExplosionList.size() > 0) {
                for (Entity e : middleVerticalExplosionList) {
                    e.setImage(Sprite.explosion_vertical.getFxImage());
                }
            }

            if (middleHorizontalExplosionList.size() > 0) {
                for (Entity e : middleHorizontalExplosionList) {
                    e.setImage(Sprite.explosion_horizontal.getFxImage());
                }
            }

            if (downEdge != null) {
                downEdge.setImage(Sprite.explosion_vertical_down_last.getFxImage());
            }

            if (upEdge != null) {
                upEdge.setImage(Sprite.explosion_vertical_top_last.getFxImage());
            }

            if (leftEdge != null) {
                leftEdge.setImage(Sprite.explosion_horizontal_left_last.getFxImage());
            }

            if (rightEdge != null) {
                rightEdge.setImage(Sprite.explosion_horizontal_right_last.getFxImage());
            }

            if (countFrame == MAX_NUM_FRAMES) {
                explosiveState = 0;
            }
        }
    }

    private void handleBombExplosion() {
        if (bombStatus == 1) {
            if ((System.currentTimeMillis() - timeOfBomb < timeBeforeExploding || DetonatorItem.hasDetonator) && !isExplosionTriggered) {
                swapBombImg();
            } else {
                bombStatus = 2;
                timeOfBomb = System.currentTimeMillis();
            }
        } else if (bombStatus == 2) {
            if (System.currentTimeMillis() - timeOfBomb < TIME_EXPLODING) {

                if (explodingLength >= 2 && !hasMiddle) {
                    createMiddleExplosion();
                    hasMiddle = true;
                }

                if (!hasEdge) {
                    createExplodingEdge();
                    hasEdge = true;
                }


                explosion();
            } else {
                bombStatus = 0;

                handleSpawning();

                if (downEdge != null) {
                    BombermanGame.entities.remove(downEdge);
                }

                if (upEdge != null) {
                    BombermanGame.entities.remove(upEdge);
                }

                if (leftEdge != null) {
                    BombermanGame.entities.remove(leftEdge);
                }

                if (rightEdge != null) {
                    BombermanGame.entities.remove(rightEdge);
                }

                if (middleVerticalExplosionList.size() > 0) {
                    BombermanGame.entities.removeAll(middleVerticalExplosionList);
                }
                if (middleHorizontalExplosionList.size() > 0) {
                    BombermanGame.entities.removeAll(middleHorizontalExplosionList);
                }

                middleVerticalExplosionList.clear();
                middleHorizontalExplosionList.clear();

                BombermanGame.listBombs.remove(this);
            }
        }
    }

    public void handleEnemyCollision(Entity e) {
        for (Enemy enemy : BombermanGame.listEnemies) {
            if (CollisionHandle.intersects(e, enemy)) {
                enemy.setAliveState(false);
            }
        }
    }

    public void handleBomberCollision(Entity e) {
        if (!FlamePassItem.isExplosionImmune && CollisionHandle.intersects(e, BombermanGame.bomberman)) {
            BombermanGame.bomberman.loseHeart();
        }
    }

    public void handleBombCollision(Entity e) {
        for (Bomb otherBomb : BombermanGame.listBombs) {
            if (CollisionHandle.intersects(e, otherBomb)) {
                otherBomb.isExplosionTriggered = true;
            }
        }
    }

    public void handleItemsCollision(Entity e) {
        for (Entity other : BombermanGame.entities) {
            if (other instanceof FlameItem && CollisionHandle.intersects(e, other)) {
                BombermanGame.entities.remove(other);
                Oneal.spawn = true;
                Oneal.spawnPosX = e.getX() / SCALED_SIZE;
                Oneal.spawnPosY = e.getY() / SCALED_SIZE;
            } else if (other instanceof BombItem && CollisionHandle.intersects(e, other)) {
                BombermanGame.entities.remove(other);
                Ballom.spawn = true;
                Ballom.spawnPosX = e.getX() / SCALED_SIZE;
                Ballom.spawnPosY = e.getY() / SCALED_SIZE;
            } else if (other instanceof WallPassItem && CollisionHandle.intersects(e, other)) {
                BombermanGame.entities.remove(other);
                Minvo.spawn = true;
                Minvo.spawnPosX = e.getX() / SCALED_SIZE;
                Minvo.spawnPosY = e.getY() / SCALED_SIZE;
            } else if (other instanceof BombPassItem && CollisionHandle.intersects(e, other)) {
                BombermanGame.entities.remove(other);
                Ovapi.spawn = true;
                Ovapi.spawnPosX = e.getX() / SCALED_SIZE;
                Ovapi.spawnPosY = e.getY() / SCALED_SIZE;
            } else if (other instanceof SpeedItem && CollisionHandle.intersects(e, other)) {
                BombermanGame.entities.remove(other);
                Doll.spawn = true;
                Doll.spawnPosX = e.getX() / SCALED_SIZE;
                Doll.spawnPosY = e.getY() / SCALED_SIZE;
            } else if (other instanceof DetonatorItem && CollisionHandle.intersects(e, other)) {
                BombermanGame.entities.remove(other);
                Minvo.spawn = true;
                Minvo.spawnPosX = e.getX() / SCALED_SIZE;
                Minvo.spawnPosY = e.getY() / SCALED_SIZE;
            } else if (other instanceof FlamePassItem && CollisionHandle.intersects(e, other)) {
                BombermanGame.entities.remove(other);
                Ovapi.spawn = true;
                Ovapi.spawnPosX = e.getX() / SCALED_SIZE;
                Ovapi.spawnPosY = e.getY() / SCALED_SIZE;
            } else if (other instanceof Portal && CollisionHandle.intersects(e, other)) {
                Ovapi.spawn = true;
                Ovapi.spawnPosX = e.getX() / SCALED_SIZE;
                Ovapi.spawnPosY = e.getY() / SCALED_SIZE;
            }
        }
    }

    public void handleCollision(Entity e) {
        handleEnemyCollision(e);
        handleBomberCollision(e);
        handleBombCollision(e);
        handleItemsCollision(e);
    }

    public void handleBrickCollision(int x, int y) {
        Rectangle2D rec = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        for (Entity other : BombermanGame.entities) {
            if (other instanceof Brick && CollisionHandle.intersects(other, rec)) {
                Brick brick = (Brick) other;
                brick.setIsExploded(true);
            }
        }

        if (!FlamePassItem.isExplosionImmune && CollisionHandle.intersects(BombermanGame.bomberman, rec)) {
            BombermanGame.bomberman.loseHeart();
        }

        for (Enemy enemy : BombermanGame.listEnemies) {
            if (CollisionHandle.intersects(enemy, rec)) {
                enemy.setAliveState(false);
            }
        }
    }

    public void handleSpawning() {
        if (Oneal.spawn) {
            Oneal oneal = new Oneal(Oneal.spawnPosX, Oneal.spawnPosY, Sprite.oneal_left1.getFxImage());
            BombermanGame.listEnemies.add(oneal);
            Oneal.spawn = false;
        } else if (Doll.spawn) {
            Doll doll = new Doll(Doll.spawnPosX, Doll.spawnPosY, Sprite.oneal_left1.getFxImage());
            BombermanGame.listEnemies.add(doll);
            Doll.spawn = false;
        } else if (Ballom.spawn) {
            Ballom ballom = new Ballom(Ballom.spawnPosX, Ballom.spawnPosY, Sprite.oneal_left1.getFxImage());
            BombermanGame.listEnemies.add(ballom);
            Ballom.spawn = false;
        } else if (Minvo.spawn) {
            Minvo minvo = new Minvo(Minvo.spawnPosX, Minvo.spawnPosY, Sprite.oneal_left1.getFxImage());
            BombermanGame.listEnemies.add(minvo);
            Minvo.spawn = false;
        } else if (Ovapi.spawn) {
            Ovapi ovapi = new Ovapi(Ovapi.spawnPosX, Ovapi.spawnPosY, Sprite.oneal_left1.getFxImage());
            BombermanGame.listEnemies.add(ovapi);
            Ovapi.spawn = false;
        }
    }

    public void update() {
        handleBombExplosion();
    }
}

