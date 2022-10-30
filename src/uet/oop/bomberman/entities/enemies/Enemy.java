package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import java.util.Random;

public abstract class Enemy extends Entity {
    protected int swapMoveImg = 1;
    protected boolean isAlive = true;
    protected int swapDeathImg = 1;
    public static final int MAX_NUM_FRAMES = 25;
    protected int countFrame = 0;
    public int direction = 0;
    public boolean shouldMoveUp = true;
    public boolean shouldMoveDown = true;
    public boolean shouldMoveRight = true;
    public boolean shouldMoveLeft = true;
    protected Random seed = new Random();

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setAliveState(boolean state) {
        this.isAlive = state;
    }

    public abstract void update();
}
