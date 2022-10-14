package uet.oop.bomberman;

import javafx.geometry.Rectangle2D;
import uet.oop.bomberman.entities.Block.Brick;
import uet.oop.bomberman.entities.Block.Wall;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Move {
    public static void moveLeft(Entity e) {
        if (!checkCollision(e.getX() - e.getVelocityX(), e.getY())) {
            e.setX(e.getX() - e.getVelocityX());
        }
    }

    public static void moveRight(Entity e) {
        if (!checkCollision(e.getX() + e.getVelocityX(), e.getY())) {
            e.setX(e.getX() + e.getVelocityX());
        }
    }

    public static void moveUp(Entity e) {
        if (!checkCollision(e.getX(), e.getY() - e.getVelocityY())) {
            e.setY(e.getY() - e.getVelocityY());
        }
    }

    public static void moveDown(Entity e) {
        if (!checkCollision(e.getX(), e.getY() + e.getVelocityY())) {
            e.setY(e.getY() + e.getVelocityY());
        }
    }

    public static boolean checkCollision(int x, int y) {
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
}
