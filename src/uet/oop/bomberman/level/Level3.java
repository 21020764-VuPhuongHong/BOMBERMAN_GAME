package uet.oop.bomberman.level;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.items.Items;

public class Level3 {
    private static ConfigLevel level3 = new ConfigLevel();

    public Level3() {
        BombermanGame.entities.clear();
        BombermanGame.stillObjects.clear();
        BombermanGame.listEnemies.clear();
        BombermanGame.listBombs.clear();
        Bomb.numOfBombs = 25;
        BombermanGame.timeLeft = BombermanGame.TIME_FOR_LEVEL;
        BombermanGame.isStart = false;
        Bomber.setHeart(Bomber.getHeart() + 1);

        level3.buildConfig("res/levels/Level3.txt");
        Items.isUncovered = new boolean[ConfigLevel.width][ConfigLevel.height];
    }

    public void build() {
        level3.createMap();
        level3.createEntities();
    }
}
