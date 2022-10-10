package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Entity {

    public ArrayList <String> input = new ArrayList<>();
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
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
