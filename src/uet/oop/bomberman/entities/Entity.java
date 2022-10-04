package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public abstract class Entity {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    public static int limW = Sprite.SCALED_SIZE * WIDTH;
    public static int limH = Sprite.SCALED_SIZE * HEIGHT;

    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    public ArrayList<String> input = new ArrayList<String>();

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int x, int y, Image img) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void setImage(Image img)
    {
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update(double time);

    protected double velocityX;
    protected double velocityY;

    public void setVelocity(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void addVelocity(double val_x, double val_y) {
        this.velocityX = val_x;
        this.velocityY = val_y;

        /*
        if((this.velocityX + val_x < limW - Sprite.SCALED_SIZE)
            && (this.velocityX + val_x > 0))
            {
                this.velocityX += val_x;
            }

        if((this.velocityY + val_y < limH - Sprite.SCALED_SIZE)
            && (this.velocityY + val_y > 0))
            {
                this.velocityY += val_y;
            }*/
    }
}
