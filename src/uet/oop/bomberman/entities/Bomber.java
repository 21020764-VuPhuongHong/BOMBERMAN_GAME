package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Block.Brick;
import uet.oop.bomberman.entities.Block.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;


public class Bomber extends Entity {

    public ArrayList<String> input = new ArrayList<>();
    private boolean isAlive = true;
    private int swapDeathImg = 1;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    private void setAliveState(boolean state) {
        this.isAlive = state;
    }

    private void killBomber(Bomber bomberman) {
        if (swapDeathImg == 1) {
            bomberman.setImage(Sprite.player_dead1.getFxImage());
            swapDeathImg = 2;
        } else if (swapDeathImg == 2) {
            bomberman.setImage(Sprite.player_dead2.getFxImage());
            swapDeathImg = 3;
        } else if (swapDeathImg == 3) {
            bomberman.setImage(Sprite.player_dead3.getFxImage());
            swapDeathImg = 4;
        } else {
            BombermanGame.entities.remove(bomberman);
            //bomberman.setImage(Sprite.transparent.getFxImage());
            bomberman.setAliveState(false);
        }
    }

    @Override
    public void update() {
        if (BombermanGame.killedEntities[bomberman.getX() / SCALED_SIZE][bomberman.getY() / SCALED_SIZE] == 1) {
            killBomber(bomberman);
        }


        if ((this.x + this.velocityX < limW)
                && (this.x + this.velocityX > 0)) {
            this.x += velocityX;
        }

        if ((this.y + this.velocityY < limH)
                && (this.y + this.velocityY > 0)) {
            this.y += velocityY;
        }


        /*
        if((this.x + this.velocityX*time < limW - Sprite.SCALED_SIZE)
                && (this.x + this.velocityX*time > 0))
                {
                    this.x += velocityX*time;
                }

        if((this.y + this.velocityY*time < limH - Sprite.SCALED_SIZE)
                && (this.y + this.velocityY*time > 0))
                {
                    this.y += velocityY*time;
                }*/
    }

    @Override
    public boolean intersectsWith(Entity e) {
        //return e.getBoundary().intersects(this.getBoundary());
        Rectangle2D rec1 = this.getBoundary();
        Rectangle2D rec2 = new Rectangle2D(rec1.getMinX(), rec1.getMinY(), rec1.getWidth() * 3 / 4, rec1.getHeight());
        return rec2.intersects(e.getBoundary());
    }

    @Override
    public void handleCollapse() {
        for (Entity e : BombermanGame.entities) {
            if (this.intersectsWith(e)) {
                if (e instanceof Brick) {
                    double velocityX = this.getVelocityX();
                    double velocityY = this.getVelocityY();
                    System.out.println("brick" + velocityX + " " + velocityY);
                    this.addVelocity(-velocityX, -velocityY);
                    this.update();
                }
            }
        }

        for (Entity e : BombermanGame.stillObjects) {
            if (this.intersectsWith(e)) {
                if (e instanceof Wall) {
                    double velocityX = this.getVelocityX();
                    double velocityY = this.getVelocityY();
                    System.out.println("wall" + velocityX + " " + velocityY);
                    this.addVelocity(-velocityX, -velocityY);
                    this.update();
                }
            }
        }
    }
}
