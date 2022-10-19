package uet.oop.bomberman.level;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Items.BombItem;
import uet.oop.bomberman.graphics.ConfigLevel;

public class Level1 {
    private static ConfigLevel level1 = new ConfigLevel();

    public Level1() {
        BombermanGame.entities.clear();
        BombermanGame.stillObjects.clear();
        Bomb.numOfBombs = 25;
        BombermanGame.timeLeft = BombermanGame.TIME_FOR_LEVEL;
        BombermanGame.isStart = false;
        BombermanGame.bomberStep = 8;
        Bomb.explodingLength = 1;
        BombItem.hasBombItem = false;

        level1.buildConfig("res/levels/Level1.txt");
    }

    public void build() {
        level1.createMap();
        level1.createEntities();
    }
}
