package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.Random;

public class Enemy extends Entity {
    public Enemy (int x, int y, Image img) {
        super(x, y , img);
        this.velocityX = 30;
        this.velocityY = 30;
    }

    public void update(){
    };
}
