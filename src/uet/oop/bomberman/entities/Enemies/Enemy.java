package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Enemy extends Entity {
    protected int swapMoveImg = 1;
    protected boolean isAlive = true;
    protected int swapDeathImg = 1;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setAliveState(boolean state) {
        this.isAlive = state;
    }

    public abstract void update();
}
