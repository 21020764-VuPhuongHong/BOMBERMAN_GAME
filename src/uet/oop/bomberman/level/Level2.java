package uet.oop.bomberman.level;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.graphics.ConfigLevel;

public class Level2 {
    private static ConfigLevel level2 = new ConfigLevel();

    public Level2() {
        BombermanGame.entities.clear();
        BombermanGame.stillObjects.clear();
        Bomb.numOfBombs = 25;
        BombermanGame.timeLeft = BombermanGame.TIME_FOR_LEVEL;
        BombermanGame.isStart = false;

        level2.buildConfig("res/levels/Level2.txt");
    }

    public void build() {
        level2.createMap();
        level2.createEntities();
    }
}
