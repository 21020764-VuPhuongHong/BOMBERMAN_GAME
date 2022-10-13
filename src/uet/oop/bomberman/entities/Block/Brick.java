package uet.oop.bomberman.entities.Block;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;

public class Brick extends Entity {
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        for (Entity e : BombermanGame.entities) {
            if (e instanceof Brick) {
                Brick brick = (Brick) e;
                if (BombermanGame.killedEntities[brick.getX() / 32][brick.getY() / 32] == 1) {
                    BombermanGame.entities.remove(brick);
                }
            }
        }
    }
}
