package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomber;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    public void MoveOneal(Bomber bomber) {
        if (this.getX() > bomber.getX()) {

        }
    }

    public void update() {
        super.update();
    }
}
