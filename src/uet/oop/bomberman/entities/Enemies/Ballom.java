package uet.oop.bomberman.entities.Enemies;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Block.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Move;
import uet.oop.bomberman.entities.Block.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Ballom extends Enemy {
    public Ballom(int x, int y, Image img) {
        super(x, y, img);
    }

    public void update() {
        super.update();
        Move.move(this, this.limH, this.limW);
        handleCollapse();
        if (!isAlive) {
            this.setImage(Sprite.transparent.getFxImage());
        }
    }

    @Override
    public boolean intersectsWith(Entity e) {
        //return e.getBoundary().intersects(this.getBoundary());
        Rectangle2D rec1 = this.getBoundary();
        Rectangle2D rec2 = new Rectangle2D(rec1.getMinX(), rec1.getMinY(), rec1.getWidth(), rec1.getHeight());
        return rec2.intersects(e.getBoundary());
    }

    @Override
    public void handleCollapse() {
        for (Entity e : BombermanGame.entities) {
            if (this.intersectsWith(e)) {
                if (e instanceof Brick) {
                    double velocityX = this.getVelocityX();
                    double velocityY = this.getVelocityY();
                    //System.out.println("brick" + velocityX + " " + velocityY);
                    this.addVelocity(-velocityX, -velocityY);
                    Move.update(this, limH, limW);
                }
            }
        }

        for (Entity e : BombermanGame.stillObjects) {
            if (this.intersectsWith(e)) {
                if (e instanceof Wall) {
                    double velocityX = this.getVelocityX();
                    double velocityY = this.getVelocityY();
                    //System.out.println("wall" + velocityX + " " + velocityY);
                    this.addVelocity(-velocityX, -velocityY);
                    Move.update(this, limH, limW);
                }
            }
        }
    }
}

