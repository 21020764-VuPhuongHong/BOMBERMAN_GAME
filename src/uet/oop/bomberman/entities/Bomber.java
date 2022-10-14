package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.CollisionHandle;
import uet.oop.bomberman.Move;
import uet.oop.bomberman.entities.Enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private boolean isAlive = true;
    private int swapDeathImg = 1;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setAliveState(boolean state) {
        this.isAlive = state;
    }

    private void killBomber() {
        if (swapDeathImg == 1) {
            this.setImage(Sprite.player_dead1.getFxImage());
            swapDeathImg = 2;
        } else if (swapDeathImg == 2) {
            this.setImage(Sprite.player_dead2.getFxImage());
            swapDeathImg = 3;
        } else if (swapDeathImg == 3) {
            this.setImage(Sprite.player_dead3.getFxImage());
            swapDeathImg = 4;
        } else {
            BombermanGame.entities.remove(this);
        }
    }

    @Override
    public void update() {
        if (!isAlive) {
            this.killBomber();
        }
        for (Entity e : BombermanGame.entities) {
            if (e instanceof Enemy && CollisionHandle.intersects(this, e)) {
                this.setAliveState(false);
            }
        }
    }
}
