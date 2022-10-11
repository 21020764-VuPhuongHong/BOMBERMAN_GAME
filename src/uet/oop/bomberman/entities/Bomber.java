package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.bomberman;


public class Bomber extends Entity {

    public ArrayList <String> input = new ArrayList<>();
    private boolean isAlive = true;
    private int swapDeathImg = 1;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    private void setAliveState(boolean state) {
        this.isAlive = state;
    }

    private void killBomber(Bomber bomberman) {
        if (swapDeathImg == 1) {
            bomberman.setImage(Sprite.player_dead1.getFxImage());
            swapDeathImg = 2;
        }
        else if (swapDeathImg == 2) {
            bomberman.setImage(Sprite.player_dead2.getFxImage());
            swapDeathImg = 3;
        }
        else if (swapDeathImg == 3) {
            bomberman.setImage(Sprite.player_dead3.getFxImage());
            swapDeathImg = 4;
        }
        else {
            BombermanGame.entities.remove(bomberman);
            bomberman.setAliveState(false);
        }
    }

    @Override
    public void update() {

        if((this.x + this.velocityX < limW)
                && (this.x + this.velocityX > 0))
        {
            this.x += velocityX;
        }

        if((this.y + this.velocityY < limH)
                && (this.y + this.velocityY > 0)) {
            this.y += velocityY;
        }

        if (BombermanGame.killedEntities[bomberman.getX() / 32][bomberman.getY() / 32] == 1) {
            killBomber(bomberman);
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
    public void handleCollapse(List<Entity> entities) {
        for(Entity e: entities) {

            if(this.intersectsWith(e)) {
                if((e instanceof Brick) || (e instanceof Wall)) {
                    double velocityX = this.getVelocityX();
                    double velocityY = this.getVelocityY();
                    System.out.println("bick or wall" + velocityX + " " + velocityY);
                    this.addVelocity(-velocityX, -velocityY);
                    this.update();
                }
            }
        }
    }
}
