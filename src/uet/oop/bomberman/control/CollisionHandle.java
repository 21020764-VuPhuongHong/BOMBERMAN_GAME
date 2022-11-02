package uet.oop.bomberman.control;

import javafx.geometry.Rectangle2D;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.still_block.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class CollisionHandle {
    public static Rectangle2D getBoundary(Entity e) {
        return new Rectangle2D(e.getX(), e.getY(), e.getWidth(), e.getHeight());
    }

    public static boolean intersects(Entity e, Rectangle2D rec) {
        Rectangle2D rec1 = getBoundary(e);
        Rectangle2D rec2 = new Rectangle2D(rec1.getMinX(), rec1.getMinY(), rec1.getWidth(), rec1.getHeight());
        return rec2.intersects(rec);
    }

    public static boolean intersects(Entity e1, Entity e2) {
        Rectangle2D rec1 = getBoundary(e1);
        Rectangle2D rec2 = new Rectangle2D(rec1.getMinX(), rec1.getMinY(), rec1.getWidth(), rec1.getHeight());
        return rec2.intersects(getBoundary(e2));
    }

    public static boolean checkWall(int x, int y) {
        Rectangle2D rec = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        for (Entity other : BombermanGame.stillObjects) {
            if (other instanceof Wall && intersects(other, rec)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkBrick(int x, int y) {
        Rectangle2D rec = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        for (Entity other : BombermanGame.entities) {
            if (other instanceof Brick && intersects(other, rec)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkBomb(int x, int y) {
        Rectangle2D rec = new Rectangle2D(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        for (Bomb bomb : BombermanGame.listBombs) {
            if (intersects(bomb, rec)) {
                return true;
            }
        }
        return false;
    }
}
