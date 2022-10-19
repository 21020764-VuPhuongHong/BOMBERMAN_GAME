package uet.oop.bomberman.level;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.graphics.ConfigLevel;

public class Level3 {
    private static ConfigLevel level3 = new ConfigLevel();

    public Level3() {
        BombermanGame.entities.clear();
        BombermanGame.stillObjects.clear();
        Bomb.numOfBombs = 25;
        BombermanGame.timeLeft = BombermanGame.TIME_FOR_LEVEL;
        BombermanGame.isStart = false;

        level3.buildConfig("res/levels/Level3.txt");
    }

    public void build() {
        level3.createMap();
        level3.createEntities();
    }
}
