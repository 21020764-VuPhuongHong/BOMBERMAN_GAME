package uet.oop.bomberman.entities;

import java.util.Random;

public class Move {
    private static Random seed = new Random();

    public static int getRandomDirection() {
        return seed.nextInt(4);
    }

    public static void move(Enemy enemy, double limH, double limW) {
        int direction = getRandomDirection();
        //System.out.println(direction);
        switch (direction) {
            case 0:
                moveUp(enemy, limH, limW);
                break;
            case 1:
                moveDown(enemy, limH, limW);
                break;
            case 2:
                moveRight(enemy, limH, limW);
                break;
            case 3:
                moveLeft(enemy, limH, limW);
                break;
        }
    }

    public static void moveUp(Enemy enemy, double limH, double limW)
    {
        if(enemy.y - enemy.velocityY > 0) {
            enemy.y -= enemy.velocityY;
        }
    }

    public static void moveDown(Enemy enemy, double limH, double limW)
    {
        if(enemy.y + enemy.velocityY < limH) {
            enemy.y += enemy.velocityY;
        }
    }

    public static void moveRight(Enemy enemy, double limH, double limW)
    {
        if(enemy.x + enemy.velocityX < limW) {
            enemy.x += enemy.velocityX;
        }
    }

    public static void moveLeft(Enemy enemy, double limH, double limW)
    {
        if(enemy.x - enemy.velocityX > 0) {
            enemy.x -= enemy.velocityX;
        }
    }
}
