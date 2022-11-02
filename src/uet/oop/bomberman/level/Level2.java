package uet.oop.bomberman.level;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.items.Items;

public class Level2 {
    private static ConfigLevel level2 = new ConfigLevel();

    public Level2() {
        BombermanGame.entities.clear();
        BombermanGame.stillObjects.clear();
        BombermanGame.listEnemies.clear();
        BombermanGame.listBombs.clear();
        Bomb.numOfBombs = 25;
        BombermanGame.timeLeft = BombermanGame.TIME_FOR_LEVEL - 100;
        BombermanGame.isStart = false;
        Bomber.setHeart(Bomber.getHeart() + 1);

        level2.buildConfig("res/levels/Level2.txt");
        Items.isUncovered = new boolean[ConfigLevel.width][ConfigLevel.height];
    }

    public void build() {
        level2.createMap();
        level2.createEntities();
    }
}
