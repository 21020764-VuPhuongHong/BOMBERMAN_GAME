package uet.oop.bomberman.find_path;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.enemies.Enemy;

public class FindPath1 {
    public static void findPath(Enemy e) {
        if (BombermanGame.bomberman.getX() == e.getX() && BombermanGame.bomberman.getY() == e.getY()) {
            e.shouldMoveDown = false;
            e.shouldMoveUp = false;
            e.shouldMoveLeft = false;
            e.shouldMoveRight = false;
        } else if (BombermanGame.bomberman.getX() == e.getX()) {
            if (BombermanGame.bomberman.getY() > e.getY() && e.shouldMoveDown) {
                e.direction = 1;
            } else if (BombermanGame.bomberman.getY() < e.getY() && e.shouldMoveUp) {
                e.direction = 0;
            }
        } else if (BombermanGame.bomberman.getY() == e.getY()) {
            if (BombermanGame.bomberman.getX() > e.getX() && e.shouldMoveRight) {
                e.direction = 3;
            } else if (BombermanGame.bomberman.getX() < e.getX() && e.shouldMoveLeft) {
                e.direction = 2;
            }
        }
    }
}
