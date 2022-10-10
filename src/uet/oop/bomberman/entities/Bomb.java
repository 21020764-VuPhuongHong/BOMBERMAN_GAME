package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    public static int[][] id_objects;
    public static int numOfBombs = 25;
    private static long timeOfBomb;      // Exploding time bomb
    private static long timeInterval;       // Time between 2 bombings
    private static Entity bomb;
    private static int bombImgStatus = 1;     // Change the operational state of the bomb
    private static int swap_explosion = 1;  // Change bomb's explosive state
    private static final List<Entity> middleHorizontalBombs = new ArrayList();
    private static final List<Entity> middleVerticalBombs = new ArrayList();
    public static int power_bomb = 0;   // Bomb's destructive power
    private static int power_bomb_down = 0;     // Bomb's destructive power from top to bottom
    private static int power_bomb_up = 0;       // The bomb's destructive power is from the bottom up
    private static int power_bomb_left = 0;     // Bomb's destructive power is from right to left
    private static int power_bomb_right = 0;    // The explosive power of the bomb is from left to right
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

    public static void putBomb() {      // The function used for the bomber to place the bomb
        if (bombStatus == 0 && numOfBombs > 0) {
            bombStatus = 1;
            numOfBombs--;
            timeOfBomb = System.currentTimeMillis();
            timeInterval = timeOfBomb;
            bomb = new Bomb(Bomber.getX(), Bomber.getY(), Sprite.bomb.getFxImage());
            BombermanGame.entities.add(bomb);
            id_objects[Bomber.getX()][Bomber.getY()] = 4;
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
        int i;
        if (Blocked.block_down_bomb(bomb, 0)) {
            edge_down = new Bomb(bomb.getX(), bomb.getY()+ 1, Sprite.bomb_exploded.getFxImage());
            if (power_bomb > 0) {
                for (i = 1; i <= power_bomb && Blocked.block_down_bomb(bomb, i); ++i) {
                    edge_down.setY(bomb.getY() + 32 + i * 32);
                    ++power_bomb_down;
                }
            }
            
            BombermanGame.entities.add(edge_down);
        }

        if (Blocked.block_up_bomb(bomb, 0)) {
            edge_up = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - 1, Sprite.bomb_exploded.getFxImage());
            if (power_bomb > 0) {
                for (i = 1; i <= power_bomb && Blocked.block_up_bomb(bomb, i); ++i) {
                    edge_up.setY(bomb.getY() - 32 - i * 32);
                    ++power_bomb_up;
                }
            }

            BombermanGame.entities.add(edge_up);
        }

        if (Blocked.block_left_bomb(bomb, 0)) {
            edge_left = new Bomb(bomb.getX() / 32 - 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            if (power_bomb > 0) {
                for (i = 1; i <= power_bomb && Blocked.block_left_bomb(bomb, i); ++i) {
                    edge_left.setX(bomb.getX() - 32 - i * 32);
                    ++power_bomb_left;
                }
            }

            BombermanGame.entities.add(edge_left);
        }

        if (Blocked.block_right_bomb(bomb, 0)) {
            edge_right = new Bomb(bomb.getX() / 32 + 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            if (power_bomb > 0) {
                for (i = 1; i <= power_bomb && Blocked.block_right_bomb(bomb, i); ++i) {
                    edge_right.setX(bomb.getX() + 32 + i * 32);
                    ++power_bomb_right;
                }
            }

            BombermanGame.entities.add(edge_right);
        }
    }

    public static void createMiddle() {     // Adjust the bomb to explode at the center position
        Entity middle;
        int i;
        for (i = 1; i <= power_bomb_down; i++) {
            middle = new Bomb(bomb.getX(), bomb.getY()+ i, Sprite.bomb_exploded.getFxImage());
            middleVerticalBombs.add(middle);
        }

        for (i = 1; i <= power_bomb_up; i++) {
            middle = new Bomb(bomb.getX(), bomb.getY()- i, Sprite.bomb_exploded.getFxImage());
            middleVerticalBombs.add(middle);
        }

        for (i = 1; i <= power_bomb_left; i++) {
            middle = new Bomb(bomb.getX()- i, bomb.getY(), Sprite.bomb_exploded.getFxImage());
            middleHorizontalBombs.add(middle);
        }

        for (i = 1; i <= power_bomb_right; i++) {
            middle = new Bomb(bomb.getX()+ i, bomb.getY(), Sprite.bomb_exploded.getFxImage());
            middleHorizontalBombs.add(middle);
        }

        BombermanGame.entities.addAll(middleHorizontalBombs);
        BombermanGame.entities.addAll(middleVerticalBombs);
    }

    public static void explosionCenter() {      // Determine the explosion center of the bomb
        if (swap_explosion == 1) {
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

            swap_explosion = 2;
        } else if (swap_explosion == 2) {
            bomb.setImage(Sprite.bomb_exploded1.getFxImage());
            if (Blocked.block_down_bomb(bomb, power_bomb_down)) {
                edge_down.setImage(Sprite.explosion_vertical_down_last1.getFxImage());
            }

            if (Blocked.block_up_bomb(bomb, power_bomb_up)) {
                edge_up.setImage(Sprite.explosion_vertical_top_last1.getFxImage());
            }

            if (Blocked.block_left_bomb(bomb, power_bomb_left)) {
                edge_left.setImage(Sprite.explosion_horizontal_left_last1.getFxImage());
            }

            if (Blocked.block_right_bomb(bomb, power_bomb_right)) {
                edge_right.setImage(Sprite.explosion_horizontal_right_last1.getFxImage());
            }

            if (is_middle) {
                for (Entity e : middleVerticalBombs) {
                    e.setImage(Sprite.explosion_vertical1.getFxImage());
                }
                for (Entity e : middleHorizontalBombs) {
                    e.setImage(Sprite.explosion_horizontal1.getFxImage());
                }
            }

            swap_explosion = 3;
        } else if (swap_explosion == 3) {
            bomb.setImage(Sprite.bomb_exploded2.getFxImage());
            if (Blocked.block_down_bomb(bomb, power_bomb_down)) {
                edge_down.setImage(Sprite.explosion_vertical_down_last2.getFxImage());
            }

            if (Blocked.block_up_bomb(bomb, power_bomb_up)) {
                edge_up.setImage(Sprite.explosion_vertical_top_last2.getFxImage());
            }

            if (Blocked.block_left_bomb(bomb, power_bomb_left)) {
                edge_left.setImage(Sprite.explosion_horizontal_left_last2.getFxImage());
            }

            if (Blocked.block_right_bomb(bomb, power_bomb_right)) {
                edge_right.setImage(Sprite.explosion_horizontal_right_last2.getFxImage());
            }

            if (is_middle) {
                for (Entity e : middleVerticalBombs) {
                    e.setImage(Sprite.explosion_vertical2.getFxImage());
                }
                for (Entity e : middleHorizontalBombs) {
                    e.setImage(Sprite.explosion_horizontal2.getFxImage());
                }
            }

            swap_explosion = 1;
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

                if (power_bomb > 0 && !is_middle) {
                    createMiddle();
                    is_middle = true;
                }

                explosionCenter();
                timeInterval += 100;
            } else {
                bombStatus = 0;
                id_objects[bomb.getX()][bomb.getY()] = 0;
                bomb.setImage(Sprite.transparent.getFxImage());
                if (Blocked.block_down_bomb(bomb, power_bomb_down)) {
                    edge_down.setImage(Sprite.transparent.getFxImage());
                    id_objects[edge_down.getX()][edge_down.getY()] = 0;
                }

                if (Blocked.block_up_bomb(bomb, power_bomb_up)) {
                    edge_up.setImage(Sprite.transparent.getFxImage());
                    id_objects[edge_up.getX()][edge_up.getY()] = 0;
                }

                if (Blocked.block_left_bomb(bomb, power_bomb_left)) {
                    edge_left.setImage(Sprite.transparent.getFxImage());
                    id_objects[edge_left.getX()][edge_left.getY()] = 0;
                }

                if (Blocked.block_right_bomb(bomb, power_bomb_right)) {
                    edge_right.setImage(Sprite.transparent.getFxImage());
                    id_objects[edge_right.getX()][edge_right.getY()] = 0;
                }

                if (is_middle) {
                    for (Entity e : middleHorizontalBombs) {
                        id_objects[e.getX()][e.getY()] = 0;
                    }
                    for (Entity e : middleVerticalBombs) {
                        id_objects[e.getX()][e.getY()] = 0;
                    }
                }

                BombermanGame.entities.removeAll(middleVerticalBombs);
                BombermanGame.entities.removeAll(middleHorizontalBombs);
                middleVerticalBombs.clear();
                middleHorizontalBombs.clear();
                is_edge = false;
                is_middle = false;
                power_bomb_down = 0;
                power_bomb_up = 0;
                power_bomb_left = 0;
                power_bomb_right = 0;
            }
        }
    }

    @Override
    public void update() {
        checkActive();
        checkExplosion();
    }
}
