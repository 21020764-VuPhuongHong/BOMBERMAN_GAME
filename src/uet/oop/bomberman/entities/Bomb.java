package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    public static int numOfBombs = 25;
    private static long timeOfBomb;      // Exploding time bomb
    private static long timeInterval;       // Time between 2 bombings
    private static Entity bomb;
    private static int bombImgStatus = 1;     // Change the operational state of the bomb
    private static int explosiveState = 1;  // Change bomb's explosive state
    private static final List<Entity> middleHorizontalBombs = new ArrayList<>();
    private static final List<Entity> middleVerticalBombs = new ArrayList<>();
    private static Entity edge_down = null;     // The bottom edge of the block blocks the character from going through
    private static Entity edge_up = null;       // The up edge of the block blocks the character from going through
    private static Entity edge_left = null;     // The left edge of the block blocks the character from going through
    private static Entity edge_right = null;    // The right edge of the block blocks the character from going through
    private static boolean is_edge = false;     // Check if that edge exists
    private static boolean is_middle = false;   // Check if the bomb explodes in the center (plus sign, not T )
    public static int bombStatus = 0;      // Check to see if there's a bomb there: //0 no bomb  //1 had bomb  //2 explosion

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

    public static void swapBombImg() {   // Show the animation from the time the bomb is placed to the time it explodes
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

    public static void createExplodingEdge() {   // Create an egde to prevent the character's movement as well as the explosion range of the bomb
        edge_down = new Bomb(bomb.getX( ) / 32, bomb.getY() / 32 + 1, Sprite.bomb_exploded.getFxImage());
        BombermanGame.entities.add(edge_down);
        edge_up = new Bomb(bomb.getX( ) / 32, bomb.getY() / 32 - 1, Sprite.bomb_exploded.getFxImage());
        BombermanGame.entities.add(edge_up);
        edge_left = new Bomb(bomb.getX( ) / 32 - 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
        BombermanGame.entities.add(edge_left);
        edge_right = new Bomb(bomb.getX( ) / 32 + 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
        BombermanGame.entities.add(edge_right);
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

            if (middleVerticalBombs.size() > 0) {
                for (Entity e : middleVerticalBombs) {
                    e.setImage(Sprite.explosion_vertical.getFxImage());
                }
            }

            if (middleHorizontalBombs.size() > 0) {
                for (Entity e : middleHorizontalBombs) {
                    e.setImage(Sprite.explosion_horizontal.getFxImage());
                }
            }

            explosiveState = 2;
        } else if (explosiveState == 2) {
            bomb.setImage(Sprite.bomb_exploded1.getFxImage());
            edge_down.setImage(Sprite.explosion_vertical_down_last1.getFxImage());
            edge_up.setImage(Sprite.explosion_vertical_top_last1.getFxImage());
            edge_left.setImage(Sprite.explosion_horizontal_left_last1.getFxImage());
            edge_right.setImage(Sprite.explosion_horizontal_right_last1.getFxImage());

            if (is_middle) {
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
            edge_down.setImage(Sprite.explosion_vertical_down_last2.getFxImage());
            edge_up.setImage(Sprite.explosion_vertical_top_last2.getFxImage());
            edge_left.setImage(Sprite.explosion_horizontal_left_last2.getFxImage());
            edge_right.setImage(Sprite.explosion_horizontal_right_last2.getFxImage());

            if (is_middle) {
                for (Entity e : middleVerticalBombs) {
                    e.setImage(Sprite.explosion_vertical2.getFxImage());
                }
                for (Entity e : middleHorizontalBombs) {
                    e.setImage(Sprite.explosion_horizontal2.getFxImage());
                }
            }

            explosiveState = 1;
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
                if (!is_edge) {
                    createExplodingEdge();
                    is_edge = true;
                }

                if (!is_middle) {
                    createMiddle();
                    is_middle = true;
                }

                explosionCenter();
                timeInterval += 100;
            } else {
                bombStatus = 0;
                bomb.setImage(Sprite.transparent.getFxImage());

                edge_down.setImage(Sprite.transparent.getFxImage());


                edge_up.setImage(Sprite.transparent.getFxImage());


                edge_left.setImage(Sprite.transparent.getFxImage());


                edge_right.setImage(Sprite.transparent.getFxImage());


                BombermanGame.entities.removeAll(middleVerticalBombs);
                BombermanGame.entities.removeAll(middleHorizontalBombs);
                middleVerticalBombs.clear();
                middleHorizontalBombs.clear();
                is_edge = false;
                is_middle = false;
            }
        }
    }

    public void update() {
        checkActive();
        checkExplosion();
    }
}

