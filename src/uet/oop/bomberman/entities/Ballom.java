package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Ballom extends Enemy {
    public Ballom (int x, int y, Image img) {
        super(x, y , img);
    }
    public void update() {
        Move.move(this);
    }
}

