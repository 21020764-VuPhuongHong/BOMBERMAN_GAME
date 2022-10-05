package uet.oop.bomberman.entities;

import java.util.Random;

public class Move {
    private static Random seed = new Random();

    public static int getRandomDirection() {
        return seed.nextInt(4);
    }

    public static void move(Enemy enemy) {
        int direction = getRandomDirection();
        //System.out.println(direction);
        switch (direction) {
            case 0:
                moveUp(enemy);
                break;
            case 1:
                moveDown(enemy);
                break;
            case 2:
                moveRight(enemy);
                break;
            case 3:
                moveLeft(enemy);
                break;
        }
    }

    public static void moveUp(Enemy enemy) {
        enemy.y -= enemy.velocityY;
    }

    public static void moveDown(Enemy enemy) {
        enemy.y += enemy.velocityY;
    }

    public static void moveRight(Enemy enemy) {
        enemy.x += enemy.velocityX;
    }

    public static void moveLeft(Enemy enemy) {
        enemy.x -= enemy.velocityX;
    }
}
