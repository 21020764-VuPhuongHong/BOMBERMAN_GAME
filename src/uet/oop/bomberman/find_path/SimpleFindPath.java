package uet.oop.bomberman.find_path;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.enemies.Enemy;

public class SimpleFindPath {
    public static void findPath(Enemy e) {
        if (BombermanGame.bomberman.getX() == e.getX() && BombermanGame.bomberman.getY() == e.getY()) {
            for (int i = 0; i < 4; i++) {
                e.shouldMove[i] = false;
            }
        } else if (BombermanGame.bomberman.getX() == e.getX()) {
            if (BombermanGame.bomberman.getY() > e.getY() && e.shouldMove[1]) {
                e.direction = 1;
            } else if (BombermanGame.bomberman.getY() < e.getY() && e.shouldMove[0]) {
                e.direction = 0;
            }
        } else if (BombermanGame.bomberman.getY() == e.getY()) {
            if (BombermanGame.bomberman.getX() > e.getX() && e.shouldMove[3]) {
                e.direction = 3;
            } else if (BombermanGame.bomberman.getX() < e.getX() && e.shouldMove[2]) {
                e.direction = 2;
            }
        }
    }
}
