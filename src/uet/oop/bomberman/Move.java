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
        if (!checkCollision(e.getX(),e.getY() - e.getVelocityY())) {
            e.setY(e.getY() - e.getVelocityY());
        }
    }

    public static void moveDown(Entity e) {
        if (!checkCollision(e.getX(),e.getY() + e.getVelocityY())) {
            e.setY(e.getY() + e.getVelocityY());
        }
    }

    private static Rectangle2D getBoundary(Entity e) {
        return new Rectangle2D(e.getX(), e.getY(), e.getWidth(), e.getHeight());
    }

    public static boolean intersects(Entity e, Rectangle2D rec) {
        Rectangle2D rec1 = getBoundary(e);
        Rectangle2D rec2 = new Rectangle2D(rec1.getMinX(), rec1.getMinY(), rec1.getWidth() * 3 / 4, rec1.getHeight());
        return rec2.intersects(rec);
    }

    public static boolean checkCollision(int x, int y) {
        Rectangle2D rec = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        for (Entity other : BombermanGame.entities) {
            if (intersects(other, rec)) {
                if (other instanceof Brick) {
                    return true;
                }
            }
        }

        for (Entity other : BombermanGame.stillObjects) {
            if (intersects(other, rec)) {
                if (other instanceof Wall) {
                    return true;
                }
            }
        }
        return false;
    }
}
