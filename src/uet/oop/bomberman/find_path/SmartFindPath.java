package uet.oop.bomberman.find_path;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.entities.enemies.Enemy;

public class SmartFindPath {
    public static void findPath(Enemy e) {
        double[] distance = new double[4];
        distance[0] = calDistanceFromBomber(e.getX(), e.getY() - e.getVelocityY());
        distance[1] = calDistanceFromBomber(e.getX(), e.getY() + e.getVelocityY());
        distance[2] = calDistanceFromBomber(e.getX() - e.getVelocityX(), e.getY());
        distance[3] = calDistanceFromBomber(e.getX() + e.getVelocityX(), e.getY());

        boolean[] canMove = new boolean[4];
        canMove[0] = Move.canMoveUp(e);
        canMove[1] = Move.canMoveDown(e);
        canMove[2] = Move.canMoveLeft(e);
        canMove[3] = Move.canMoveRight(e);

        double minValue = Double.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < 4; i++) {
            if (distance[i] < minValue && canMove[i] && e.shouldMove[i]) {
                minValue = distance[i];
                minIndex = i;
            }
        }

        if (minIndex != -1) {
            e.direction = minIndex;

            int reverseDir = minIndex < 2 ? 1 - minIndex : 5 - minIndex;
            e.shouldMove[reverseDir] = false;
            e.shouldMove[3 - minIndex] = true;
            e.shouldMove[3 - reverseDir] = true;
        } else {
            int reverseDir = e.direction < 2 ? 1 - e.direction : 5 - e.direction;
            e.shouldMove[reverseDir] = true;
        }
    }

    public static double calDistanceFromBomber(int x, int y) {
        return Math.pow(x - BombermanGame.bomberman.getX(), 2) + Math.pow(y - BombermanGame.bomberman.getY(), 2);
    }
}
