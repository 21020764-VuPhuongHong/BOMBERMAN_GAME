package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    public static int numOfBombs = 25;
    private static long timeOfBomb;
    private static long timeInterval;
    private static Entity bomb;
    private static int bombImgStatus = 1;
    private static int explosiveState = 1;
    private static final List<Entity> middleHorizontalBombs = new ArrayList<>();
    private static final List<Entity> middleVerticalBombs = new ArrayList<>();
    private static Entity downEdge = null;
    private static Entity upEdge = null;
    private static Entity leftEdge = null;
    private static Entity rightEdge = null;
    private static boolean isEdge = false;
    private static boolean isMiddle = false;
    public static int bombStatus = 0;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public static void putBomb(Entity bomber) {      // The function used for the bomber to place the bomb
        if (bombStatus == 0 && numOfBombs > 0) {
            bombStatus = 1;
            numOfBombs--;
            timeOfBomb = System.currentTimeMillis();
            timeInterval = timeOfBomb;
            bomb = new Bomb(bomber.getX() / 32, bomber.getY() / 32, Sprite.bomb.getFxImage());
            BombermanGame.entities.add(bomb);
        }
    }

    public static void swapBombImg() {
        if (bombImgStatus == 1) {
            bomb.setImage(Sprite.bomb.getFxImage());
            bombImgStatus = 2;
        } else if (bombImgStatus == 2) {
            bomb.setImage(Sprite.bomb_1.getFxImage());
            bombImgStatus = 3;
        } else if (bombImgStatus == 3) {
            bomb.setImage(Sprite.bomb_2.getFxImage());
            bombImgStatus = 4;
        } else {
            bomb.setImage(Sprite.bomb_1.getFxImage());
            bombImgStatus = 1;
        }
    }

    public static void createExplodingEdge() {
        downEdge = new Bomb(bomb.getX( ) / 32, bomb.getY() / 32 + 1, Sprite.bomb_exploded.getFxImage());
        BombermanGame.entities.add(downEdge);

        upEdge = new Bomb(bomb.getX( ) / 32, bomb.getY() / 32 - 1, Sprite.bomb_exploded.getFxImage());
        BombermanGame.entities.add(upEdge);

        leftEdge = new Bomb(bomb.getX( ) / 32 - 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
        BombermanGame.entities.add(leftEdge);

        rightEdge = new Bomb(bomb.getX( ) / 32 + 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
        BombermanGame.entities.add(rightEdge);
    }

    public static void createMiddle() {     // Adjust the bomb to explode at the center position
        Entity middle;

        middle = new Bomb(bomb.getX( ) / 32, bomb.getY() / 32 + 1, Sprite.bomb_exploded.getFxImage());
        middleVerticalBombs.add(middle);

        middle = new Bomb(bomb.getX( ) / 32, bomb.getY() / 32 - 1, Sprite.bomb_exploded.getFxImage());
        middleVerticalBombs.add(middle);

        middle = new Bomb(bomb.getX( ) / 32 - 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
        middleHorizontalBombs.add(middle);

        middle = new Bomb(bomb.getX( ) / 32 + 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
        middleHorizontalBombs.add(middle);

        BombermanGame.entities.addAll(middleHorizontalBombs);
        BombermanGame.entities.addAll(middleVerticalBombs);
    }

    public static void explosionCenter() {      // Determine the explosion center of the bomb
        if (explosiveState == 1) {
            bomb.setImage(Sprite.bomb_exploded.getFxImage());
            BombermanGame.killedEntities[bomb.getX() / 32][bomb.getY() / 32] = 1;

            downEdge.setImage(Sprite.explosion_vertical_down_last.getFxImage());
            BombermanGame.killedEntities[downEdge.getX() / 32][downEdge.getY() / 32] = 1;

            upEdge.setImage(Sprite.explosion_vertical_top_last.getFxImage());
            BombermanGame.killedEntities[upEdge.getX() / 32][upEdge.getY() / 32] = 1;

            leftEdge.setImage(Sprite.explosion_horizontal_left_last.getFxImage());
            BombermanGame.killedEntities[leftEdge.getX() / 32][leftEdge.getY() / 32] = 1;

            rightEdge.setImage(Sprite.explosion_horizontal_right_last.getFxImage());
            BombermanGame.killedEntities[rightEdge.getX() / 32][rightEdge.getY() / 32] = 1;

            if (middleVerticalBombs.size() > 0) {
                for (Entity e : middleVerticalBombs) {
                    e.setImage(Sprite.explosion_vertical.getFxImage());
                    BombermanGame.killedEntities[e.getX() / 32][e.getY() / 32] = 1;
                }
            }

            if (middleHorizontalBombs.size() > 0) {
                for (Entity e : middleHorizontalBombs) {
                    e.setImage(Sprite.explosion_horizontal.getFxImage());
                    BombermanGame.killedEntities[e.getX() / 32][e.getY() / 32] = 1;
                }
            }

            explosiveState = 2;
        } else if (explosiveState == 2) {
            bomb.setImage(Sprite.bomb_exploded1.getFxImage());
            downEdge.setImage(Sprite.explosion_vertical_down_last1.getFxImage());

            upEdge.setImage(Sprite.explosion_vertical_top_last1.getFxImage());

            leftEdge.setImage(Sprite.explosion_horizontal_left_last1.getFxImage());

            rightEdge.setImage(Sprite.explosion_horizontal_right_last1.getFxImage());

            if (isMiddle) {
                for (Entity e : middleVerticalBombs) {
                    e.setImage(Sprite.explosion_vertical1.getFxImage());

                }
                for (Entity e : middleHorizontalBombs) {
                    e.setImage(Sprite.explosion_horizontal1.getFxImage());
                }
            }

            explosiveState = 3;
        } else if (explosiveState == 3) {
            bomb.setImage(Sprite.bomb_exploded2.getFxImage());
            downEdge.setImage(Sprite.explosion_vertical_down_last2.getFxImage());
            upEdge.setImage(Sprite.explosion_vertical_top_last2.getFxImage());
            leftEdge.setImage(Sprite.explosion_horizontal_left_last2.getFxImage());
            rightEdge.setImage(Sprite.explosion_horizontal_right_last2.getFxImage());

            if (isMiddle) {
                for (Entity e : middleVerticalBombs) {
                    e.setImage(Sprite.explosion_vertical2.getFxImage());
                }
                for (Entity e : middleHorizontalBombs) {
                    e.setImage(Sprite.explosion_horizontal2.getFxImage());
                }
            }

            explosiveState = 0;
        }

    }

    private static void checkActive() {     // Check what stages the bomb has gone through before detonating
        if (bombStatus == 1) {
            if (System.currentTimeMillis() - timeOfBomb < 2000) {
                swapBombImg();
                timeInterval += 100;
            } else {
                bombStatus = 2;
                timeOfBomb = System.currentTimeMillis();
                timeInterval = timeOfBomb;
            }
        }
    }

    private static void checkExplosion() {      // Check the bomb's detonation time after the bomb is activated
        if (bombStatus == 2) {
            if (System.currentTimeMillis() - timeOfBomb < 1000) {
                if (!isEdge) {
                    createExplodingEdge();
                    isEdge = true;
                }

                if (!isMiddle) {
                    createMiddle();
                    isMiddle = true;
                }

                explosionCenter();
                timeInterval += 100;
            } else {
                bombStatus = 0;

                bomb.setImage(Sprite.transparent.getFxImage());

                downEdge.setImage(Sprite.transparent.getFxImage());


                upEdge.setImage(Sprite.transparent.getFxImage());


                leftEdge.setImage(Sprite.transparent.getFxImage());


                rightEdge.setImage(Sprite.transparent.getFxImage());

                BombermanGame.killedEntities[bomb.getX() / 32][bomb.getY() / 32] = 0;

                BombermanGame.killedEntities[downEdge.getX() / 32][downEdge.getY() / 32] = 0;

                BombermanGame.killedEntities[upEdge.getX() / 32][upEdge.getY() / 32] = 0;

                BombermanGame.killedEntities[leftEdge.getX() / 32][leftEdge.getY() / 32] = 0;

                BombermanGame.killedEntities[rightEdge.getX() / 32][rightEdge.getY() / 32] = 0;

                if (isMiddle) {
                    for (Entity e : middleVerticalBombs) {
                        BombermanGame.killedEntities[e.getX() / 32][e.getY() / 32] = 0;
                    }
                    for (Entity e : middleHorizontalBombs) {
                        BombermanGame.killedEntities[e.getX() / 32][e.getY() / 32] = 0;
                    }
                }

                BombermanGame.entities.removeAll(middleVerticalBombs);
                BombermanGame.entities.removeAll(middleHorizontalBombs);
                middleVerticalBombs.clear();
                middleHorizontalBombs.clear();
                isEdge = false;
                isMiddle = false;
                explosiveState = 1;
            }
        }
    }

    public void update() {
        checkActive();
        checkExplosion();
    }
}

