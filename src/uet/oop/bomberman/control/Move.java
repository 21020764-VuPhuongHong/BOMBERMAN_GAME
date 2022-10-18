package uet.oop.bomberman.control;

import javafx.geometry.Rectangle2D;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Block.Brick;
import uet.oop.bomberman.entities.Block.Wall;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Move {
    public static void moveLeft(Entity e) {
        if (canMoveLeft(e)) {
            e.setX(e.getX() - e.getVelocityX());
        }
    }

    public static void moveRight(Entity e) {
        if (canMoveRight(e)) {
            e.setX(e.getX() + e.getVelocityX());
        }
    }

    public static void moveUp(Entity e) {
        if (canMoveUp(e)) {
            e.setY(e.getY() - e.getVelocityY());
        }
    }

    public static void moveDown(Entity e) {
        if (canMoveDown(e)) {
            e.setY(e.getY() + e.getVelocityY());
        }
    }

    public static boolean checkBrickAndWall(int x, int y) {
        Rectangle2D rec = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        for (Entity other : BombermanGame.entities) {
            if (other instanceof Brick && CollisionHandle.intersects(other, rec)) {
                return true;
            }
        }

        for (Entity other : BombermanGame.stillObjects) {
            if (other instanceof Wall && CollisionHandle.intersects(other, rec)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkBomb(int x, int y) {
        Rectangle2D rec = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        for (Entity other : BombermanGame.entities) {
            if (other instanceof Bomb && CollisionHandle.intersects(other, rec)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canMoveLeft(Entity e) {
        if (checkBrickAndWall(e.getX() - e.getVelocityX(), e.getY())) {
            return false;
        }
        if (checkBomb(e.getX() - e.getVelocityX(), e.getY())) {
            if (e instanceof Bomber && Bomb.bomberFirstGoLeftThroughBomb) {
                Bomb.bomberFirstGoRightThroughBomb = false;
                Bomb.bomberFirstGoUpThroughBomb = false;
                Bomb.bomberFirstGoDownThroughBomb = false;
                return true;
            }
            return false;
        }
        return true;
    }

    public static boolean canMoveRight(Entity e) {
        if (checkBrickAndWall(e.getX() + e.getVelocityX(), e.getY())) {
            return false;
        }
        if (checkBomb(e.getX() + e.getVelocityX(), e.getY())) {
            if (e instanceof Bomber && Bomb.bomberFirstGoRightThroughBomb) {
                Bomb.bomberFirstGoLeftThroughBomb = false;
                Bomb.bomberFirstGoUpThroughBomb = false;
                Bomb.bomberFirstGoDownThroughBomb = false;
                return true;
            }
            return false;
        }
        return true;
    }

    public static boolean canMoveUp(Entity e) {
        if (checkBrickAndWall(e.getX(), e.getY() - e.getVelocityY())) {
            return false;
        }
        if (checkBomb(e.getX(), e.getY() - e.getVelocityY())) {
            if (e instanceof Bomber && Bomb.bomberFirstGoUpThroughBomb) {
                Bomb.bomberFirstGoLeftThroughBomb = false;
                Bomb.bomberFirstGoRightThroughBomb = false;
                Bomb.bomberFirstGoDownThroughBomb = false;
                return true;
            }
            return false;
        }
        return true;
    }

    public static boolean canMoveDown(Entity e) {
        if (checkBrickAndWall(e.getX(), e.getY() + e.getVelocityY())) {
            return false;
        }
        if (checkBomb(e.getX(), e.getY() + e.getVelocityY())) {
            if (e instanceof Bomber && Bomb.bomberFirstGoDownThroughBomb) {
                Bomb.bomberFirstGoLeftThroughBomb = false;
                Bomb.bomberFirstGoRightThroughBomb = false;
                Bomb.bomberFirstGoUpThroughBomb = false;
                return true;
            }
            return false;
        }
        return true;
    }
}
