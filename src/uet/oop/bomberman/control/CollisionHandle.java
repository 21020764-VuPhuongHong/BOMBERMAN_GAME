package uet.oop.bomberman.control;

import javafx.geometry.Rectangle2D;
import uet.oop.bomberman.entities.Entity;

public class CollisionHandle {
    private static Rectangle2D getBoundary(Entity e) {
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
}
