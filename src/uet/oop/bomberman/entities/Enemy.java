package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Enemy extends Entity {
    private boolean isAlive = true;
    private int swapDeathImg = 1;
    public Enemy (int x, int y, Image img) {
        super(x, y , img);
        this.velocityX = 30;
        this.velocityY = 30;
    }

    private void setAliveState(boolean state) {
        this.isAlive = state;
    }

    private void killEnemy(Enemy enemy) {
        if (swapDeathImg == 1) {
            enemy.setImage(Sprite.mob_dead1.getFxImage());
            swapDeathImg = 2;
        }
        else if (swapDeathImg == 2) {
            enemy.setImage(Sprite.mob_dead2.getFxImage());
            swapDeathImg = 3;
        }
        else if (swapDeathImg == 3) {
            enemy.setImage(Sprite.mob_dead3.getFxImage());
            swapDeathImg = 4;
        }
        else {
            enemy.setAliveState(false);
            BombermanGame.entities.remove(enemy);
            swapDeathImg = 1;
        }
    }

    public void update(){
        for (Entity e : BombermanGame.entities) {
            if (e instanceof Enemy) {
                Enemy enemy = (Enemy) e;
                if (BombermanGame.killedEntities[enemy.getX() / 32][enemy.getY() / 32] == 1) {
                    killEnemy(enemy);
                }
            }
        }
    }
}
