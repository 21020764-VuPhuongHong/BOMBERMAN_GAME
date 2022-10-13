package uet.oop.bomberman.entities;

import uet.oop.bomberman.entities.Enemies.Ballom;
import uet.oop.bomberman.entities.Enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

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
                //moveUp(enemy, limH, limW);
                enemy.addVelocity(0, -Sprite.step);
                if (enemy instanceof Ballom) {
                    if (enemy.getSwapMoveImg() == 1) {
                        enemy.setImage(Sprite.balloom_left1.getFxImage());
                        enemy.setSwapMoveImg(2);
                    } else if (enemy.getSwapMoveImg() == 2) {
                        enemy.setImage(Sprite.balloom_left2.getFxImage());
                        enemy.setSwapMoveImg(3);
                    } else if (enemy.getSwapMoveImg() == 3) {
                        enemy.setImage(Sprite.balloom_left3.getFxImage());
                        enemy.setSwapMoveImg(1);
                    }
                }
                break;
            case 1:
                //moveDown(enemy, limH, limW);
                enemy.addVelocity(0, Sprite.step);
                if (enemy.getSwapMoveImg() == 1) {
                    enemy.setImage(Sprite.balloom_right1.getFxImage());
                    enemy.setSwapMoveImg(2);
                } else if (enemy.getSwapMoveImg() == 2) {
                    enemy.setImage(Sprite.balloom_right2.getFxImage());
                    enemy.setSwapMoveImg(3);
                } else if (enemy.getSwapMoveImg() == 3) {
                    enemy.setImage(Sprite.balloom_right3.getFxImage());
                    enemy.setSwapMoveImg(1);
                }
                break;
            case 2:
                //moveRight(enemy, limH, limW);
                enemy.addVelocity(Sprite.step, 0);
                if (enemy.getSwapMoveImg() == 1) {
                    enemy.setImage(Sprite.balloom_right1.getFxImage());
                    enemy.setSwapMoveImg(2);
                } else if (enemy.getSwapMoveImg() == 2) {
                    enemy.setImage(Sprite.balloom_right2.getFxImage());
                    enemy.setSwapMoveImg(3);
                } else if (enemy.getSwapMoveImg() == 3) {
                    enemy.setImage(Sprite.balloom_right3.getFxImage());
                    enemy.setSwapMoveImg(1);
                }
                break;
            case 3:
                //moveLeft(enemy, limH, limW);
                enemy.addVelocity(-Sprite.step, 0);
                if (enemy.getSwapMoveImg() == 1) {
                    enemy.setImage(Sprite.balloom_left1.getFxImage());
                    enemy.setSwapMoveImg(2);
                } else if (enemy.getSwapMoveImg() == 2) {
                    enemy.setImage(Sprite.balloom_left2.getFxImage());
                    enemy.setSwapMoveImg(3);
                } else if (enemy.getSwapMoveImg() == 3) {
                    enemy.setImage(Sprite.balloom_left3.getFxImage());
                    enemy.setSwapMoveImg(1);
                }
                break;
        }

        update(enemy, limH, limW);
    }
    /*

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
    }*/

    public static void update(Enemy enemy, double limH, double limW) {

        if ((enemy.x + enemy.velocityX < limW)
                && (enemy.x + enemy.velocityX > 0)) {
            enemy.x += enemy.velocityX;
        }

        if ((enemy.y + enemy.velocityY < limH)
                && (enemy.y + enemy.velocityY > 0)) {
            enemy.y += enemy.velocityY;
        }


        /*
        if((enemy.x + enemy.velocityX*time < limW - Sprite.SCALED_SIZE)
                && (enemy.x + enemy.velocityX*time > 0))
                {
                    enemy.x += velocityX*time;
                }

        if((enemy.y + enemy.velocityY*time < limH - Sprite.SCALED_SIZE)
                && (enemy.y + enemy.velocityY*time > 0))
                {
                    enemy.y += velocityY*time;
                }*/
    }
}
