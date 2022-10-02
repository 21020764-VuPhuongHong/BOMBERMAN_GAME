package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Bomber extends Entity {

    public ArrayList <String> input = new ArrayList<String>();
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update(double time) {

        if((this.x + this.velocityX< limW - Sprite.step)
                && (this.x + this.velocityX > 0))
        {
            this.x += velocityX;
        }

        if((this.y + this.velocityY < limH - Sprite.step)
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
}
