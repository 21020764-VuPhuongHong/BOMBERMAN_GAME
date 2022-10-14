package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import javafx.geometry.Rectangle2D;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    private int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    private int y;
    private Image img;
    private int velocityX;
    private int velocityY;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int x, int y, Image img) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public void setVelocity(int val_x, int val_y) {
        this.velocityX = val_x;
        this.velocityY = val_y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int _x) {
        this.x = _x;
    }

    public void setY(int _y) {
        this.y = _y;
    }

    public int getVelocityX() {
        return this.velocityX;
    }

    public int getVelocityY() {
        return this.velocityY;
    }

    public double getWidth() {
        return this.img.getWidth();
    }

    public double getHeight() {
        return this.img.getHeight();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();
}