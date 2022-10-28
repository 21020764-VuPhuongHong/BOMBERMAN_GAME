package uet.oop.bomberman.level;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.items.*;

public class Level1 {
    private static ConfigLevel level1 = new ConfigLevel();

    public Level1() {
        BombermanGame.entities.clear();
        BombermanGame.stillObjects.clear();
        BombermanGame.listEnemies.clear();
        BombermanGame.listBombs.clear();
        Bomb.numOfBombs = 25;
        BombermanGame.timeLeft = BombermanGame.TIME_FOR_LEVEL;
        BombermanGame.isStart = false;
        BombermanGame.bomberStep = 8;
        Bomb.explodingLength = 1;
        Bomb.numBombsPutAtATime = 1;
        WallPassItem.canPassBrick = false;
        BombPassItem.canPassBomb = false;
        FlamePassItem.isExplosionImmune = false;
        DetonatorItem.hasDetonator = false;
        Bomber.setHeart(3);

        level1.buildConfig("res/levels/Level1.txt");
        Items.isUncovered = new boolean[ConfigLevel.width][ConfigLevel.height];
    }

    public void build() {
        level1.createMap();
        level1.createEntities();
    }
}
