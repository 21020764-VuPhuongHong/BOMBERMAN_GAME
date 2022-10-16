package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.CollisionHandle;
import uet.oop.bomberman.entities.Block.Brick;
import uet.oop.bomberman.entities.Block.Wall;
import uet.oop.bomberman.entities.Enemies.Enemy;
import uet.oop.bomberman.graphics.ConfigLevel;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Bomb extends Entity {
    public static int numOfBombs = 25;
    private long timeOfBomb;
    private int bombImgStatus = 1;
    private int countFrame = 0;
    private static final int MAX_NUM_FRAMES = 6;
    private int explosiveState = 1;
    private List<Entity> middleHorizontalBombs = new ArrayList<>();
    private List<Entity> middleVerticalBombs = new ArrayList<>();
    private Entity downEdge = null;
    private Entity upEdge = null;
    private Entity leftEdge = null;
    private Entity rightEdge = null;
    private boolean isEdge = false;
    private boolean isMiddle = false;
    private int bombStatus = 0;
    public static int explodingLength = 1;
    public static final long TIME_BETWEEN_2_BOMBS = 3000;
    public static long timePutBomb;
    public static long timeWaitForPutting2ndBomb = TIME_BETWEEN_2_BOMBS;
    public int timeBeforeExploding = 2000;
    private static final int TIME_EXPLODING = 500;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public void putBomb() {
        if (bombStatus == 0 && numOfBombs > 0) {
            bombStatus = 1;
            numOfBombs--;
            timeOfBomb = System.currentTimeMillis();
            timePutBomb = System.currentTimeMillis();
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
        if (this.getY() / SCALED_SIZE + explodingLength < ConfigLevel.height && !checkWallCollision(this.getX(), this.getY() + explodingLength * SCALED_SIZE)
                && !checkBrickCollision(this.getX(), this.getY() + (explodingLength - 1) * SCALED_SIZE)
                && !checkWallCollision(this.getX(), this.getY() + (explodingLength - 1) * SCALED_SIZE)) {
            downEdge = new Bomb(this.getX() / SCALED_SIZE, this.getY() / SCALED_SIZE + explodingLength, Sprite.transparent.getFxImage());
            BombermanGame.entities.add(downEdge);
        }

        if (this.getY() / SCALED_SIZE - explodingLength >= 0 && !checkWallCollision(this.getX(), this.getY() - explodingLength * SCALED_SIZE)
                && !checkBrickCollision(this.getX(), this.getY() - (explodingLength - 1) * SCALED_SIZE)
                && !checkWallCollision(this.getX(), this.getY() - (explodingLength - 1) * SCALED_SIZE)) {
            upEdge = new Bomb(this.getX() / SCALED_SIZE, this.getY() / SCALED_SIZE - explodingLength, Sprite.transparent.getFxImage());
            BombermanGame.entities.add(upEdge);
        }

        if (this.getX() / SCALED_SIZE - explodingLength >= 0 && !checkWallCollision(this.getX() - explodingLength * SCALED_SIZE, this.getY())
                && !checkBrickCollision(this.getX() - (explodingLength - 1) * SCALED_SIZE, this.getY())
                && !checkWallCollision(this.getX() - (explodingLength - 1) * SCALED_SIZE, this.getY())) {
            leftEdge = new Bomb(this.getX() / SCALED_SIZE - explodingLength, this.getY() / SCALED_SIZE, Sprite.transparent.getFxImage());
            BombermanGame.entities.add(leftEdge);
        }

        if (this.getX() / SCALED_SIZE + explodingLength < ConfigLevel.width && !checkWallCollision(this.getX() + explodingLength * SCALED_SIZE, this.getY())
                && !checkBrickCollision(this.getX() + (explodingLength - 1) * SCALED_SIZE, this.getY())
                && !checkWallCollision(this.getX() + (explodingLength - 1) * SCALED_SIZE, this.getY())) {
            rightEdge = new Bomb(this.getX() / SCALED_SIZE + explodingLength, this.getY() / SCALED_SIZE, Sprite.transparent.getFxImage());
            BombermanGame.entities.add(rightEdge);
        }
    }

    public void createMiddle() {
        Entity middle;

        if (!checkWallCollision(this.getX(), this.getY() + SCALED_SIZE)) {
            middle = new Bomb(this.getX() / SCALED_SIZE, this.getY() / SCALED_SIZE + 1, Sprite.transparent.getFxImage());
            middleVerticalBombs.add(middle);
        }

        if (!checkWallCollision(this.getX(), this.getY() - SCALED_SIZE)) {
            middle = new Bomb(this.getX() / SCALED_SIZE, this.getY() / SCALED_SIZE - 1, Sprite.transparent.getFxImage());
            middleVerticalBombs.add(middle);
        }

        if (!checkWallCollision(this.getX() - SCALED_SIZE, this.getY())) {
            middle = new Bomb(this.getX() / SCALED_SIZE - 1, this.getY() / SCALED_SIZE, Sprite.transparent.getFxImage());
            middleHorizontalBombs.add(middle);
        }

        if (!checkWallCollision(this.getX() + SCALED_SIZE, this.getY())) {
            middle = new Bomb(this.getX() / SCALED_SIZE + 1, this.getY() / SCALED_SIZE, Sprite.transparent.getFxImage());
            middleHorizontalBombs.add(middle);
        }


        BombermanGame.entities.addAll(middleHorizontalBombs);
        BombermanGame.entities.addAll(middleVerticalBombs);
    }


    public void explosion() {
        if (explosiveState == 1) {
            this.setImage(Sprite.bomb_exploded.getFxImage());
            handleCollision(this);

            if (middleVerticalBombs.size() > 0) {
                for (Entity e : middleVerticalBombs) {
                    e.setImage(Sprite.explosion_vertical.getFxImage());
                    handleCollision(e);
                }
            }

            if (middleHorizontalBombs.size() > 0) {
                for (Entity e : middleHorizontalBombs) {
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

            explosiveState = 2;
        } else if (explosiveState == 2) {
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

            if (middleVerticalBombs.size() > 0) {
                for (Entity e : middleVerticalBombs) {
                    e.setImage(Sprite.explosion_vertical1.getFxImage());
                }
            }

            if (middleHorizontalBombs.size() > 0) {
                for (Entity e : middleHorizontalBombs) {
                    e.setImage(Sprite.explosion_horizontal1.getFxImage());
                }
            }

            explosiveState = 3;
        } else if (explosiveState == 3) {
            this.setImage(Sprite.bomb_exploded2.getFxImage());

            if (middleVerticalBombs.size() > 0) {
                for (Entity e : middleVerticalBombs) {
                    e.setImage(Sprite.explosion_vertical2.getFxImage());
                }
            }

            if (middleHorizontalBombs.size() > 0) {
                for (Entity e : middleHorizontalBombs) {
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

            explosiveState = 0;
        }
    }

    private void handleBombExplosion() {
        if (bombStatus == 1) {
            if (System.currentTimeMillis() - timeOfBomb < timeBeforeExploding) {
                swapBombImg();
            } else {
                bombStatus = 2;
                timeOfBomb = System.currentTimeMillis();
            }
        } else if (bombStatus == 2) {
            if (System.currentTimeMillis() - timeOfBomb < TIME_EXPLODING) {
                if (explodingLength >= 2 && !isMiddle) {
                    createMiddle();
                    isMiddle = true;
                }

                if (!isEdge) {
                    createExplodingEdge();
                    isEdge = true;
                }

                explosion();
            } else {
                bombStatus = 0;

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

                if (middleVerticalBombs.size() > 0) {
                    BombermanGame.entities.removeAll(middleVerticalBombs);
                }
                if (middleHorizontalBombs.size() > 0) {
                    BombermanGame.entities.removeAll(middleHorizontalBombs);
                }

                middleVerticalBombs.clear();
                middleHorizontalBombs.clear();
                BombermanGame.entities.remove(this);
            }
        }
    }

    public void handleEnemyCollision(Entity e, Entity other) {
        if (other instanceof Enemy && CollisionHandle.intersects(e, other)) {
            Enemy enemy = (Enemy) other;
            enemy.setAliveState(false);
        }
    }

    public void handleBomberCollision(Entity e, Entity other) {
        if (other instanceof Bomber && CollisionHandle.intersects(e, other)) {
            Bomber bomber = (Bomber) other;
            bomber.setAliveState(false);
        }
    }

    public void handleBrickCollision(Entity e, Entity other) {
        if (other instanceof Brick && CollisionHandle.intersects(e, other)) {
            Brick brick = (Brick) other;
            brick.setIsExploded(true);
        }
    }

    public void handleBombCollision(Entity e, Entity other) {
        if (other instanceof Bomb && CollisionHandle.intersects(e, other)) {
            Bomb otherBomb = (Bomb) other;
            otherBomb.timeBeforeExploding = 0;
        }
    }

    public void handleCollision(Entity e) {
        for (Entity other : BombermanGame.entities) {
            handleEnemyCollision(e, other);
            handleBomberCollision(e, other);
            handleBrickCollision(e, other);
            handleBombCollision(e, other);
        }
    }

    public boolean checkBrickCollision(int x, int y) {
        Rectangle2D rec = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        for (Entity other : BombermanGame.entities) {
            if (other instanceof Brick && CollisionHandle.intersects(other, rec)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWallCollision(int x, int y) {
        Rectangle2D rec = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        for (Entity other : BombermanGame.stillObjects) {
            if (other instanceof Wall && CollisionHandle.intersects(other, rec)) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        handleBombExplosion();
    }
}

